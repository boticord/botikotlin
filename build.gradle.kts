plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.serialization)
    `maven-publish`
    application
}

apply(plugin = "kotlin")
apply(plugin = "maven-publish")

group = "top.boticord"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(libs.bundles.ktor.client)
    implementation(libs.bundles.kotlin.coroutines)
    implementation(libs.bundles.kotlin.serialization)

    implementation(libs.meili.search)
    implementation(libs.logging)
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

application {
    mainClass.set("top.boticord.BotiCordClient")
}

kotlin {
    explicitApi()

    jvmToolchain(21)
}