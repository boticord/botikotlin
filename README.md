# Docs & examples
[Documentation](https://magmigo2.gitbook.io/boticord-kotlin/)

[Examples](https://github.com/MagM1go/botikotlin/tree/main/examples)

# How to download it?

Мне лень писать что-то на английском.

Or if you want to download older version, try to replace "1.0-beta (or whatever)" with a commit hash, like com.github.MagM1go:botikotlin:xxxxxx

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