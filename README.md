# RTC-Switching
[![](https://jitpack.io/v/799685093/RTC-Switching.svg)](https://jitpack.io/#799685093/RTC-Switching)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

**RTC-Switching** 是一套基于 Kotlin 的模块化 Android RTC开发框架，可动态注册音视频引擎、热切换引擎，独立日志模块打印。
## 需求背景
直播项目根据业务层动态注册/切换直播SDK
## 模块总览
| 模块                   | 功能    | 说明                                                           |
|----------------------|-------|--------------------------------------------------------------|
| **rtc-switching**    | RTC切换 | 统一管理、动态注册RTC等                                                |
| **log-kit**          | 日志模块  | 日志打印模块，支持UI、控制台、文件输出                                         |
## 引入方式

### 1. 添加 JitPack 仓库

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. 添加依赖
#### 2.1 kotlin项目
```kotlin
// app/build.gradle.kts
dependencies {
    // 按需引入单个模块（请替换为 JitPack Release 页面的最新版本号）
    val rtcSwitchingVersion = "1.0.0"
    implementation("com.github.799685093.RTC-Switching:rtc-switching:$rtcSwitchingVersion")
    implementation("com.github.799685093.RTC-Switching:log-kit:$rtcSwitchingVersion")

}
```
#### 2.2 Groovy项目
```gradle
// 项目根目录/gradle/libs.versions.toml
[versions]
// 其余配置项...
rtcSwitchingVersion="1.0.1"

[libraries]
// 其余配置项...
rtc-switching = {group = "com.github.799685093.RTC-Switching", name = "rtc-switching", version.ref = "rtcSwitchingVersion"}
rtc-switching-log = {group = "com.github.799685093.RTC-Switching", name = "log-kit", version.ref = "rtcSwitchingVersion"}
```
```gradle
// app/build.gradle
dependencies {
    // 按需引入单个模块（请替换为 JitPack Release 页面的最新版本号）
    implementation libs.rtc.switching
    implementation libs.rtc.switching.log

}
```
## 快速上手
### rtc-switching — RTC切换
#### 初始化
```kotlin
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //...
        //无权限请求 可以在此处直接初始化
        RTCManager.instance.init(app)
    }
    
    //...
}
```
#### 注册模块
可同时注册多个rtc引擎 根据需要进行切换 支持APP热切换
```kotlin
RTCManager.instance.register {
    //TRTCEngineImpl 为实现IRtcEngine的类
            TRTCEngineImpl()
        }
```
#### 实现模块
需要实现基础模块Engine、audio、video、room，可进行模块拓展实现IRtcExtension即可
##### 1.基础模块
```kotlin
class TRTCEngineImpl : IRtcEngine()
class TRTCAudioImpl: IRtcAudio
class TRTCRoomImpl: IRtcRoom
class TRTCVideoImpl: IRtcVideo
```
##### 2.拓展模块
```kotlin
//美颜模块 可自定义实现类方法
class TRTCBeautyImpl: IRtcExtension
```
具体使用可参考demo
### log-kit — 日志模块
#### 初始化
```kotlin
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //...
        //无权限请求 可以在此处直接初始化
        LogUtils.init(this, LogConfig(
            
        ))
    }
    
    //...
}
```
#### LogConfig参数说明
```kotlin
data class LogConfig(

    // ===== 日志过滤 =====
    var minLevel: LogLevel = LogLevel.D,                            //日志打印级别 参考LogLevel

    // ===== 输出开关 =====
    var enableConsoleLog: Boolean = true,                           //是否控制台打印  建议测试开启 生产环境关闭
    var enableFileLog: Boolean = false,                             //是否支持文件输出 建议测试关闭 生产环境开启
    var enableJsonFormat: Boolean = false,                          //输出是否转为Json

    // ===== 文件 =====
    var fileName: String = "app_log.txt",                           //生成文件名称 enableFileLog为true时生效
    var maxFileSize: Long = 5 * 1024 * 1024,                        //生成文件最大大小 enableFileLog为true时生效

    // ✔ 新增：UI总开关（关键）
    var enableFloatUI: Boolean = true,                              //是否显示UI 建议测试时开启 生产环境关闭
    // ===== UI =====
    var textColor: Int = Color.GREEN,                               //UI显示文字颜色
    var textSizeSp: Float = 12f,                                    //UI显示文字大小
    var backgroundColor: Int = "#AA000000".toColorInt(),            //UI背景颜色
    var cornerRadius: Float = 12f,                                  //UI圆角半径
    var viewWidth: Int = FrameLayout.LayoutParams.MATCH_PARENT,     //UI显示宽度
    var viewHeight: Int = 600,                                      //UI显示高度 支持LayoutParams参数
    var autoScroll: Boolean = true,                                 //UI显示时 是否自动滚动
    
    // ===== 性能 =====
    var maxCacheSize: Int = 300,                                    //最大缓存数
    
    //初始化
    var isInitialized : Boolean = true                              //无需修改 传参后判断使用
)
```
#### 进阶功能
支持自定义日志显示组件
```kotlin
val tvResult = (TextView)findViewById(R.id.tv_result)
//设置该项时需设置isCustom为true
LogUtils.bindUI(isCustom = true) { log ->
    tvResult?.append(log)
        }
```