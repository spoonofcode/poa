import com.android.build.api.dsl.LibraryExtension
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.pathToPackageName
import com.spoonofcode.convention.pathToResourcePrefix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmpAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("convention-kmp-library")
            }

            extensions.configure<LibraryExtension> {
                namespace = this@with.pathToPackageName()
                resourcePrefix = this@with.pathToResourcePrefix()

                // Required to make debug build of app run in iOS simulator
                experimentalProperties["android.experimental.kmp.enableAndroidResources"] = "true"
            }
        }
    }
}
