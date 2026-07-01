package cn.zb96.rtc.core_api

import cn.zb96.rtc.core_api.event.onRtcStatusCallback
import cn.zb96.rtc.core_api.model.RtcRoomConfig
import cn.zb96.rtc.core_api.model.RtcSdkConfig
import cn.zb96.rtc.core_api.rtc.IRtcEngine

class RTCManager private constructor() {

    companion object {
        val instance by lazy { RTCManager() }
    }

    private lateinit var context: Any
    private val engineMap = HashMap<Int, IRtcEngine>()
    private val factoryMap = HashMap<Int, () -> IRtcEngine>()

    private var currentEngine: IRtcEngine? = null
    private var currentRoomInfo: RtcRoomConfig? = null

    /**
     * 全局启动时调用一次
     */
    fun init(context: Any){
        this.context = context
    }

    fun register(factory: () -> IRtcEngine) {
        val engine = factory()
        val type = engine.getRtcType()

        factoryMap[type] = factory
        engineMap[type] = engine
        if (currentEngine == null){
            switchEngine(type)
        }
    }

    fun switchEngine(type: Int){

        val oldEngine = currentEngine

        val newEngine = engineMap.getOrPut(type) {
            factoryMap[type]?.invoke()
                ?: error("No engine registered for type=$type")
        }

        if (oldEngine == newEngine) return

        // 🔁 热切换核心逻辑
        hotSwitch(oldEngine, newEngine)

        currentEngine = newEngine
    }

    fun initSDK(sdkConfig: RtcSdkConfig,callback: onRtcStatusCallback?){
        getEngine()?.initSDK(sdkConfig,callback)
    }

    private fun hotSwitch(old: IRtcEngine?, new: IRtcEngine) {


        old?.release()

        // ⚠️ 关键：复用 session 信息
        new.init(context)
        new.updateRoomConfig(currentRoomInfo)
    }

    /**
     * 释放资源 一般在整个APP退出时使用
     */
    fun release() {
        for (factory in factoryMap.values) {
            factory.invoke().release()
        }
        factoryMap.clear()
        engineMap.clear()
        currentEngine = null
        currentRoomInfo = null
    }

    fun getEngine(): IRtcEngine?{
        return currentEngine
    }
}