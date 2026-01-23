import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    group = "com.abacatepay"
    version = "0.0.5"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(11)
    }
}
