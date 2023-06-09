import com.github.magm1go.botikotlin.BotiCordClient

fun main(args: Array<String>) {
    val boticordClient = BotiCordClient()

    // getServer принимает Long
    println(boticordClient.getServer(000000000000000))
}