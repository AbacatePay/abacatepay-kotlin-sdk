plugins {
    // The Kotlin DSL plugin provides a convenient way to develop convention plugins.
    // Convention plugins are located in `src/main/kotlin`, with the file extension `.gradle.kts`,
    // and are applied in the project's `build.gradle.kts` files as required.
    `kotlin-dsl`
    alias(libs.plugins.kotlin.serialization)

}

kotlin {
    jvmToolchain(11)
}

dependencies {
    // Add a dependency on the Kotlin Gradle plugin, so that convention plugins can apply it.
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.dokka)
    implementation(libs.publisher)

}

repositories {
    gradlePluginPortal()
    mavenCentral()
}
