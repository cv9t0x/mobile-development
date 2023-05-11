package ru.samsung.itacademy.mdev.getusdrate

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RateCheckInteractor {
    val networkClient = NetworkClient()

    suspend fun requestRate(): String {
        return withContext(Dispatchers.IO) {
            val result = networkClient.request(MainViewModel.ETH_RATE_URL)
            if (!result.isNullOrEmpty()) {
                parseRate(result)
            } else {
                ""
            }
        }
    }

    private fun parseRate(jsonString: String): String {
        try {
            return JSONObject(jsonString)
                .getString("USD")
        } catch (e: Exception) {

            Log.e("RateCheckInteractor", "", e)
        }
        return ""
    }
}
