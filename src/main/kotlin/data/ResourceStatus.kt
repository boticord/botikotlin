package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ResourceStatus(val code: Int) {
    @SerialName("0") HIDDEN(0),
    @SerialName("1") PUBLIC(1),
    @SerialName("2") BANNED(2),
    @SerialName("3") PENDING(3)
}