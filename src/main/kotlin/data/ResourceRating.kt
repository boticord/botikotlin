package data

import kotlinx.serialization.Serializable

@Serializable
data class ResourceRating(val count: Int, val rating: Int)