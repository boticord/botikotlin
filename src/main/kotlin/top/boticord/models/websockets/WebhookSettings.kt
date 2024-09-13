package top.boticord.models.websockets

import kotlinx.serialization.Serializable

@Serializable
public data class WebhookSettings(
    val enabled: Boolean? = null,
    val headers: Map<String, String>? = null,
    val url: String? = null,
    val token: String? = null
)