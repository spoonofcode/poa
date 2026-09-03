plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
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

                api(libs.koin.core)
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
    packageOfResClass = "com.spoonofcode.core.designsystem"
    generateResClass = always
}
