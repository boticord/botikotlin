package top.boticord

import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import top.boticord.http.*
import top.boticord.models.Resource
import top.boticord.models.bots.BotProfile
import top.boticord.models.servers.ResourceServer
import top.boticord.models.users.UserProfile
import top.boticord.models.websockets.EventFullData
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

public class BotiCordClient(
    private var boticordToken: String?,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    },
    logging: Boolean = true
) {
    private val http: HttpManager = HttpManager(boticordToken)
    private val websockets: Notifications = Notifications(
        logging,
        boticordToken,
        http,
        json
    )

    // хз пусть будет)
    @OptIn(ExperimentalEncodingApi::class)
    internal fun decodeId(): String? {
        val toParts = boticordToken?.split(".")
        val payload = toParts?.get(1)?.toByteArray() ?: return null
        val payloadJson = Json.decodeFromString<JsonObject>(String(Base64.decode(payload)))

        return payloadJson["id"]?.jsonPrimitive?.content
    }

    @Throws(IllegalArgumentException::class)
    public suspend fun fetch(id: Long, type: Type): Resource = when (type) {
        Type.Bot -> fetchBot(id)
        Type.User -> fetchUser(id)
        Type.Server -> fetchServer(id)
    }

    public suspend fun update(
        botId: Long,
        memberCount: Int?,
        shardCount: Int?,
        guildCount: Int?
    ): BotProfile {
        if (boticordToken.isNullOrEmpty())
            throw IllegalStateException("Can't update bot statistics while boticord token is null")

        val response = http.request(BotRoute.UPDATE_BOT_STATISTICS) {
            params("id" to botId.toString())
            body {
                put("members", memberCount)
                put("shards", shardCount)
                put("servers", guildCount)
            }
        }

        return decode<BotProfile>(response.bodyAsText())
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

    private suspend fun fetchSearchKey(): String =
        parseResultFromString(http.request(SearchRoute.SEARCH_KEY).bodyAsText(), "key")

    @Throws(IllegalArgumentException::class)
    private fun parseResultFromString(data: String, key: String = "result"): String =
        json.decodeFromString<JsonObject>(data)
            .jsonObject[key]?.toString() ?: throw IllegalStateException("API returned null result.")

    @Throws(
        IllegalArgumentException::class,
        SerializationException::class
    )
    private inline fun <reified T> decode(data: String): T =
        json.decodeFromString<T>(parseResultFromString(data))

    @Throws(IllegalStateException::class)
    public suspend fun notifications(block: (EventFullData) -> Unit) {
        if (boticordToken.isNullOrEmpty())
            throw IllegalStateException("Notifications can't be listened if boticord token is null")

        websockets.listen { block(it) }
    }

    @OptIn(DelicateCoroutinesApi::class)
    public suspend fun autopost(
        botId: Long,
        memberCount: Int?,
        shardCount: Int? = null,
        guildCount: Int? = null
    ) {
        GlobalScope.launch {
            update(botId, memberCount, shardCount, guildCount)
            delay(10_000L)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    public suspend fun autopost(
        scope: CoroutineScope = GlobalScope,
        botId: Long,
        memberCount: Int?,
        shardCount: Int? = null,
        guildCount: Int? = null
    ) {
        scope.launch {
            while (isActive) {
                update(botId, memberCount, shardCount, guildCount)
                delay(10_000L)
            }
        }
    }
}

public suspend fun <T> boticord(
    block: suspend BotiCordClient.() -> T
): T = BotiCordClient(null).block()

public suspend fun <T> boticord(
    token: String,
    block: suspend BotiCordClient.() -> T
): T = BotiCordClient(token).block()

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

public fun boticord(): BotiCordClient =
    BotiCordClient(null)