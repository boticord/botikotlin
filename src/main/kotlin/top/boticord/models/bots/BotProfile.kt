@file:UseContextualSerialization(
    ResourceUp::class,
    PartialUser::class,
    ResourceRating::class,
    ResourceStatus::class,
    BotLibrary::class
)

package top.boticord.models.bots

import top.boticord.models.users.PartialUser
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.models.PremiumData
import top.boticord.models.Resource
import top.boticord.models.ResourceRating
import top.boticord.models.ResourceStatus

@Serializable
public data class BotProfile(
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
    val ratings: List<ResourceRating>,
    val createdDate: String,
    val members: Int? = null,
    val website: String? = null,
    val tags: List<BotTags>,
    val upCount: Int? = null,
    val prefix: String,
    val discriminator: String,
    val supportServerInviteCode: String? = null,
    val library: BotLibrary? = null,
    val guilds: Int? = null,
    val shards: Int? = null,
    val developers: List<PartialUser>? = null,
) : Resource()