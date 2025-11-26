package soft.visiontech

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.junit.Test

class HeavyWork {

    @Test
    fun heavy_work() {
        val job = GlobalScope.launch {
            heavyWork()
        }
        job.cancel()
    }

    suspend fun heavyWork() {
        val ctx = currentCoroutineContext()
        repeat(10_000_000) {
            if (ctx.isActive) {
                val x = it * it
            }
        }
    }
}