@file:UseContextualSerialization(UserBadge::class)

package top.boticord.data.users

import top.boticord.data.bots.BotProfile
import top.boticord.data.servers.PartialServer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.data.SocialTypes

@Serializable
data class UserProfile(
    val username: String,
    val discriminator: String,
    val avatar: String? = null,
    val id: String,
    val badges: List<UserBadge> = emptyList(),
    val bots: List<BotProfile>,
    val servers: List<PartialServer>,
    val socials: Map<SocialTypes, String?>,
    val description: String? = null,
    val shortDescription: String? = null,
    val status: String? = null,
    val shortDomain: String? = null
)
