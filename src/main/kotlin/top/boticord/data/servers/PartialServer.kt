package top.boticord.data.servers

import kotlinx.serialization.Serializable
import top.boticord.data.ResourceRating
import top.boticord.data.ResourceStatus

@Serializable
data class PartialServer(
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
