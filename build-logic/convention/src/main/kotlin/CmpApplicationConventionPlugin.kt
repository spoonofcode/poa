import com.spoonofcode.convention.applyHierarchyTemplate
import com.spoonofcode.convention.applyPlugin
import com.spoonofcode.convention.configureAndroidTarget
import com.spoonofcode.convention.configureIosTargets
import com.spoonofcode.convention.pathToFrameworkName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugin("convention-android-application-compose")
            applyPlugin("kotlin-multiplatform")
            applyPlugin("compose-multiplatform")
            applyPlugin("compose-compiler")
            applyPlugin("kotlin-serialization")

            configureAndroidTarget()
            configureIosTargets(baseName = pathToFrameworkName())

            extensions.configure<KotlinMultiplatformExtension> {
                applyHierarchyTemplate()
            }
        }
    }
}
