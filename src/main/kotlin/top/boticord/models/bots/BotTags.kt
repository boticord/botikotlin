package top.boticord.models.bots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class BotTags {
    @SerialName("0") Moderation,
    @SerialName("1") Combine,
    @SerialName("2") Utilities,
    @SerialName("3") Fun,
    @SerialName("4") Music,
    @SerialName("5") Economy,
    @SerialName("6") Logs,
    @SerialName("7") Levels,
    @SerialName("8") NSFW,
    @SerialName("9") Settings,
    @SerialName("10") Roleplay,
    @SerialName("11") Memes,
    @SerialName("12") Games,
    @SerialName("13") AI
}