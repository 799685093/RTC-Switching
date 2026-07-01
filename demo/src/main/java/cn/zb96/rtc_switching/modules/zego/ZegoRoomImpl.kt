package cn.zb96.rtc_switching.modules.zego

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcRoom

class ZegoRoomImpl: IRtcRoom {
    private val TAG = "ZegoRoomImpl"
    private var mListener: RtcEventListener? = null
    val mRoomId:String?=null

    /**
     * 进入房间
     */
    override fun join(
        roomId: String,
        userId: String,
        token: String?,
        role: Int?
    ) {

        LogUtils.e(TAG, "join: roomId=$roomId,userId=$userId,token=$token,role=$role")
    }

    /**
     * 离开房间
     */
    override fun leave() {
        LogUtils.e(TAG, "leave: ")
    }

    /**
     * 切换角色
     *
     * 主播
     * 观众
     */
    override fun switchRole(role: Int) {
        LogUtils.e(TAG, "switchRole: role=$role")
    }

    /**
     * 当前房间
     */
    override fun currentRoomId(): String? {
        LogUtils.e(TAG, "currentRoomId: ")
        return mRoomId
    }

    /**
     * 是否已入房
     */
    override fun isInRoom(): Boolean {

        return true
    }

    /**
     * 设置监听事件
     */
    override fun addListener(listener: RtcEventListener?) {
        LogUtils.e(TAG, "addListener")
        this.mListener = listener
    }

    override fun getListener(): RtcEventListener? {
        LogUtils.e(TAG, "getListener")
        return mListener
    }

    /**
     * 移除监听事件
     */
    override fun removeListener() {
        LogUtils.e(TAG, "removeListener")
        mListener = null
    }

    /**
     * 资源释放
     */
    override fun release() {
        removeListener()
        leave()
    }
}