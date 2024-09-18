plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.serialization)
    `maven-publish`
    application
}

group = "top.boticord"
version = "2.1.2.5"

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

            from(components["kotlin"])
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