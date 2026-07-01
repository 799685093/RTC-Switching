package cn.zb96.rtc.core_api.event

interface onRtcStatusCallback {

    fun onSuccess()

    fun onFailed(code: Int, msg: String)
}