package cn.zb96.logkit

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout

object LogFloatBinder {

    private var view: LogFloatView? = null
    private var app: Application?=null
    private var callback: Application.ActivityLifecycleCallbacks?=null

    fun init(app: Application) {
        this.app = app
        this.callback = object :
            Application.ActivityLifecycleCallbacks {

            override fun onActivityResumed(activity: Activity) {
                attach(activity)
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        }
        app.registerActivityLifecycleCallbacks(callback)

    }

    private fun attach(activity: Activity) {
        val config = LogUtils.config  // ✔ 从统一入口拿
        val root = activity.findViewById<ViewGroup>(android.R.id.content)

        if (view == null) {
            view = LogFloatView(activity)

            LogUtils.bindUI { log ->
                view?.append(log, config)
            }
        }

        if (view?.parent != null) return
        // ❗ 防御：未初始化直接跳过
        if (!LogUtils.isInitialized) return

        if (!config.enableFloatUI) return
        root.addView(
            view,
            FrameLayout.LayoutParams(
                config.viewWidth,
                config.viewHeight
            ).apply {
                gravity = Gravity.BOTTOM
            }
        )
    }
    fun release(){
        app?.unregisterActivityLifecycleCallbacks(callback)
    }
}