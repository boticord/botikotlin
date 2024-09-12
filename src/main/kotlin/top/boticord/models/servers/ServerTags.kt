package top.boticord.models.servers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ServerTags {
    @SerialName("130") Chatting,
    @SerialName("131") Fun,
    @SerialName("132") Games,
    @SerialName("133") Movies,
    @SerialName("134") Anime,
    @SerialName("135") Art,
    @SerialName("136") Programming,
    @SerialName("137") Music,
    @SerialName("138") NSFW,
    @SerialName("139") RolePlay,
    @SerialName("140") Humor,
    @SerialName("160") GenshinImpact,
    @SerialName("161") Minecraft,
    @SerialName("162") GTA,
    @SerialName("163") CounterStrike,
    @SerialName("164") Dota,
    @SerialName("165") AmongUs,
    @SerialName("166") Fortnite,
    @SerialName("167") BrawlStars
}