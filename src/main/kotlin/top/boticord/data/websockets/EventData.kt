package top.boticord.data.websockets

import kotlinx.serialization.Serializable

@Serializable
data class EventData(
    val id: String,
    val type: Int,
    val payload: String,
    val affected: String,
    val user: String,
    val happened: Long
)