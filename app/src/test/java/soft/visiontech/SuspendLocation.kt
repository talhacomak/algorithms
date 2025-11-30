package soft.visiontech

import android.location.Location
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import org.junit.Test
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SuspendLocation {

    @Test
    fun test() {
        val fusedClient = FusedLocationClient()

        val location: Location = runBlocking { awaitLocation(fusedClient) }

        /*runTest {
            val location = awaitLocation(fusedClient)
        }*/

        GlobalScope.launch {
            val location = awaitLocation(fusedClient)
        }
    }

    interface LocationClient {
        suspend fun getCurrentLocation(): Location?
    }

    class FusedLocationClient : LocationClient {
        fun getLastLocation(
            onSuccess: (Location?) -> Unit,
            onError: (Throwable) -> Unit = {}
        ) {
            // Simüle edilmiş async lokasyon alma
        }

        override suspend fun getCurrentLocation(): Location? {
            // Simüle edilmiş suspend lokasyon alma
            return null
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun awaitLocation(fusedClient: FusedLocationClient): Location =
        suspendCancellableCoroutine { continuation ->
            fusedClient.getLastLocation(
                onSuccess = { loc ->
                    if (loc != null && continuation.isActive) {
                        continuation.resume(loc)
                    } else if (continuation.isActive) {
                        continuation.resumeWithException(
                            IllegalStateException("Location is null")
                        )
                    }
                },
                onError = { error ->
                    if (continuation.isActive) {
                        continuation.resumeWithException(error)
                    }
                }
            )

            // İstersen cancellation ile request iptal etme hook’u da ekleyebilirsin:
            continuation.invokeOnCancellation {
                // fusedClient.cancelRequest() vs
            }
        }
}