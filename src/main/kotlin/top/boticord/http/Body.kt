package top.boticord.http

import kotlinx.serialization.json.*

internal class Body {
    private var body: JsonObject? = null
    internal var parameters: MutableMap<String, String> = mutableMapOf()

    internal fun asStringifiedJson(): String? = body?.toString()

    internal fun body(block: JsonObjectBuilder.() -> Unit) {
        body = buildJsonObject(block)
    }

    internal fun params(vararg parameter: Pair<String, String>) =
        parameter.forEach { parameters[it.first] = it.second }
}