plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.lsplugin.cmaker)
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
