package cn.zb96.rtc.core_api.rtc

interface IRtcAudio :IRtcEventListener {

    /**
     * 开启麦克风
     */
    fun openMic()

    /**
     * 关闭麦克风
     */
    fun closeMic()

    /**
     * 本地静音
     */
    fun muteLocalAudio(
        mute: Boolean
    )

    /**
     * 静音远端用户
     */
    fun muteRemoteAudio(
        userId: String,
        mute: Boolean
    )

    /**
     * 扬声器
     */
    fun setSpeakerOn(
        enable: Boolean
    )

    /**
     * 耳返
     */
    fun setEarMonitor(
        enable: Boolean
    )

    /**
     * 音量
     */
    fun setVolume(
        volume: Int
    )
}