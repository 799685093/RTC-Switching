package cn.zb96.rtc_switching

import android.app.Application
import cn.zb96.logkit.LogConfig
import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.RTCManager

class App: Application() {

    lateinit var app: App
    override fun onCreate() {
        super.onCreate()
        app = this
        LogUtils.init(this, LogConfig(
            enableFileLog = true,
            enableConsoleLog = true,
            enableFloatUI = true
        ))
        RTCManager.instance.init(app)
    }

}