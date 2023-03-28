plugins {
    kotlin("jvm") version "1.5.21"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("io.github.monun:tap-api:4.1.6")
    compileOnly("io.github.monun:kommand-api:2.6.4")
}

tasks {
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
        filteringCharset = "UTF-8"
    }
    jar {
        archiveClassifier.set("")
        archiveVersion.set("")
    }
    create<Copy>("dist") {
        from (jar)
        into(".\\.server\\plugins")
    }
}
