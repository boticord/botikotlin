package top.boticord.models.servers

import kotlinx.serialization.Serializable
import top.boticord.models.ResourceRating
import top.boticord.models.ResourceStatus

@Serializable
public data class PartialServer(
    val id: String,
    val name: String,
    val shortDescription: String,
    val description: String,
    val avatar: String? = null,
    val shortLink: String? = null,
    val inviteLink: String,
    val standardBannerID: Int,
    val owner: String? = null,
    val status: ResourceStatus,
    val ratings: List<ResourceRating> = emptyList()
)
