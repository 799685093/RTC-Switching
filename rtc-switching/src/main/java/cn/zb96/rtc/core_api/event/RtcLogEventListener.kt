package cn.zb96.rtc.core_api.event

interface RtcLogEventListener {

    /**
     * 错误信息
     */
    fun onError(code: Int, msg: String) {

    }

    /**
     * 普通信息
     */
    fun onInfo(msg: String) {

    }

}