import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.pathToPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure

class BuildKonfigConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin("buildkonfig")

            val localProperties = gradleLocalProperties(rootDir, rootProject.providers)

            val recaptchaDev = localProperties.getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_DEV")
                ?: throw IllegalStateException(
                    "Missing RECAPTCHA_SITE_KEY_ANDROID_POA_DEV property in local.properties"
                )

            val recaptchaStage = localProperties.getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_STAGE")
                ?: throw IllegalStateException(
                    "Missing RECAPTCHA_SITE_KEY_ANDROID_POA_STAGE property in local.properties"
                )

            val recaptchaProd = localProperties.getProperty("RECAPTCHA_SITE_KEY_ANDROID_POA_PROD")
                ?: throw IllegalStateException(
                    "Missing RECAPTCHA_SITE_KEY_ANDROID_POA_PROD property in local.properties"
                )

            val recaptchaIOS = localProperties.getProperty("RECAPTCHA_SITE_KEY_IOS")
                ?: throw IllegalStateException(
                    "Missing RECAPTCHA_SITE_KEY_IOS property in local.properties"
                )

            val recaptchaWeb = localProperties.getProperty("RECAPTCHA_SITE_KEY_WEB")
                ?: throw IllegalStateException(
                    "Missing RECAPTCHA_SITE_KEY_WEB property in local.properties"
                )

            val googleIdServerClientId = localProperties.getProperty("GOOGLE_ID_SERVER_CLIENT_ID_WEB")
                ?: throw IllegalStateException(
                    "Missing GOOGLE_ID_SERVER_CLIENT_ID_WEB property in local.properties"
                )

            extensions.configure<BuildKonfigExtension> {
                packageName = target.pathToPackageName()

                defaultConfigs {
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaDev)
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_IOS", recaptchaIOS)
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_WEB", recaptchaWeb)
                    buildConfigField(Type.STRING, "GOOGLE_ID_SERVER_CLIENT_ID_WEB", googleIdServerClientId)
                }

                defaultConfigs("development") {
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaDev)
                }

                defaultConfigs("staging") {
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaStage)
                }

                defaultConfigs("production") {
                    buildConfigField(Type.STRING, "RECAPTCHA_SITE_KEY_ANDROID", recaptchaProd)
                }
            }
        }
    }
}
