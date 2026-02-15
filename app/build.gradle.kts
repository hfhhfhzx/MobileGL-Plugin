plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mio.plugin.renderer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mio.plugin.renderer"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    
    signingConfigs {
        create("release") {
            storeFile = file("../keystore")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD") ?: project.findProperty("SIGNING_STORE_PASSWORD") as String?
            keyAlias = System.getenv("SIGNING_KEY_ALIAS") ?: project.findProperty("SIGNING_KEY_ALIAS") as String?
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD") ?: project.findProperty("SIGNING_KEY_PASSWORD") as String?
        }
    }

    buildFeatures {
        resValues = true
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
        }
        configureEach {
            //应用名
            //app name
            resValue("string","app_name","MobileGL Renderer")
            //包名后缀
            //package name Suffix
            applicationIdSuffix = ".mobilegl"

            //渲染器在启动器内显示的名称
            //The name displayed by the renderer in the launcher
            manifestPlaceholders["des"] = "MobileGL"
            //渲染器的具体定义 格式为 名称:渲染器库名:EGL库名 例如 LTW:libltw.so:libltw.so
            //The specific definition format of a renderer is ${name}:${renderer library name}:${EGL library name}, for example:   LTW:libltw.so:libltw.so
            manifestPlaceholders["renderer"] = "MobileGL:libMobileGL.so:libMobileGL.so"

            //特殊Env
            //Special Env
            //DLOPEN=libxxx.so 用于加载额外库文件
            //DLOPEN=libxxx.so used to load external library
            //如果有多个库,可以使用","隔开,例如  DLOPEN=libxxx.so,libyyy.so
            //If there are multiple libraries, you can use "," to separate them, for example  DLOPEN=libxxx.so,libyyy.so
            manifestPlaceholders["boatEnv"] = mutableMapOf<String,String>().apply {

            }.run {
                var env = ""
                forEach { (key, value) ->
                    env += "$key=$value:"
                }
                env.dropLast(1)
            }

            manifestPlaceholders["pojavEnv"] = mutableMapOf<String,String>().apply {

            }.run {
                var env = ""
                forEach { (key, value) ->
                    env += "$key=$value:"
                }
                env.dropLast(1)
            }

            //最小支持的MC版本
            //The minimum supported MC version
            manifestPlaceholders["minMCVer"] = "1.17"
            //最大支持的MC版本
            //The maximum supported MC version
            manifestPlaceholders["maxMCVer"] = "" //为空则不限制 No restriction if empty
        }
    }
}

dependencies {
}
