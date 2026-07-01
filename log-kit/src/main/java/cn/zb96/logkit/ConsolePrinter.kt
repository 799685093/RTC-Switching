package cn.zb96.logkit

import android.util.Log
import org.json.JSONObject

object ConsolePrinter {

    fun print(
        level: String,
        tag: String,
        msg: String,
        enableJson: Boolean
    ) {

        val output = if (enableJson) formatJson(msg) else msg

        when (level) {
            "D" -> Log.d(tag, output)
            "I" -> Log.i(tag, output)
            "W" -> Log.w(tag, output)
            "E" -> Log.e(tag, output)
            else -> Log.d(tag, output)
        }
    }

    private fun formatJson(msg: String): String {
        return try {
            JSONObject(msg).toString(2)
        } catch (e: Exception) {
            msg
        }
    }
}