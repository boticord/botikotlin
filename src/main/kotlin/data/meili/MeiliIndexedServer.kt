
package data.meili

import data.PremiumData
import data.servers.ServerTags
import kotlinx.serialization.Serializable

@Serializable
data class MeiliIndexedServer(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val description: String,
    val avatar: String? = null,
    val invite: String? = null,
    val premium: PremiumData,
    val discordBanner: String?,
    val banner: Int,
    val rating: Int,
    val created: Int,
    val members: Int? = null,
    val tags: List<ServerTags>,
    val ups: Int
)
