# botikotlin

![botikotlin-banner](https://media.discordapp.net/attachments/1052589926119641098/1222579608361107538/image.png?ex=6616bafd&is=660445fd&hm=c481c590aa338a19540b8bfc24d478b341d24dec1b4dcbf2802cffe1db3ace33&=&format=webp&quality=lossless&width=1366&height=663)

## [Docs](https://magmigo2.gitbook.io/boticord-kotlin/) ㆍ [Examples](https://github.com/MagM1go/botikotlin/tree/main/examples)

# Get started

> [!NOTE]\
> If you want to use a specific version of a library, replace the version number with a commit hash.  
> For example, instead of using `1.0-beta` (or another version), use `com.github.boticord:botikotlin:COMMIT-HASH`.


Gradle
```groovy
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.boticord:botikotlin:main-SNAPSHOT")
}
```

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.boticord</groupId>
        <artifactId>botikotlin</artifactId>
        <version>main-SNAPSHOT</version>
    </dependency>
</dependencies>
```
# Example
**Update bot statistics**
```kotlin
val response = boticord(token) {
    update(BOT_ID, memberCount?, shardCount?, guildCount?)
}

logger.info(response)
```

**Get user/bot**
```kotlin
val api = boticord(token)
val response = api.fetch(USER_ID, Type.User/Bot)

logger.info(response)

// or
val response = boticord(token) {
    fetch(USER_ID, Type.User/Bot)
}

logger.info(response)
```

**Listen to boticord notifications**
```kotlin
boticord(token) {
    notifications { event ->
        println("I received event!!!!! $event")
    }
}
```