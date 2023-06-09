package data.users

import data.SocialTypes
import kotlinx.serialization.Serializable

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
