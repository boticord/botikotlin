package top.boticord.models

import kotlinx.serialization.Serializable

@Serializable
public data class ErrorDetail(
    val code: Int,
    val message: String
)