package cn.zb96.rtc.core_api.event

interface RoomEventListener {

    /**
     * 入房成功
     */
    fun onJoinRoomSuccess(
        roomId: String
    ) {
    }

    /**
     * 入房失败
     */
    fun onJoinRoomFail(
        roomId: String,
        code: Int,
        msg: String
    ) {
    }

    /**
     * 离开房间
     */
    fun onLeaveRoom(
        roomId: String
    ) {
    }

    /**
     * 房间销毁
     */
    fun onRoomDestroy(
        roomId: String
    ) {
    }
}