package cn.zb96.rtc.core_api.rtc

import android.view.View

interface IRtcVideo :IRtcEventListener{

    /**
     * 开启摄像头
     */
    fun openCamera()

    /**
     * 关闭摄像头
     */
    fun closeCamera()

    /**
     * 切换前后摄像头
     */
    fun switchCamera()

    /**
     * 本地预览
     */
    fun startLocalPreview(
        view: View
    )

    /**
     * 停止预览
     */
    fun stopLocalPreview()

    /**
     * 远端渲染
     */
    fun startRemoteRender(
        userId: String,
        view: View
    )

    /**
     * 停止远端渲染
     */
    fun stopRemoteRender(
        userId: String
    )

    /**
     * 远端视频订阅
     */
    fun muteRemoteVideo(
        userId: String,
        mute: Boolean
    )
}