import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization") version "1.4.21"
    kotlin("jvm") version "1.7.20"
    `maven-publish`
    application
}

apply(plugin = "kotlin")
apply(plugin = "maven-publish")

group = "top.boticord"
version = "1.0-SNAPSHOT"

val ktor_version = "2.3.1"
val slf4j_version = "2.0.7"
val kotlin_coro_core = "1.7.1"

repositories {
    mavenCentral()
    maven(url = uri("https://jitpack.io"))
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // ktor
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // slf4j
    implementation("org.slf4j:slf4j-api:${slf4j_version}")
    implementation("org.slf4j:slf4j-simple:${slf4j_version}")

    // async kotlin omg wtf ?!?!??!?!
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_coro_core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coro_core")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "top.boticord"
            artifactId = "botikotlin"
            version = version

            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("top.boticord.BotiCordClient")
}