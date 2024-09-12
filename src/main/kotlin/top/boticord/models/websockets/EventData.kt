package top.boticord.models.websockets

import kotlinx.serialization.Serializable

@Serializable
public data class EventData(
    val id: String,
    val type: Int,
    val payload: String,
    val affected: String,
    val user: String,
    val happened: Long
)