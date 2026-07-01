package cn.zb96.rtc.core_api.event

interface AudioEventListener {

    /**
     * 用户开关麦
     */
    fun onUserMicChanged(
        userId: String,
        open: Boolean
    ) {
    }

    /**
     * 音量回调
     */
    fun onUserVolumeChanged(
        userId: String,
        volume: Int
    ) {
    }

    /**
     * 正在说话
     */
    fun onUserSpeaking(
        userId: String
    ) {
    }

    /**
     * 停止说话
     */
    fun onUserStopSpeaking(
        userId: String
    ) {
    }
}