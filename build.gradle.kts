plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.buildkonfig) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.cocoapods) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.mokkery) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

subprojects {
    tasks.matching {
        it.name.startsWith("podGen") || it.name.startsWith("podSetupBuild") || it.name.startsWith("podInstall")
    }.configureEach {
        doLast {
            val syntheticPodfile = file("build/cocoapods/synthetic/ios/Podfile")
            if (syntheticPodfile.exists()) {
                val content = syntheticPodfile.readText()
                if (content.contains("version = \"#{12}.#{0}\"") || content.contains("deployment_target_major < 12")) {
                    val newContent = content
                        .replace("version = \"#{12}.#{0}\"", "version = \"#{15}.#{0}\"")
                        .replace("deployment_target_major < 12", "deployment_target_major < 15")
                        .replace("deployment_target_major == 12", "deployment_target_major == 15")
                    syntheticPodfile.writeText(newContent)

                    ProcessBuilder("pod", "install")
                        .directory(file("build/cocoapods/synthetic/ios"))
                        .inheritIO()
                        .start()
                        .waitFor()
                }
            }
        }
    }
}