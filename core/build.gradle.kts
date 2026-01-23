
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
    api(libs.ktor.client.core)
    api(libs.ktor.client.cio)
    api(libs.ktor.client.serialization)
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.client.logging)


    testImplementation(kotlin("test"))
    testImplementation(libs.ktor.client.mock)
}

tasks.test {
    useJUnitPlatform()
}

libraryData {
    name = "AbacatePay SDK Core"
    description = "The core of the AbacatePay SDK"
}