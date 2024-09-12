package top.boticord

import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import top.boticord.http.BotRoute
import top.boticord.http.HttpManager
import top.boticord.http.ServerRoute
import top.boticord.http.UserRoute
import top.boticord.models.Resource
import top.boticord.models.bots.BotProfile
import top.boticord.models.servers.ResourceServer
import top.boticord.models.users.UserProfile
import kotlin.jvm.Throws

public class BotiCordClient(
    private var boticordToken: String?,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
) {
    private val http = HttpManager(boticordToken)

    @Throws(IllegalArgumentException::class)
    public suspend fun fetch(id: Long, type: Type): Resource = when (type) {
        Type.Bot -> fetchBot(id)
        Type.User -> fetchUser(id)
        Type.Server -> fetchServer(id)
    }

    public fun setup(token: String) {
        this.boticordToken = token
    }

    private suspend fun fetchUser(id: Long): UserProfile {
        val response = http.request(UserRoute.FETCH_USER) {
            params("id" to id.toString())
        }

        return decode<UserProfile>(response.bodyAsText())
    }

    private suspend fun fetchBot(id: Long): BotProfile {
        val response = http.request(BotRoute.FETCH_BOT) {
            params("id" to id.toString())
        }

        return decode<BotProfile>(response.bodyAsText())
    }

    private suspend fun fetchServer(id: Long): ResourceServer {
        val response = http.request(ServerRoute.FETCH_SERVER) {
            params("id" to id.toString())
        }

        return decode<ResourceServer>(response.bodyAsText())
    }

    @Throws(IllegalArgumentException::class)
    private fun parseResultFromString(data: String) = json.decodeFromString<JsonObject>(data)
            .jsonObject["result"]?.toString() ?: throw IllegalStateException("API returned null result.")

    @Throws(
        IllegalArgumentException::class,
        SerializationException::class
    )
    private inline fun <reified T> decode(data: String) =
        json.decodeFromString<T>(parseResultFromString(data))
}

@OptIn(DelicateCoroutinesApi::class)
public fun <T> boticord(
    scope: CoroutineScope = GlobalScope,
    block: suspend BotiCordClient.() -> T
): Deferred<T> = scope.async { BotiCordClient(null).block() }

@OptIn(DelicateCoroutinesApi::class)
public fun <T> boticord(
    token: String,
    scope: CoroutineScope = GlobalScope,
    block: suspend BotiCordClient.() -> T
): Deferred<T> = scope.async { BotiCordClient(token).block() }

public fun <T> boticordBlocking(
    block: suspend BotiCordClient.() -> T
): T = runBlocking { BotiCordClient(null).block() }

public fun <T> boticordBlocking(
    token: String,
    block: suspend BotiCordClient.() -> T
): T = runBlocking { BotiCordClient(token).block() }

public fun boticord(token: String): BotiCordClient =
    BotiCordClient(token)