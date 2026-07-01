package cn.zb96.rtc.core_api.rtc

import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.event.onRtcStatusCallback
import cn.zb96.rtc.core_api.model.RtcRoomConfig
import cn.zb96.rtc.core_api.model.RtcSdkConfig
import cn.zb96.rtc.core_api.type.RtcRoleType

abstract class IRtcEngine :IRtcEventListener{

    var mListener: RtcEventListener?=null
    var extensionMap = HashMap<Class<*>, IRtcExtension>()
    var roomConfig: RtcRoomConfig? = null
    lateinit var context: Any

    fun init(context: Any){
        this.context = context
        initData()
    }

    /**
     * 初始化SDK
     */
    abstract fun initSDK(rtcSdkConfig: RtcSdkConfig,callback: onRtcStatusCallback?)

    /**
     * 获取sdk类型
     */
    abstract fun getRtcType(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 房间操作
     */
    abstract fun room(): IRtcRoom?

    /**
     * 音频操作
     */
    abstract fun audio(): IRtcAudio?

    /**
     * 视频操作
     */
    abstract fun video(): IRtcVideo?

    fun <T : IRtcExtension> getExtension(
        clazz: Class<T>
    ): IRtcExtension?
    {
        return extensionMap[clazz]
    }

    fun <T : IRtcExtension> addExtension(clazz: Class<T>,extension: IRtcExtension){
        extensionMap[clazz] = extension
    }

    override fun addListener(listener: RtcEventListener?) {
        mListener = listener
        room()?.addListener(listener)
        audio()?.addListener(listener)
        video()?.addListener(listener)
        extensionMap.let {
            for (extension in it.values) {
                extension.addListener(listener)
            }
        }

    }

    override fun getListener(): RtcEventListener? {
        return mListener
    }

    override fun removeListener() {
        mListener = null
        room()?.removeListener()
        audio()?.removeListener()
        video()?.removeListener()
        extensionMap.let {
            for (extension in it.values) {
                extension.removeListener()
            }
        }
    }

    override fun release(){
        removeListener()
        room()?.release()
        audio()?.release()
        video()?.release()
    }

    /**
     * 解析为rtc需要的房间id
     */
    fun getRtcRoomId(roomId: String): String{
        return roomId
    }

    /**
     * 解析为rtc需要的用户id
     */
    fun getRtcUserId(userId: String): String{
        return userId
    }
    fun getRtcToken(rtcRoomConfig: RtcRoomConfig): String{
        return ""
    }

    fun getRtcRole(role: Int?): Int{
        return role?: RtcRoleType.ROLE_AUDIENCE
    }

     fun updateRoomConfig(currentRoomInfo: RtcRoomConfig?){
         roomConfig = currentRoomInfo
         roomConfig?.let { it
             room()?.join(getRtcRoomId(it.roomId),getRtcUserId(it.userId),getRtcToken(it),getRtcRole(it.role))
         }
    }
}