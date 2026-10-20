plugins {
    alias(libs.plugins.convention.kmp.domain)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.session.domain)
                implementation(projects.core.firebase.domain)
                implementation(projects.feature.notification.domain)
            }
        }
        commonTest {
            dependencies {
                implementation(projects.core.test)
                implementation(projects.feature.product.dataTest)
                implementation(projects.feature.notification.dataTest)
                implementation(projects.core.firebase.dataTest)
            }
        }
    }
}
