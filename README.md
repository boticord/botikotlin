# How to download it?

Gradle
```gradle
dependencies {
    implementation("url")
}
```

Maven:
```maven
idk later
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