package cn.zb96.rtc_switching.modules.trtc

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcAudio

class TRTCAudioImpl: IRtcAudio {

    private val TAG = "TRTCAudioImpl"
    var mListener: RtcEventListener? = null
    /**
     * 开启麦克风
     */
    override fun openMic() {
        LogUtils.e(TAG, "openMic")
    }

    /**
     * 关闭麦克风
     */
    override fun closeMic() {
        LogUtils.e(TAG, "closeMic")
    }

    /**
     * 本地静音
     */
    override fun muteLocalAudio(mute: Boolean) {
        LogUtils.e(TAG, "muteLocalAudio: mute=$mute")
    }

    /**
     * 静音远端用户
     */
    override fun muteRemoteAudio(userId: String, mute: Boolean) {
        LogUtils.e(TAG, "muteRemoteAudio: userId=$userId,mute=$mute")
    }

    /**
     * 扬声器
     */
    override fun setSpeakerOn(enable: Boolean) {
        LogUtils.e(TAG, "setSpeakerOn: enable=$enable")
    }

    /**
     * 耳返
     */
    override fun setEarMonitor(enable: Boolean) {
        LogUtils.e(TAG, "setEarMonitor: enable=$enable")
    }

    /**
     * 音量
     */
    override fun setVolume(volume: Int) {
        LogUtils.e(TAG, "setVolume: volume=$volume")
    }

    /**
     * 设置监听事件
     */
    override fun addListener(listener: RtcEventListener?) {
        LogUtils.e(TAG, "addListener")
        this.mListener = listener
    }

    override fun getListener(): RtcEventListener? {
        LogUtils.e(TAG, "getListener")
        return mListener
    }

    /**
     * 移除监听事件
     */
    override fun removeListener() {
        LogUtils.e(TAG, "removeListener")
        this.mListener = null
    }

    /**
     * 资源释放
     */
    override fun release() {
        LogUtils.e(TAG,"release")
        removeListener()
        closeMic()
        muteLocalAudio(mute = true)
    }
}