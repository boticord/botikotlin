@file:UseContextualSerialization(
    ResourceUp::class,
    PartialUser::class
)

package top.boticord.models.servers

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.models.PremiumData
import top.boticord.models.Resource
import top.boticord.models.ResourceRating
import top.boticord.models.ResourceStatus
import top.boticord.models.bots.ResourceUp
import top.boticord.models.users.PartialUser

@Serializable
public data class ResourceServer(
    val id: String,
    val name: String,
    val shortDescription: String,
    val description: String,
    val avatar: String? = null,
    val shortLink: String? = null,
    val inviteLink: String? = null,
    val premium: PremiumData,
    val standardBannerID: Int,
    val owner: String? = null,
    val status: ResourceStatus,
    val ratings: List<ResourceRating> = emptyList(),
    val createdDate: String,
    val memberCount: Int? = null,
    val website: String? = null,
    val tags: List<ServerTags> = emptyList(),
    val moderators: List<PartialUser> = emptyList(),
    val upCount: Int,
    val ups: List<ResourceUp> = emptyList()
) : Resource()
