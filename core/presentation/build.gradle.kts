plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.jetbrains.compose.ui.tooling.preview)
            api(libs.androidx.activity.compose)
            api(libs.androidx.media3.exoplayer)
            api(libs.androidx.media3.ui.compose)
            api(libs.androidx.security)
            api(libs.androidx.splash.screen)
            api(libs.koin.android)
            api(libs.koin.androidx.compose)
            api(libs.ktor.client.okhttp)

            api(libs.androidx.credentials)
            api(libs.androidx.credentials.play.service.auth)
            api(libs.google.id)
            api(libs.recaptcha)
            api(libs.firebase.messaging)
            api(project.dependencies.platform(libs.firebase.bom))
        }

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.jetbrains.compose.components.resources)

                api(libs.compose.icons.fontawesome)
                implementation(libs.compottie)
                implementation(libs.compottie.dot)
                implementation(libs.compottie.network)
                implementation(libs.connectivity.core)
                implementation(libs.connectivity.device)

                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.landscapist.coil)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.material.icons.core)
                implementation(libs.jetbrains.compose.material.icons.extended)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.ui.tooling.preview)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tab.navigator)
                implementation(libs.voyager.transitions)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // TODO #109 Fix after migration to feature modules
                implementation(projects.core.data)

                // TODO #109 Fix after migration to feature modules
                implementation(projects.feature.appnavigation)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
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