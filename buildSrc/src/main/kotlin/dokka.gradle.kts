import CommonResources.REPO_URL

plugins {
    id("org.jetbrains.dokka")
}

dokka {
    moduleName = if (project.name != "docs") project.name else "AbacatePay Kotlin SDK"
    dokkaSourceSets.configureEach {
        sourceRoots.setFrom(project.projectDir.resolve("src/$name/kotlin"))
        sourceLink {
            localDirectory.set(file("src/$name/kotlin"))
            remoteUrl("$REPO_URL/blob/master/${project.name}/src/$name/kotlin")
            remoteLineSuffix.set("#L")
        }
    }

    pluginsConfiguration.html {
        customAssets.from(rootDir.resolve("assets/logo-icon.png"))
        footerMessage.set("AbacatePay Kotlin SDK")
        homepageLink.set(REPO_URL)
    }
}