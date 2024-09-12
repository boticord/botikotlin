package top.boticord.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ResourceStatus {
    @SerialName("0") Hidden,
    @SerialName("1") Public,
    @SerialName("2") Banned,
    @SerialName("3") Pending
}