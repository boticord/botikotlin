package top.boticord.models.users

import kotlinx.serialization.Serializable
import top.boticord.models.SocialTypes

@Serializable
public data class PartialUser(
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
