# How to download it?

Gradle
```gradle
repositories {
    maven(url = uri("https://jitpack.io"))
}

dependencies {
    implementation("com.github.MagM1go:botikotlin:1.0-beta")
}
```

Maven:
```maven
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.MagM1go</groupId>
        <artifactId>botikotlin</artifactId>
        <version>1.0-beta</version>
    </dependency>
</dependencies>
```
# Example
```kotlin
fun main(args: Array<String>) {
    val boticordClient = BotiCordClient("TOKEN")
    
    boticordClient.updateBotStats(
        100000000000000, 
        BotStats(members = 100000)
    ) // And it returns BotProfile? object
}
```

ммм шоколадка...