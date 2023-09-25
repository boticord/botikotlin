
package top.boticord.data.meili

import kotlinx.serialization.Serializable
import top.boticord.data.PremiumData
import top.boticord.data.servers.ServerTags

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
