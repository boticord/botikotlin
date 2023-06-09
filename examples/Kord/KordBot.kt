suspend fun main(args: Array<String>) {
    private val botID: Long = 000000000000
    private val boticordClient = BotiCordClient("boticord token")
    val kord = Kord(args.firstOrNull() ?: error("token required"))

    kord.on<ReadyEvent> {
        boticordClient.updateBotStats(botID, BotStats(members = 10000))
    }
}