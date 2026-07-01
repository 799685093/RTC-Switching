package cn.zb96.rtc.core_api.rtc

import cn.zb96.rtc.core_api.event.ICommonEventListener
import cn.zb96.rtc.core_api.event.RtcEventListener

interface IRtcEventListener : ICommonEventListener{

    /**
     * 设置监听事件
     */
    fun addListener(listener: RtcEventListener?)

    fun getListener(): RtcEventListener?

    /**
     * 移除监听事件
     */
    fun removeListener()

}