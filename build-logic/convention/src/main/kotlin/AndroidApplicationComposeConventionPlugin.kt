import com.android.build.api.dsl.ApplicationExtension
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin("convention-android-application")
            applyPlugin("compose-compiler")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}
