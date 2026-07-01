package cn.zb96.rtc_switching.modules.trtc

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcRoom

class TRTCRoomImpl: IRtcRoom {
    private val TAG = "TRTCRoomImpl"
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

        LogUtils.d(TAG, "join: roomId=$roomId,userId=$userId,token=$token,role=$role")
    }

    /**
     * 离开房间
     */
    override fun leave() {
        LogUtils.d(TAG, "leave: ")
    }

    /**
     * 切换角色
     *
     * 主播
     * 观众
     */
    override fun switchRole(role: Int) {
        LogUtils.d(TAG, "switchRole: role=$role")
    }

    /**
     * 当前房间
     */
    override fun currentRoomId(): String? {
        LogUtils.d(TAG, "currentRoomId: ")
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
        LogUtils.d(TAG, "addListener")
        this.mListener = listener
    }

    override fun getListener(): RtcEventListener? {
        LogUtils.d(TAG, "getListener")
        return mListener
    }

    /**
     * 移除监听事件
     */
    override fun removeListener() {
        LogUtils.d(TAG, "removeListener")
        mListener = null
    }
    /**
     * 资源释放
     */
    override fun release() {
        LogUtils.d(TAG, "release")
        removeListener()
        leave()
    }
}