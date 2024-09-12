@file:UseContextualSerialization(UserBadge::class)

package top.boticord.models.users

import top.boticord.models.bots.BotProfile
import top.boticord.models.servers.PartialServer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import top.boticord.models.Resource
import top.boticord.models.SocialTypes

@Serializable
public data class UserProfile(
    val username: String,
    val discriminator: String,
    val avatar: String? = null,
    val id: String,
    val badges: List<UserBadge> = emptyList(),
    val bots: List<BotProfile>,
    val servers: List<PartialServer>,
    val socials: Map<SocialTypes, String?>?,
    val description: String? = null,
    val shortDescription: String? = null,
    val status: String? = null,
    val shortDomain: String? = null
) : Resource() {
    override fun toString(): String {
        return "UserProfile(" +
                "username=$username, " +
                "id=$id, " +
                "badges=$badges, " +
                "shortDescription=$shortDescription, " +
                "status=$status" +
                ")"
    }
}