plugins {
    alias(libs.plugins.convention.cmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.jetbrains.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.ui.compose)
            implementation(libs.androidx.security)
            implementation(libs.androidx.splash.screen)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)

            implementation(libs.firebase.messaging)
            implementation(project.dependencies.platform(libs.firebase.bom))
        }

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.jetbrains.compose.components.resources)

                implementation(libs.calf.permissions)
                implementation(libs.compose.icons.fontawesome)
                implementation(libs.compottie)
                implementation(libs.compottie.dot)
                implementation(libs.compottie.network)
                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)

                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.landscapist.coil)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.lifecycle.runtime.compose)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.material.icons.core)
                implementation(libs.jetbrains.compose.material.icons.extended)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.ui.tooling.preview)

                implementation(libs.navigation3.runtime)
                implementation(libs.navigation3.ui)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // TODO #132 Optimize dependencies
                implementation(projects.core.data)

                // TODO #132 Optimize dependencies
                implementation(projects.feature.appnavigation)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.koin.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.kotlin.test)
                implementation(libs.turbine)
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