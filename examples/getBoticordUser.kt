import com.github.magm1go.botikotlin.BotiCordClient

fun main(args: Array<String>) {
    val boticordClient = BotiCordClient()

    // getUserProfile принимает Long
    println(boticordClient.getUserProfile(000000000000000))
}