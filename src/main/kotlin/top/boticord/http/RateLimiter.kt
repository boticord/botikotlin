package top.boticord.http

import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

internal object RateLimiter {
    private const val MAX_REQUEST_COUNT: Int = 25

    private val duration: Duration = 25.seconds
    private val logger: Logger = LoggerFactory.getLogger("[BotiCord: RateLimiter]")

    private val mutex: Mutex = Mutex()
    private var requestQueue: Queue<Long> = ConcurrentLinkedQueue()

    suspend fun ratelimitedRequest(request: suspend () -> HttpResponse): HttpResponse {
        return mutex.withLock {
            val currentTime = System.currentTimeMillis()

            while (requestQueue.isNotEmpty() && currentTime - requestQueue.first() > duration.inWholeMilliseconds) {
                println(currentTime - requestQueue.first() > duration.inWholeMilliseconds)

                requestQueue.poll()
            }

            if (requestQueue.size >= MAX_REQUEST_COUNT) {
                val waitTime = duration.inWholeMilliseconds - (currentTime - requestQueue.first())
                logger.warn("Rate limit exceeded. Waiting for $waitTime ms.")
                delay(waitTime)
                ratelimitedRequest(request)
            }

            requestQueue.add(currentTime)

            request()
        }
    }
}