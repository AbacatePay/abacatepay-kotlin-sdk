plugins {
    id("maven-publish")
}

val libraryData = extensions.create<PublishingExtension>("libraryData")

publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            groupId = "com.abacatepay"
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            pom {
                description = libraryData.description
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AbacatePay/abacatepay-kotlin-sdk")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}
