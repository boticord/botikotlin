package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.annotations.ApiStatus

class HttpManager(val boticordToken: String?, val devApiUrl: Boolean) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }

        install(WebSockets) {
            pingInterval = 20000
        }
    }

    private val url: String = if (devApiUrl) Route.DEV_API_URL.path else Route.API_URL.path
    private var webSocketResponse: String? = null

    suspend fun sendRequest(httpMethod: HttpMethod, data: (() -> JsonObject)?, route: String?, authorization: String?): HttpResponse {
        return client.request(url + route) {
            method = httpMethod
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }

            when (method) {
                HttpMethod.Post -> {
                    headers {
                        append(HttpHeaders.Authorization, authorization!!)
                    }
                    contentType(ContentType.Application.Json)
                    if (data != null) {
                        setBody(data())
                    }
                }
            }
        }
    }

    @ApiStatus.Experimental
    fun receiveWebsocketMessage(block: (message: String?) -> Unit) {
        val websocketUrl = Route.WEBSOCKET_BASE_URL.path

        // Hello u-u o/
        val helloJson = JsonObject(mapOf(
            "event" to JsonPrimitive("auth"),
            "data" to JsonObject(mapOf(
                "token" to JsonPrimitive(boticordToken)
            ))
        ))

        runBlocking {
            client.webSocket(host = websocketUrl, path = Route.WEBSOCKET_PATH.path) {
                send(helloJson.toString())

                val message = (incoming.receive() as? Frame.Text)?.readText()
                block(message)
            }
        }
    }
}