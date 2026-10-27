# Add project-specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles directive in build.gradle.kts.

# Kotlin Metadata & Serialization
-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod
-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}

# Coroutines
-keep class kotlinx.coroutines.** { *; }

# Koin Dependency Injection
-keep class org.koin.** { *; }

# Compose / Navigation / Lifecycle
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault
