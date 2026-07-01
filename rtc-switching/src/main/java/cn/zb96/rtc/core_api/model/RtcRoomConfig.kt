package cn.zb96.rtc.core_api.model

import cn.zb96.rtc.core_api.type.RtcRoleType

data class RtcRoomConfig(

    val roomId: String,

    val userId: String,

    val token: String?=null,

    val role: Int?= RtcRoleType.ROLE_AUDIENCE
)