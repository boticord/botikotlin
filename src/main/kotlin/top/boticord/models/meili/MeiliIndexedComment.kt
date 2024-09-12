package top.boticord.models.meili

import kotlinx.serialization.Serializable

@Serializable
public data class MeiliIndexedComment(
    val id: Int,
    val author: Int,
    val content: String,
    val rating: Int,
    val resource: String,
    val created: String,
    val modReply: String
)