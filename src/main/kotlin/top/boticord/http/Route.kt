package top.boticord.http

import io.ktor.http.*

internal class Route(val method: HttpMethod, path: String) {
    private val version: Int = 3
    private val base: String = "https://api.boticord.top/v$version"

    internal val url = "$base$path"
}

internal object WebsocketRoute {
    private const val PREFIX: String = "wss://"

    const val WEBSOCKET_URL: String = "gateway.boticord.top/websocket"
}

internal object BotRoute {
    val FETCH_BOT: Route = Route(HttpMethod.Get, "/bots/{id}")
    val UPDATE_BOT_STATISTICS: Route = Route(HttpMethod.Post, "/bots/{id}/stats")
}

internal object UserRoute {
    val FETCH_USER: Route = Route(HttpMethod.Get, "/users/{id}")
}

internal object SearchRoute {
    val SEARCH_KEY: Route = Route(HttpMethod.Get, "/search-key")
}

internal object ServerRoute {
    val FETCH_SERVER: Route = Route(HttpMethod.Get, "/servers/{id}")
}