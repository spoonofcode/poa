import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureComposeMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project

class CmpPresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPlugin("convention-kmp-library")
            }

            configureComposeMultiplatform()
        }
    }
}
