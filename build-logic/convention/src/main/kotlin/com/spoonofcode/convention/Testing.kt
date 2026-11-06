package com.spoonofcode.convention

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

internal fun Project.configureTesting() {
    tasks.withType<Test>().configureEach {
        // Run test classes in parallel across multiple JVM worker processes
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)

        // Enable JUnit 5 parallel test execution within the JVM process
        systemProperty("junit.jupiter.execution.parallel.enabled", "true")
        systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
        systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
        systemProperty("junit.jupiter.execution.parallel.config.strategy", "dynamic")
    }
}
