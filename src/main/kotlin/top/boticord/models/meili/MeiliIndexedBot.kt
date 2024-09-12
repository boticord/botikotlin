@file:UseContextualSerialization(
    ResourceUp::class,
    PartialUser::class,
    ResourceRating::class,
    ResourceStatus::class,
    BotLibrary::class
)

package top.boticord.models.meili

import top.boticord.models.bots.BotLibrary
import top.boticord.models.bots.BotTags
import top.boticord.models.bots.ResourceUp
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.models.PremiumData
import top.boticord.models.ResourceRating
import top.boticord.models.ResourceStatus
import top.boticord.models.users.PartialUser

@Serializable
public data class MeiliIndexedBot(
    val id: String,
    val name: String,
    val shortDescription: String,
    val description: String,
    val avatar: String? = null,
    val invite: String? = null,
    val premium: PremiumData,
    val created: Int,
    val members: Int? = null,
    val tags: List<BotTags>,
    val ups: Int? = null,
    val discriminator: String,
    val guilds: Int? = null,
    val shards: Int? = null,
    val rating: Int,
    val banner: Int
)