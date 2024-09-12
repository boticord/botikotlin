package top.boticord.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SocialTypes {
    @SerialName("vk") Vk,
    @SerialName("telegram") Telegram,
    @SerialName("donate") Donate,
    @SerialName("git") Git,
    @SerialName("custom") Custom
}