plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.lsplugin.cmaker)
}

val mobileGlLogActiveLevel: String by lazy {
    (rootProject.findProperty("mobilegl.logLevel") as? String)
        ?: (System.getenv("MOBILEGL_LOG_ACTIVE_LEVEL") ?: "MOBILEGL_LOG_LEVEL_INFO")
}

cmaker {
    default {
        // 指定编译的目标 CPU 架构
        // 可用值：arm64-v8a, armeabi-v7a, x86, x86_64, riscv64
        abiFilters("arm64-v8a")
    }
}

android {
    namespace = "top.mobilegl.mobilegl"
    compileSdk = 37

    defaultConfig {
        minSdk = 21
        
        externalNativeBuild {
        cmake {
            arguments += "-DMOBILEGL_LOG_ACTIVE_LEVEL=${mobileGlLogActiveLevel}"
        }
    }
    }

    buildTypes {
        release{
            optimization {
                enable = true
            }
        }
    }

    externalNativeBuild {
        cmake {
            path = file("MobileGL/CMakeLists.txt")
        }
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}
