package game.network

import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject

object FuelClient {
    private const val serverUrl = "https://dog-shit-game.vercel.app"


    fun login(
        context: Context,
        playerName: String,
        playerPassword: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val url = "$serverUrl/login/$playerName/$playerPassword"
        println("Requesting URL: $url") // Debugging
        Fuel.get(url)
            .responseObject { _, _, result ->
                result.fold(
                    success = { player ->
                        val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putInt("playerID", player)
                            apply()
                        }
                        onSuccess("Log in successful")
                    },
                    failure = { error ->
                        onFailure("Login failed: ${error.message}")
                    }
                )
            }
    }
    fun getcollectables(
        context: Context,
        playerID: Int,
        onSuccess: (List<Int>) -> Unit, // Change success callback to return a list of integers
        onFailure: (String) -> Unit
    ) {
        val url = "$serverUrl/collectables/collected/$playerID"
        println("Requesting URL: $url") // Debugging
        Fuel.get(url)
            .responseJson { _, _, result ->
                result.fold(
                    success = { json ->
                        try {
                            // Parse the response as a JSONArray
                            val jsonArray = json.array()
                            val ids = (0 until jsonArray.length()).map { index ->
                                jsonArray.getInt(index)
                            }
                            onSuccess(ids) // Pass the parsed IDs to the success callback
                        } catch (e: Exception) {
                            onFailure("Parsing error: ${e.message}")
                        }
                    },
                    failure = { error ->
                        onFailure("Request failed: ${error.message}")
                    }
                )
            }
    }
}