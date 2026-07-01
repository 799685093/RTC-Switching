package cn.zb96.rtc.core_api.rtc

import cn.zb96.rtc.core_api.type.RtcRoleType

interface IRtcRoom :IRtcEventListener{


    /**
     * 进入房间
     */
    fun join(
        roomId: String,
        userId: String,
        token: String?=null,
        role: Int?= RtcRoleType.ROLE_AUDIENCE
    )

    /**
     * 离开房间
     */
    fun leave()

    /**
     * 切换角色
     *
     * 主播
     * 观众
     */
    fun switchRole(
        role: Int
    )

    /**
     * 当前房间
     */
    fun currentRoomId(): String?

    /**
     * 是否已入房
     */
    fun isInRoom(): Boolean



}