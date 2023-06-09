package data.bots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BotTags(val code: Int) {
    @SerialName("0") Moderation(0),
    @SerialName("1") Combine(1),
    @SerialName("2") Utilities(2),
    @SerialName("3") Fun(3),
    @SerialName("4") Music(4),
    @SerialName("5") Economy(5),
    @SerialName("6") Logs(6),
    @SerialName("7") Levels(7),
    @SerialName("8") NSFW(8),
    @SerialName("9") Settings(9),
    @SerialName("10") Roleplay(10),
    @SerialName("11") Memes(11),
    @SerialName("12") Games(12),
    @SerialName("13") AI(13)
}