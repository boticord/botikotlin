package top.boticord.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SocialTypes {
    @SerialName("vk") VK,
    @SerialName("telegram") TELEGRAM,
    @SerialName("donate") DONATE,
    @SerialName("git") GIT,
    @SerialName("custom") CUSTOM
}