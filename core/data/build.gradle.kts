plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters")
    }

    sourceSets {
        commonMain {
            dependencies {
                // TODO #132 Optimize dependecies
                api(libs.kotlinx.datetime)
                api(libs.ktor.client.core)

                implementation(libs.kotlin.stdlib)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.kotlinx.serialization.json)

                implementation(libs.touchlab.kermit)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.security)
                implementation(libs.ktor.client.okhttp)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }

}
