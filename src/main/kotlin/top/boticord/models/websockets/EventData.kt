package top.boticord.models.websockets

import kotlinx.serialization.Serializable

@Serializable
public data class EventData(
    val type: String? = null,
    val payload: Map<String, String>? = null,
    val id: String? = null,
    val user: String? = null,
    val happened: Long? = null,
    val webhookSettings: WebhookSettings? = null,
)