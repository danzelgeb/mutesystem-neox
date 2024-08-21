plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "de.danzel34"
version = "0.1-alpha"


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    compileOnly("com.mojang:authlib:3.13.56")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("org.projectlombok:lombok:1.18.34")
    implementation("commons-codec:commons-codec:1.17.1")
    implementation("org.mongodb:mongodb-driver-sync:5.1.0")
    annotationProcessor("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        minimize()
        relocate("co.aikar", "net.verdox.lib")
    }

    compileJava {
        options.encoding = "UTF-8"
    }
}