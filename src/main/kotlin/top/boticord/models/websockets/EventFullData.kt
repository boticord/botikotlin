package top.boticord.models.websockets

import kotlinx.serialization.Serializable

@Serializable
public data class EventFullData(
    val event: String,
    val data: EventData
)