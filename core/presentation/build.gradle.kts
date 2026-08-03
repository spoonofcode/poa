plugins {
    alias(libs.plugins.convention.cmp.library)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling)
            implementation(libs.jetbrains.compose.ui.tooling)
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
                api(libs.kotlin.stdlib)
                api(libs.jetbrains.compose.components.resources)

                api(libs.calf.permissions)
                api(libs.compose.icons.fontawesome)
                api(libs.compottie)
                api(libs.compottie.dot)
                api(libs.compottie.network)
                api(libs.connectivity.core)
                api(libs.connectivity.device)

                api(libs.jetbrains.compose.foundation)
                api(libs.landscapist.coil)
                api(libs.lifecycle.viewmodel.compose)
                api(libs.lifecycle.runtime.compose)
                api(libs.jetbrains.compose.runtime)
                api(libs.jetbrains.compose.material.icons.core)
                api(libs.jetbrains.compose.material.icons.extended)
                api(libs.jetbrains.compose.material3)
                api(libs.jetbrains.compose.ui)
                api(libs.jetbrains.compose.ui.tooling.preview)

                api(libs.navigation3.runtime)
                api(libs.navigation3.ui)

                api(libs.koin.core)
                api(libs.koin.compose)
                api(libs.koin.compose.viewmodel)

                // TODO #132 Optimize dependencies
                api(projects.core.data)

                // TODO #132 Optimize dependencies
                api(projects.feature.appnavigation)
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