package top.boticord

import top.boticord.http.HttpManager
import top.boticord.http.exceptions.HttpException
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*
import top.boticord.data.InternalServerErrorData
import top.boticord.data.bots.BotProfile
import top.boticord.data.bots.BotStats
import top.boticord.data.servers.ResourceServer
import top.boticord.data.users.UserProfile
import top.boticord.http.Route

// TODO: Meili search support and autopost bot statistic
class BotiCordClient(private val boticordToken: String? = null) {
    private val jsonBuilder = Json {
        this.isLenient = true
        this.ignoreUnknownKeys = true
    }
    private val httpClient = HttpManager(this.boticordToken, Route.API_URL.path)

    /* private fun getMeiliKey(): MeiliKeyBotiCord? {
        val response = httpClient.getRequest(Route.SEARCH_KEY.path)
        return handleErrors(response, jsonBuilder)
    }

    fun searchBots(): HttpResponse<String> {
        val client = HttpClient.newBuilder().build()
        val request = httpClient.createMeiliRequest(IRequestManager.Methods.GET, null, null, getMeiliKey()!!.key)

        return client.send(request, BodyHandlers.ofString())
    } */

    fun getBotInfo(botID: Long): BotProfile? = runBlocking {
        val apiResponse = async {
            httpClient.sendRequest(HttpMethod.Get, null, Route.GET_BOT.path.format(botID.toString()), null)
        }

        return@runBlocking handleErrors(apiResponse.await(), jsonBuilder)
    }

    fun updateBotStats(botID: Long, stats: BotStats): BotProfile? = runBlocking {
        val apiResponse = async {
            httpClient.sendRequest(
                HttpMethod.Post, {
                    JsonObject(mapOf(
                        "members" to JsonPrimitive(stats.members),
                        "guilds" to JsonPrimitive(stats.guilds),
                        "shards" to JsonPrimitive(stats.shards)
                    ))
                }, Route.POST_BOT_STATS.path.format(botID.toString()), boticordToken
            )
        }

        return@runBlocking handleErrors(apiResponse.await(), jsonBuilder)
    }

    fun getUserProfile(userID: Long): UserProfile? = runBlocking {
        val apiResponse = async {
            httpClient.sendRequest(HttpMethod.Get, null, Route.GET_USER.path.format(userID), null)
        }

        return@runBlocking handleErrors(apiResponse.await(), jsonBuilder)
    }

    fun getServer(serverID: Long): ResourceServer? = runBlocking {
        val apiResponse = async {
            httpClient.sendRequest(HttpMethod.Get, null, Route.GET_SERVER.path.format(serverID), null)
        }

        return@runBlocking handleErrors(apiResponse.await(), jsonBuilder)
    }

    private inline fun <reified T> handleErrors(apiResponse: HttpResponse, jsonBuilder: Json): T? = runBlocking {
        val jsonResponse = jsonBuilder.parseToJsonElement(apiResponse.body())
            .jsonObject

        when (apiResponse.status.value) {
            200, 201 -> return@runBlocking jsonBuilder.decodeFromJsonElement(jsonResponse["result"]!!)
            else -> {
                val errors = jsonResponse["errors"]?.jsonArray?.map { jsonBuilder.decodeFromString<InternalServerErrorData>(it.toString()) }

                for (error in errors!!) {
                    throw HttpException("${error.code} (${error.code.code}) - ${error.message}")
                }
            }
        }

        return@runBlocking null
    }
}