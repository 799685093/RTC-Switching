package cn.zb96.logkit

import android.app.Application
import android.content.Context
import java.util.logging.LogManager

object LogUtils {

    lateinit var config: LogConfig
        private set
    private var uiListener: ((String) -> Unit)? = null
    var isInitialized: Boolean=false
    fun init(app: Application, config: LogConfig) {
        this.config = config
        isInitialized = true
        LogPrinter.init(app, config.fileName)
        // ✔ UI只在允许时初始化
        if (this.config.enableFloatUI) {
            LogFloatBinder.init(app)
        }
    }

    fun bindUI(isCustom: Boolean = false,listener: (String) -> Unit) {
        if (isCustom){
            LogFloatBinder.release()
        }
        uiListener = listener
    }

    fun d(tag: String, msg: String) = log("D", tag, msg)
    fun i(tag: String, msg: String) = log("I", tag, msg)
    fun e(tag: String, msg: String) = log("E", tag, msg)

    private fun log(level: String, tag: String, msg: String) {

        val caller = getCaller()

        val className = caller?.className?.substringAfterLast(".") ?: "Unknown"
        val method = caller?.methodName ?: "unknown"
        val line = caller?.lineNumber ?: -1

        val finalTag = "$className--$method"

        // ✔ 统一格式（UI / file / console 共用）
        val text = "$level/$tag: $finalTag ($className.kt:$line): $msg"

        // ===== 过滤 =====
        if (levelPriority(level) < levelPriority(config.minLevel.name)) return

        // ===== 内存 =====
        LogStore.add(text, config.maxCacheSize)

        // ===== UI =====
        uiListener?.invoke(text)

        // ===== 文件 =====
        LogPrinter.print(text, config)

        // ===== 控制台 =====
        if (config.enableConsoleLog) {
            ConsolePrinter.print(
                level,
                tag,
                "$finalTag ($className.kt:$line)\n$msg",
                config.enableJsonFormat
            )
        }
    }

    private fun getCaller(): StackTraceElement? {

        val trace = Throwable().stackTrace

        return trace.firstOrNull {

            val cls = it.className

            // ✔ 排除 SDK / 系统栈
            !cls.startsWith("java.") &&
                    !cls.startsWith("android.") &&
                    !cls.startsWith("kotlin.") &&
                    !cls.contains("LogManager") &&
                    !cls.contains("LogUtils") &&
                    !cls.contains("Thread")
        }
    }
    private fun levelPriority(level: String): Int {
        return when (level) {
            "D" -> 0
            "I" -> 1
            "W" -> 2
            "E" -> 3
            else -> 0
        }
    }
}