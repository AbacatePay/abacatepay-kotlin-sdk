
plugins {
    id("java-library")
    id("application")
    kotlin("jvm")
    alias(libs.plugins.kotlin.serialization)
    dokka
    publish
}

dependencies {
    //#ktor
    //api("io.ktor:ktor-client-core-jvm:3.0.0")
    api(libs.ktor.client.core)
    //api("io.ktor:ktor-client-cio-jvm:3.0.0")
    api(libs.ktor.client.cio)
    //api("io.ktor:ktor-serialization-kotlinx-json-jvm:3.0.0")
    api(libs.ktor.client.serialization)
    //api("io.ktor:ktor-client-content-negotiation-jvm:3.0.0")
    api(libs.ktor.client.content.negotiation)

    api(libs.ktor.client.logging)


    //testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation(kotlin("test"))
    //testImplementation("io.ktor:ktor-client-mock:3.0.0")
    testImplementation(libs.ktor.client.mock)
}

tasks.test {
    useJUnitPlatform()
}

libraryData {
    name = "AbacatePay SDK Core"
    description = "The core of the AbacatePay SDK"
}