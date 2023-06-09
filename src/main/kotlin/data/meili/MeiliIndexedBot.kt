@file:UseContextualSerialization(
    ResourceUp::class,
    PartialUser::class,
    ResourceRating::class,
    ResourceStatus::class,
    BotLibrary::class
)

package data.meili

import data.PremiumData
import data.ResourceRating
import data.ResourceStatus
import data.bots.BotLibrary
import data.bots.BotTags
import data.bots.ResourceUp
import data.users.PartialUser
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization

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