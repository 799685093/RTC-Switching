package cn.zb96.logkit

import android.R.attr.translationX
import android.R.attr.translationY
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.TextView

class LogFloatView(context: Context) : FrameLayout(context) {

    private val textView = TextView(context)
    private val scrollView = ScrollView(context)

    private var expanded = true

    init {
        scrollView.addView(textView)
        addView(scrollView)

        applyStyle(LogUtils.config)

        setOnClickListener { toggle() }

        enableDrag()
    }

    fun applyStyle(config: LogConfig) {

        setBackgroundColor(config.backgroundColor)

        textView.setTextColor(config.textColor)
        textView.textSize = config.textSizeSp

        background = GradientDrawable().apply {
            setColor(config.backgroundColor)
            cornerRadius = config.cornerRadius
        }
    }

    fun append(log: String, config: LogConfig) {

        textView.append(log + "\n")

        if (config.autoScroll) {
            scrollView.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }

    private fun toggle() {
        expanded = !expanded
        scrollView.visibility = if (expanded) VISIBLE else GONE
    }

    private fun enableDrag() {

        var lastX = 0f
        var lastY = 0f

        setOnTouchListener { _, event ->

            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    lastX = event.rawX
                    lastY = event.rawY
                }

                MotionEvent.ACTION_MOVE -> {

                    val dx = event.rawX - lastX
                    val dy = event.rawY - lastY

                    translationX += dx
                    translationY += dy

                    lastX = event.rawX
                    lastY = event.rawY
                }
            }

            true
        }
    }
}