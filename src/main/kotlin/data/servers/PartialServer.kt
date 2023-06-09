package data.servers

import data.ResourceRating
import data.ResourceStatus
import kotlinx.serialization.Serializable

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
