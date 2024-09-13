package top.boticord.models.websockets

import kotlinx.serialization.Serializable

@Serializable
public data class EventPayload(
    val id: String? = null,
    val type: Int? = null,
    val payload: String? = null,
    val affected: String? = null,
    val user: String? = null,
    val happened: Long? = null
)