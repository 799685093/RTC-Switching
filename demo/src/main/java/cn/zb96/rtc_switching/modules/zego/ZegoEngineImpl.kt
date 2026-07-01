package cn.zb96.rtc_switching.modules.zego

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.onRtcStatusCallback
import cn.zb96.rtc.core_api.model.RtcSdkConfig
import cn.zb96.rtc.core_api.rtc.IRtcAudio
import cn.zb96.rtc.core_api.rtc.IRtcEngine
import cn.zb96.rtc.core_api.rtc.IRtcRoom
import cn.zb96.rtc.core_api.rtc.IRtcVideo
import cn.zb96.rtc.core_api.type.RtcSDKType

class ZegoEngineImpl : IRtcEngine() {

    private val TAG = "ZegoEngineImpl"
    var initialed: Boolean = false
    val room by lazy { ZegoRoomImpl() }
    val audio by lazy { ZegoAudioImpl() }

    val video by lazy { ZegoVideoImpl() }
    /**
     * 初始化SDK
     */
    override fun initSDK(
        rtcSdkConfig: RtcSdkConfig,
        callback: onRtcStatusCallback?
    ) {
        //此处做SDK初始化操作
        initialed = true
        LogUtils.e(TAG, "initSDK: rtcSdkConfig=$rtcSdkConfig")
    }

    override fun getRtcType(): Int {
        return RtcSDKType.RTC_SDK_TYPE_ZEGO
    }

    override fun initData() {
        //此处预留SDK初始化后的数据处理
        LogUtils.e(TAG, "initData")
    }

    override fun room(): IRtcRoom {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
        }
        return room
    }

    override fun audio(): IRtcAudio {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
        }
        return audio
    }

    override fun video(): IRtcVideo {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
        }
        return video
    }
}