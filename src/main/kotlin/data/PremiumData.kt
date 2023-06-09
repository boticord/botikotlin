package data

import kotlinx.serialization.Serializable

@Serializable
data class PremiumData(
    val active: Boolean,
    val splashURL: String? = null,
    val autoFetch: Boolean,
    val bannerURL: String? = null
)
