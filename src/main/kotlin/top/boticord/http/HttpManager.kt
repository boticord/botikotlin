package top.boticord.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import top.boticord.http.exceptions.BoticordException
import top.boticord.http.exceptions.HttpException
import top.boticord.models.ErrorDetail

internal class HttpManager(private val boticordToken: String?) {
    companion object {
        private const val INTERVAL = 30_000L
        private const val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 OPR/115.0.0.0 (Edition developer)"
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(WebSockets)
        install(HttpTimeout)
    }

    private fun format(
        template: String,
        map: Map<String, String>
    ): String = map.entries.fold(template) { acc, entry ->
        acc.replace("{${entry.key}}", entry.value)
    }

    @Throws(BoticordException::class)
    internal suspend fun request(
        route: Route,
        headers: HeadersBuilder.() -> Unit = {},
        body: Body.() -> Unit = {}
    ): HttpResponse {
        val builtBody = Body().apply(body)

        val url = if (builtBody.parameters.isNotEmpty()) {
            format(route.url, builtBody.parameters)
        } else {
            route.url
        }

        val response = client.request(url) {
            this.userAgent(USER_AGENT)
            this.contentType(ContentType.Application.Json)

            if (!boticordToken.isNullOrEmpty()) {
                header("Authorization", boticordToken)
            }

            this.method = route.method
            this.headers(headers)

            val data = builtBody.asStringifiedJson()
            if (!data.isNullOrEmpty()) setBody(data)
        }

        response.contentType()?.let {
            if (!it.match(ContentType.Application.Json)) {
                throw HttpException("Boticord respond with invalid content type (${response.contentType()})")
            }
        }

        val jsonBody = Json.decodeFromString<JsonObject>(response.bodyAsText())
        if (jsonBody.containsKey("errors"))
            throw BoticordException(
                "Boticord respond with error(s):\n" +
                        parseApiErrors(jsonBody["errors"]!!.jsonArray).joinToString("\n") {
                            "Boticord Message: ${it.code} - ${it.message}"
                        }
            )

        return response
    }

    internal suspend fun websocket(
        method: HttpMethod,
        host: String,
        path: String,
        block: suspend DefaultClientWebSocketSession.() -> Unit = {}
    ) = client.wss(method, host, path = path) { block() }

    private fun parseApiErrors(errors: JsonArray): List<ErrorDetail> =
        errors.map { Json.decodeFromString<ErrorDetail>(it.toString()) }
}