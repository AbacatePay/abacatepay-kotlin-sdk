plugins{
    kotlin("jvm") apply false
    dokka
}

dependencies {
    rootProject.subprojects.filter { it.name !in listOf("docs") }.forEach {
        dokka(project(":" + it.name))
    }
}