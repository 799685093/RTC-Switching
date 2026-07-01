package cn.zb96.rtc_switching.modules.trtc

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.onRtcStatusCallback
import cn.zb96.rtc.core_api.model.RtcSdkConfig
import cn.zb96.rtc.core_api.rtc.IRtcAudio
import cn.zb96.rtc.core_api.rtc.IRtcEngine
import cn.zb96.rtc.core_api.rtc.IRtcRoom
import cn.zb96.rtc.core_api.rtc.IRtcVideo
import cn.zb96.rtc.core_api.type.RtcSDKType

class TRTCEngineImpl : IRtcEngine() {

    private val TAG = "TRTCEngineImpl"
    var initialed: Boolean = false
    val room by lazy { TRTCRoomImpl() }
    val audio by lazy { TRTCAudioImpl() }

    val video by lazy { TRTCVideoImpl() }
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
        return RtcSDKType.RTC_SDK_TYPE_TENCENT
    }

    override fun initData() {
        //此处预留SDK初始化后的数据处理
        LogUtils.e(TAG, "initData")
        addExtension(TRTCBeautyImpl::class.java, TRTCBeautyImpl())
    }

    override fun room(): IRtcRoom? {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
            return null
        }
        return room
    }

    override fun audio(): IRtcAudio? {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
            return null
        }
        return audio
    }

    override fun video(): IRtcVideo? {
        if (!initialed){
            LogUtils.e(TAG, "SDK not initialized")
            return null
        }
        return video
    }
}