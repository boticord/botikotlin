package top.boticord.data.websockets

import kotlinx.serialization.Serializable

@Serializable
data class EventFullData(
    val event: String,
    val data: EventData
)