import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CmpLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("convention-kmp-library")
                applyPlugin("compose-compiler")
                applyPlugin("compose-multiplatform")
            }

            dependencies {
                "commonMainApi"(libs.findLibrary("jetbrains-compose-ui").get())
                "commonMainApi"(libs.findLibrary("jetbrains-compose-foundation").get())
                "commonMainApi"(libs.findLibrary("jetbrains-compose-material3").get())
                "commonMainApi"(libs.findLibrary("jetbrains-compose-runtime").get())
                "commonMainApi"(libs.findLibrary("jetbrains-compose-components-resources").get())
                "commonMainApi"(libs.findLibrary("jetbrains-compose-ui-tooling-preview").get())
            }
        }
    }
}
