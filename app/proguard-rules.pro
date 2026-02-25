-obfuscationdictionary dictionary.txt
-classobfuscationdictionary dictionary.txt
-packageobfuscationdictionary dictionary.txt

-keeppackagenames com.fcl.plugin.mobilegl

-keep public class com.mio.plugin.renderer.MainActivity

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keep class **.R$* { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}
