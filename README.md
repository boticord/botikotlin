# botikotlin

![botikotlin-banner](https://media.discordapp.net/attachments/1052589926119641098/1222579608361107538/image.png?ex=6616bafd&is=660445fd&hm=c481c590aa338a19540b8bfc24d478b341d24dec1b4dcbf2802cffe1db3ace33&=&format=webp&quality=lossless&width=1366&height=663)

## [Docs](https://magmigo2.gitbook.io/boticord-kotlin/) ㆍ [Examples](https://github.com/MagM1go/botikotlin/tree/main/examples)

# Get started

If you want to download older version, try to replace "1.0-beta (or whatever)" with a commit hash, like com.github.MagM1go:botikotlin:xxxxxx

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
