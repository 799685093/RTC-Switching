package cn.zb96.logkit

object LogStore {

    private val logs = mutableListOf<String>()

    fun add(log: String, maxSize: Int) {
        logs.add(log)
        if (logs.size > maxSize) {
            logs.removeAt(0)
        }
    }

    fun getAll(): List<String> = logs
}