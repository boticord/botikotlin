package top.boticord.data.users

import kotlinx.serialization.Serializable

@Serializable
data class UserBadge(val id: Int, val name: String, val assetURL: String)
