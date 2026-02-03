import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.spoonofcode.convention.pathToPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class BuildKonfigConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.codingfeline.buildkonfig")
            }

            extensions.configure<BuildKonfigExtension> {
                packageName = target.pathToPackageName()
                defaultConfigs {
                    val recaptchaSiteKeyAndroid = gradleLocalProperties(rootDir, rootProject.providers)
                        .getProperty("RECAPTCHA_SITE_KEY_ANDROID")
                        ?: throw IllegalStateException(
                            "Missing RECAPTCHA_SITE_KEY_ANDROID property in local.properties"
                        )

                    val recaptchaSiteKeyIOS = gradleLocalProperties(rootDir, rootProject.providers)
                        .getProperty("RECAPTCHA_SITE_KEY_IOS")
                        ?: throw IllegalStateException(
                            "Missing RECAPTCHA_SITE_KEY_IOS property in local.properties"
                        )


                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaSiteKeyAndroid)
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_IOS", recaptchaSiteKeyIOS)
                }
            }
        }
    }
}