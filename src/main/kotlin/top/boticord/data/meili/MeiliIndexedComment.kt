package top.boticord.data.meili

import kotlinx.serialization.Serializable

@Serializable
data class MeiliIndexedComment(
    val id: Int,
    val author: Int,
    val content: String,
    val rating: Int,
    val resource: String,
    val created: String,
    val modReply: String
)