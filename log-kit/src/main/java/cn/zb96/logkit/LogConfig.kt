package cn.zb96.logkit

import android.graphics.Color
import android.widget.FrameLayout
import androidx.core.graphics.toColorInt

data class LogConfig(

    // ===== 日志过滤 =====
    var minLevel: LogLevel = LogLevel.D,

    // ===== 输出开关 =====
    var enableConsoleLog: Boolean = true,
    var enableFileLog: Boolean = false,
    var enableJsonFormat: Boolean = false,

    // ===== 文件 =====
    var fileName: String = "app_log.txt",
    var maxFileSize: Long = 5 * 1024 * 1024,

    // ✔ 新增：UI总开关（关键）
    var enableFloatUI: Boolean = true,
    // ===== UI =====
    var textColor: Int = Color.GREEN,
    var textSizeSp: Float = 12f,
    var backgroundColor: Int = "#AA000000".toColorInt(),
    var cornerRadius: Float = 12f,
    var viewWidth: Int = FrameLayout.LayoutParams.MATCH_PARENT,
    var viewHeight: Int = 600,

    // ===== 性能 =====
    var maxCacheSize: Int = 300,
    var autoScroll: Boolean = true,

    //初始化
    var isInitialized : Boolean = true
)