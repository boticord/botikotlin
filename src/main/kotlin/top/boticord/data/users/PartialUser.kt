package top.boticord.data.users

import kotlinx.serialization.Serializable
import top.boticord.data.SocialTypes

@Serializable
data class PartialUser(
    val username: String,
    val discriminator: String,
    val avatar: String? = null,
    val id: String,
    val socials: Map<SocialTypes, String?>,
    val description: String? = null,
    val shortDescription: String? = null,
    val status: String? = null,
    val shortDomain: String? = null
)
