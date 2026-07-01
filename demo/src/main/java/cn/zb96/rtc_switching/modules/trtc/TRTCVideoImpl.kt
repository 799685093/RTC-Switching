package cn.zb96.rtc_switching.modules.trtc

import android.view.View
import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcVideo

class TRTCVideoImpl: IRtcVideo {

    private val TAG = "TRTCVideoImpl"
    private var mListener: RtcEventListener? = null

    /**
     * 开启摄像头
     */
    override fun openCamera() {
        LogUtils.d(TAG, "openCamera")
    }

    /**
     * 关闭摄像头
     */
    override fun closeCamera() {
        LogUtils.d(TAG, "closeCamera")
    }

    /**
     * 切换前后摄像头
     */
    override fun switchCamera() {
        LogUtils.d(TAG, "switchCamera")
    }

    /**
     * 本地预览
     */
    override fun startLocalPreview(view: View) {
        LogUtils.d(TAG, "startLocalPreview")
    }

    /**
     * 停止预览
     */
    override fun stopLocalPreview() {
        LogUtils.d(TAG, "stopLocalPreview")
    }

    /**
     * 远端渲染
     */
    override fun startRemoteRender(userId: String, view: View) {
        LogUtils.d(TAG, "startRemoteRender")
    }

    /**
     * 停止远端渲染
     */
    override fun stopRemoteRender(userId: String) {
        LogUtils.d(TAG, "stopRemoteRender")
    }

    /**
     * 远端视频订阅
     */
    override fun muteRemoteVideo(userId: String, mute: Boolean) {
        LogUtils.d(TAG, "muteRemoteVideo")
    }

    /**
     * 设置监听事件
     */
    override fun addListener(listener: RtcEventListener?) {
        LogUtils.d(TAG, "addListener")
        this.mListener = listener
    }

    override fun getListener(): RtcEventListener? {
        LogUtils.d(TAG, "getListener")
        return mListener
    }

    /**
     * 移除监听事件
     */
    override fun removeListener() {
        LogUtils.d(TAG, "removeListener")
        mListener = null
    }

    /**
     * 资源释放
     */
    override fun release() {
        LogUtils.d(TAG, "release")
        removeListener()
        stopLocalPreview()
        closeCamera()
    }
}