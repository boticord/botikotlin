@file:UseContextualSerialization(UserBadge::class)

package data.users

import data.ResourceStatus
import data.SocialTypes
import data.bots.BotProfile
import data.servers.PartialServer
import data.servers.ResourceServer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization

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
