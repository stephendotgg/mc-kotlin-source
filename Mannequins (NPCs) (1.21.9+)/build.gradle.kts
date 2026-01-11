import io.papermc.paperweight.userdev.ReobfArtifactConfiguration
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "9.3.0"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "gg.stephen"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

paperweight {
    reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val props = mapOf("version" to project.version)
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    runServer {
        minecraftVersion("1.21.11")
    }

    shadowJar {
        archiveClassifier.set("")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}