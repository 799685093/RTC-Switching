package cn.zb96.rtc_switching.modules.trtc

import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.event.RtcEventListener
import cn.zb96.rtc.core_api.rtc.IRtcExtension

class TRTCBeautyImpl: IRtcExtension {
    private val TAG = "TRTCBeautyImpl"
    private var mListener: RtcEventListener?=null
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
        //自行处理新增模块的资源释放
    }
}