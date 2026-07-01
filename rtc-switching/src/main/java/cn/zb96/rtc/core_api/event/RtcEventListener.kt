package cn.zb96.rtc.core_api.event

interface RtcEventListener :
    RoomEventListener,
    AudioEventListener,
    VideoEventListener,
    RtcLogEventListener