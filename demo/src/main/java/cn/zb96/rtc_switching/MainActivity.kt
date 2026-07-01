package cn.zb96.rtc_switching

import android.os.Bundle
import android.util.Log.v
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cn.zb96.logkit.LogUtils
import cn.zb96.rtc.core_api.RTCManager
import cn.zb96.rtc.core_api.model.RtcRoomConfig
import cn.zb96.rtc.core_api.model.RtcSdkConfig
import cn.zb96.rtc.core_api.type.RtcRoleType
import cn.zb96.rtc.core_api.type.RtcSDKType
import cn.zb96.rtc_switching.databinding.ActivityMainBinding
import cn.zb96.rtc_switching.modules.trtc.TRTCBeautyImpl
import cn.zb96.rtc_switching.modules.trtc.TRTCEngineImpl
import cn.zb96.rtc_switching.modules.zego.ZegoEngineImpl


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var binding: ActivityMainBinding? = null
    val roomId = "room_1"
    val userId = "user_1001"
    val token = "sdk token"

    override fun onCreate(savedInstanceState: Bundle?) {
        this.enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val rootView  = layoutInflater.inflate(R.layout.activity_main,null)
        setContentView(rootView)
        binding = ActivityMainBinding.bind(rootView)
        setCustomLogView()
        setListener()
    }

    private fun registerEngine() {
        //此处可注册多个引擎 也可根据业务需求选择注册
        RTCManager.instance.register {
            TRTCEngineImpl()
        }
        RTCManager.instance.register {
            ZegoEngineImpl()
        }
    }

    private fun setListener() {
        binding!!.btnInit.setOnClickListener { v: View? ->
            initConfig()
        }
        binding!!.btnRegister.setOnClickListener{ v:View? ->
            registerEngine()
        }
        binding!!.btnJoin.setOnClickListener { v: View? ->
            setRoomInfo()
        }
        binding!!.btnLeave.setOnClickListener { v: View? ->
            leave()
        }
        binding!!.btnDestroy.setOnClickListener { v: View? ->
            destroy()
        }
        binding!!.btnSwitch.setOnClickListener { v: View? ->
            switchSdk()
        }
        binding!!.btnExtension.setOnClickListener {
            extension()
        }
    }

    fun extension() {
        RTCManager.instance.getEngine()?.getExtension(TRTCBeautyImpl::class.java)?.let {
            LogUtils.d(TAG,"获取到拓展组件")
        }?:run {
            LogUtils.e(TAG,"未获取到拓展组件")
        }
    }

    fun switchSdk() {
        RTCManager.instance.getEngine()?.let {
            if (it.getRtcType() == RtcSDKType.RTC_SDK_TYPE_ZEGO){
                RTCManager.instance.switchEngine(RtcSDKType.RTC_SDK_TYPE_TENCENT)
            }else{
                RTCManager.instance.switchEngine(RtcSDKType.RTC_SDK_TYPE_ZEGO)
            }
        }?:run {
            RTCManager.instance.switchEngine(RtcSDKType.RTC_SDK_TYPE_TENCENT)
        }
        RTCManager.instance.initSDK(RtcSdkConfig(),null)

    }


    fun destroy() {
        RTCManager.instance.release()
    }

    fun leave() {
        RTCManager.instance.getEngine()?.room()?.leave()
    }

    /**
     * 加入房间
     */
    private fun setRoomInfo() {
        RTCManager.instance.getEngine()?.updateRoomConfig(RtcRoomConfig(
            roomId = roomId,
            userId = userId,
            token = token,
            role = RtcRoleType.ROLE_AUDIENCE
        ))
    }

    private fun setCustomLogView(){
        LogUtils.bindUI(true) { log ->
            binding?.tvResult?.append(log)
        }
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {
        //此处可以放在Application的onCreate方法中执行 确保APP全局
        RTCManager.instance.init(applicationContext)
    }
}