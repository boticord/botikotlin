package data

import kotlinx.serialization.Serializable

@Serializable
data class InternalServerErrorData(val code: BotiCordErrors, val message: String)
