class JDABot : EventListener {
    private val botID: Long = 00000000000000
    private val boticordClient = BotiCordClient("YOUR_BOTICORD_TOKEN")

    fun onEvent(event: GenericEvent?) {
        when (event) {
            is ReadyEvent -> boticordClient.updateBotStats(botID, BotStats(members = 10000))
            else -> {}
        }
    }

    companion object {
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val jda: JDA = JDABuilder.createDefault("token")
                .addEventListeners(ReadyListener())
                .build()

            jda.awaitReady()
        }
    }
}