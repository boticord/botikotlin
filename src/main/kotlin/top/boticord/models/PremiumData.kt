package top.boticord.models

import kotlinx.serialization.Serializable

@Serializable
public data class PremiumData(
    val active: Boolean,
    val splashURL: String? = null,
    val autoFetch: Boolean,
    val bannerURL: String? = null
)
