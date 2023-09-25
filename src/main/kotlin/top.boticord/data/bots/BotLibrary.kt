package top.boticord.data.bots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BotLibrary(val code: Int) {
    @SerialName("0") NOT_CHOSEN(0),
    @SerialName("1") Discord4J(1),
    @SerialName("2") Discordcr(2),
    @SerialName("3") DiscordGO(3),
    @SerialName("4") Discordoo(4),
    @SerialName("5") DSharpPlus(5),
    @SerialName("6") DiscordJs(6),
    @SerialName("7") DiscordNET(7),
    @SerialName("8") DiscordPy(8),
    @SerialName("9") Eris(9),
    @SerialName("10") JavaCord(10),
    @SerialName("11") JDA(11),
    @SerialName("12") Other(12)
}