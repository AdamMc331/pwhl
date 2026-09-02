import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.kmp.library).apply(false)
    alias(libs.plugins.apollo.graphql).apply(false)
    alias(libs.plugins.buildKonfig).apply(false)
    alias(libs.plugins.cash.burst).apply(false)
    alias(libs.plugins.cash.sqldelight).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.detekt).apply(true) // Needs to be applied at the root, unlike others.
    alias(libs.plugins.google.devtools.ksp).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlinter).apply(false)
    alias(libs.plugins.screenshot).apply(false)
    alias(libs.plugins.square.sortDependencies).apply(false)
}

apply(from = "buildscripts/githooks.gradle")

subprojects {
    apply(plugin = "com.squareup.sort-dependencies")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jmailen.kotlinter")
}

dependencies {
    // Has to be at the root, since detekt is applied at the root
    // https://github.com/detekt/detekt/issues/3989#issuecomment-890331512
    detektPlugins(project(":detekt-rules"))
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

tasks {
    /**
     * The detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    @Suppress("UnusedPrivateMember")
    val detektAll by registering(Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
        dependsOn(":detekt-rules:assemble")
    }
}

/**
 * The most basic list of validations so we can run checks locally when CI isn't running,
 * given this is a private project and has limited Actions resources.
 */
tasks.register<GradleBuild>("prChecks") {
    tasks = listOf(
        "assembleDebug",
        "linkDebugFrameworkIosSimulatorArm64",
        "detektAll",
        "lintKotlin",
        "test",
    )
}
