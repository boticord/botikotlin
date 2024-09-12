package top.boticord.models

import kotlinx.serialization.Serializable

@Serializable
public data class ResourceRating(val count: Int, val rating: Int)