@file:UseContextualSerialization(
    ResourceUp::class,
    PartialUser::class,
    ResourceRating::class,
    ResourceStatus::class,
    BotLibrary::class
)

package top.boticord.data.meili

import top.boticord.data.bots.BotLibrary
import top.boticord.data.bots.BotTags
import top.boticord.data.bots.ResourceUp
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.data.PremiumData
import top.boticord.data.ResourceRating
import top.boticord.data.ResourceStatus
import top.boticord.data.users.PartialUser

@Serializable
data class MeiliIndexedBot(
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