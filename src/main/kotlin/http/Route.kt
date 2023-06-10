package http

enum class Route(val path: String) {
    GET_BOT("/bots/%s"),
    POST_BOT_STATS("/bots/%s/stats"),
    GET_SERVER("/servers/%s"),
    GET_USER("/users/%s"),
    SEARCH_KEY("/search-key/"),
    API_URL("https://api.boticord.top"),
    WEBSOCKET_BASE_URL("gateway.boticord.top"),
    WEBSOCKET_PATH("/websocket/")
}
