package cn.zb96.rtc.core_api.event

interface VideoEventListener {

    /**
     * 摄像头变化
     */
    fun onUserCameraChanged(
        userId: String,
        open: Boolean
    ) {
    }

    /**
     * 首帧
     */
    fun onFirstVideoFrame(
        userId: String
    ) {
    }

    /**
     * 视频丢失
     */
    fun onVideoLost(
        userId: String
    ) {
    }
}