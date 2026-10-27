# Add project-specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles directive in build.gradle.kts.

# Kotlin Metadata & Serialization
-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod
-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
}
-keep,allowobfuscation @interface kotlinx.serialization.Serializable
-keep,allowobfuscation @interface kotlinx.serialization.SerialName

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Koin Dependency Injection
-keep class org.koin.** { *; }
-keep class com.spoonofcode.poa.** { *; }

# Ktor Networking
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# Landscapist / Coil / Image Loading
-keep class com.github.skydoves.landscapist.** { *; }
-keep class coil3.** { *; }
-dontwarn com.github.skydoves.landscapist.**
-dontwarn coil3.**

# Compottie Lottie Animation
-keep class io.github.alexzhirkevich.compottie.** { *; }
-dontwarn io.github.alexzhirkevich.compottie.**

# Kermit Logging
-keep class co.touchlab.kermit.** { *; }
-dontwarn co.touchlab.kermit.**

# Navigation 3 / Compose Navigation
-keep class androidx.navigation3.** { *; }
-keep class org.jetbrains.androidx.navigation.** { *; }
-dontwarn androidx.navigation3.**
-dontwarn org.jetbrains.androidx.navigation.**

# Compose / Navigation / Lifecycle
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}
