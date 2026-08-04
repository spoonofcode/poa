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
            implementation(libs.koin.android)
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
                api(libs.compottie.dot)
                api(libs.compottie.network)
                api(libs.connectivity.core)
                api(libs.connectivity.device)

                api(libs.landscapist.coil)
                api(libs.lifecycle.viewmodel.compose)
                api(libs.lifecycle.runtime.compose)

                api(libs.navigation3.runtime)

                api(libs.koin.core)

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
