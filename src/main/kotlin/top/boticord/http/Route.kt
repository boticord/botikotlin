package top.boticord.http

import io.ktor.http.*

internal const val BASE: String = "https://api.boticord.top/v3"
internal const val MEILI_BASE: String = "https://api.boticord.top/search"

internal class Route(val method: HttpMethod, path: String, base: String = BASE) {
    internal val url = "$base$path"
}

internal object WebsocketRoute {
    const val WEBSOCKET_HOST: String = "gateway.boticord.top"
    const val WEBSOCKET_PATH: String = "/websocket/"
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