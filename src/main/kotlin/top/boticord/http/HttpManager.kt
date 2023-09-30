package top.boticord.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.annotations.ApiStatus

class HttpManager(private val boticordToken: String?, apiUrl: String? = Route.API_URL.path) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }

        install(WebSockets) {
            pingInterval = 20000
        }
    }

    private val url: String = apiUrl ?: Route.API_URL.path
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

    @OptIn(DelicateCoroutinesApi::class)
    fun startHttpServer() {
        /* val job = GlobalScope.launch {
            embeddedServer(Netty, port = 8080, module = )
        } */
    }

    @ApiStatus.Experimental
    fun receiveWebsocketMessage(block: (message: String?) -> Unit) {
        val websocketUrl = Route.WEBSOCKET_BASE_URL.path

        // Hello u-u o/
        val pingEvent = JsonObject(mapOf(
            "event" to JsonPrimitive("ping")
        ))

        runBlocking {
            client.webSocket(host = websocketUrl, path = Route.WEBSOCKET_PATH.path) {
                send(pingEvent.toString())

                val message = (incoming.receive() as? Frame.Text)?.readText()
                block(message)
            }
        }
    }
}