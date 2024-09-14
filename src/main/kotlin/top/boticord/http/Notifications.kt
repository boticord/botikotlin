package top.boticord.http

import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.boticord.models.websockets.EventFullData
import kotlin.time.Duration.Companion.seconds

internal class Notifications(
    private val loggingEnabled: Boolean,
    private val token: String?,
    private val http: HttpManager,
    private val json: Json
) {
    private val logger: Logger = LoggerFactory.getLogger("[BotiCord: Notifications]")
    private val payload: Payload = Payload(token)

    internal suspend fun listen(block: (EventFullData) -> Unit) {
        if (token.isNullOrEmpty()) return

        http.websocket(HttpMethod.Get, WebsocketRoute.WEBSOCKET_HOST, WebsocketRoute.WEBSOCKET_PATH) {
            send(payload.helloPayload.toString())

            val pingJob = launch {
                while (isActive) {
                    kotlin.runCatching {
                        send(payload.pingPayload.toString())
                        delay(90.seconds.inWholeMilliseconds)
                    }.onFailure { ex ->
                        log("Ping failed: ${ex.message}", "error")
                        this@websocket.cancel("Ping failed due to exception: ${ex.message}")
                    }
                }
            }

            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val value = json.decodeFromString<EventFullData>(frame.readText())
                        log("Received ${value.event} event.", "info")
                        block(value)
                    }
                    is Frame.Close -> log("Websocket was closed because ${frame.readReason()}")
                    else -> log("Received value is not text\nFrame: $frame", "warn")
                }
            }

            pingJob.cancelAndJoin()
        }
    }

    private class Payload(val token: String?) {
        private val emptyObject = buildJsonObject {}
        private val emptyData = buildJsonObject { put("data", emptyObject) }

        val helloPayload: JsonObject = buildJsonObject {
            put("event", JsonPrimitive("auth"))
            put("data", buildJsonObject {
                put("token", JsonPrimitive(token))
            })
        }

        val pingPayload: JsonObject = buildJsonObject {
            put("event", JsonPrimitive("ping"))
            put("data", emptyData)
        }
    }

    init {
        if (token.isNullOrEmpty()) {
            logger.warn("Boticord token not provided. Notification service will be ignored by SDK.")
        }
    }

    private fun log(message: String, level: String = "info") {
        if (!loggingEnabled) return

        when (level) {
            "info" -> logger.info(message)
            "warn" -> logger.warn(message)
            "error" -> logger.error(message)
        }
    }
}