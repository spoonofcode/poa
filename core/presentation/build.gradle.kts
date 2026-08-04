plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling)
            implementation(libs.jetbrains.compose.ui.tooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.ui.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }

        commonMain {
            dependencies {
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
                api(libs.jetbrains.compose.material.icons.core)
                api(libs.jetbrains.compose.material.icons.extended)

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
//    generateResClass = always
}
