plugins {
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
    id("maven-publish")
    kotlin("jvm") version "2.3.0"
}

group = property("maven_group") as String
version = property("mod_version") as String

base {
    archivesName = property("archives_base_name") as String
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.terraformersmc.com/")
}

loom {
    runs.named("client") {
        client()
        ideConfigGenerated(true)
        runDir = "run/"
        environment = "client"
        programArgs("--username=Dev")
        configName = "Fabric Client"
    }
    runs.named("server") {
        server()
        ideConfigGenerated(true)
        runDir = "run/"
        environment = "server"
        configName = "Fabric Server"
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")

    modImplementation("com.terraformersmc:modmenu:${property("modmenu_version")}")

    implementation("de.greenman999:layr-api:1.0-SNAPSHOT") { isChanging = true }
    modImplementation("de.greenman999:layr:0.1.0-alpha.1+1.21.11-fabric") { isChanging = true }
    implementation(project(":"))
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

kotlin {
    jvmToolchain(21)
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    inputs.property("archivesName", project.base.archivesName.orNull ?: project.name)

    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.orNull ?: project.name}" }
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}