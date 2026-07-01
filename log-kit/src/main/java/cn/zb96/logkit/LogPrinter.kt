package cn.zb96.logkit

import android.content.Context
import java.io.File

object LogPrinter {

    private var file: File? = null
    private val lock = Any()

    fun init(context: Context, fileName: String) {
        file = File(context.filesDir, fileName)
        if (!file!!.exists()) file!!.createNewFile()
    }

    fun print(log: String, config: LogConfig) {

        if (file == null || !config.enableFileLog) return

        synchronized(lock) {

            try {
                // 控制文件大小
                if (file!!.length() > config.maxFileSize) {
                    file!!.writeText("") // 清空
                }

                file!!.appendText(
                    "${System.currentTimeMillis()} $log\n"
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFile(): File? = file
}