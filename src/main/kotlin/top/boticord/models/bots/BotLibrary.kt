package top.boticord.models.bots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class BotLibrary {
    @SerialName("0") NotChosen,
    @SerialName("1") Discord4J,
    @SerialName("2") Discordcr,
    @SerialName("3") DiscordGO,
    @SerialName("4") Discordoo,
    @SerialName("5") DSharpPlus,
    @SerialName("6") DiscordJs,
    @SerialName("7") DiscordNET,
    @SerialName("8") DiscordPy,
    @SerialName("9") Eris,
    @SerialName("10") JavaCord,
    @SerialName("11") JDA,
    @SerialName("12") Other
}