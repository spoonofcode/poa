plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.ui.compose)
            implementation(compose.uiTooling)
        }

        commonMain {
            dependencies {
                api(compose.ui)
                api(compose.foundation)
                api(compose.material3)
                api(compose.runtime)
                api(compose.components.resources)
                api(compose.preview)
                api(compose.materialIconsExtended)

                api(libs.calf.permissions)
                api(libs.compose.icons.fontawesome)
                api(libs.compottie)
                api(libs.bundles.connectivity)
                api(libs.koin.core)
                api(libs.landscapist.coil)
                api(libs.bundles.lifecycle)
                api(libs.bundles.navigation)

                api(projects.core.logging)
                api(projects.core.data)
                api(projects.feature.appnavigation)
            }
        }

        commonTest {
            dependencies {
                implementation(projects.core.test)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.spoonofcode.core.presentation"
    generateResClass = always
}
