package top.boticord.models.bots

import kotlinx.serialization.Serializable

@Serializable
public data class BotStats(
    var members: Int? = null,
    var shards: Int? = null,
    var guilds: Int? = null
)