package top.boticord.models.users

import kotlinx.serialization.Serializable

@Serializable
public data class UserBadge(val id: Int, val name: String, val assetURL: String)
