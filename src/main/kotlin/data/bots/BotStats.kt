package data.bots

import kotlinx.serialization.Serializable

@Serializable
data class BotStats(
    var members: Int? = null,
    var shards: Int? = null,
    var guilds: Int? = null
)