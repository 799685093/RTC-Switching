package cn.zb96.rtc_switching.modules.zego

import android.view.View
import cn.zb96.logkit.LogUtils

import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcVideo

class ZegoVideoImpl: IRtcVideo {

    private val TAG = "ZegoVideoImpl"
    private var mListener: RtcEventListener? = null

    /**
     * 开启摄像头
     */
    override fun openCamera() {
        LogUtils.e(TAG, "openCamera")
    }

    /**
     * 关闭摄像头
     */
    override fun closeCamera() {
        LogUtils.e(TAG, "closeCamera")
    }

    /**
     * 切换前后摄像头
     */
    override fun switchCamera() {
        LogUtils.e(TAG, "switchCamera")
    }

    /**
     * 本地预览
     */
    override fun startLocalPreview(view: View) {
        LogUtils.e(TAG, "startLocalPreview")
    }

    /**
     * 停止预览
     */
    override fun stopLocalPreview() {
        LogUtils.e(TAG, "stopLocalPreview")
    }

    /**
     * 远端渲染
     */
    override fun startRemoteRender(userId: String, view: View) {
        LogUtils.e(TAG, "startRemoteRender")
    }

    /**
     * 停止远端渲染
     */
    override fun stopRemoteRender(userId: String) {
        LogUtils.e(TAG, "stopRemoteRender")
    }

    /**
     * 远端视频订阅
     */
    override fun muteRemoteVideo(userId: String, mute: Boolean) {
        LogUtils.e(TAG, "muteRemoteVideo")
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
        mListener = null
    }

    /**
     * 资源释放
     */
    override fun release() {
        LogUtils.e(TAG, "release")
        removeListener()
        stopLocalPreview()
        closeCamera()
    }
}