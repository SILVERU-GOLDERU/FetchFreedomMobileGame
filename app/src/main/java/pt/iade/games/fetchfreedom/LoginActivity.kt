package pt.iade.games.fetchfreedom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.innoveworkshop.gametest.GameActivity
import game.network.FuelClient
import pt.iade.games.fetchfreedom.ui.components.LoginScreen

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LoginScreen { username, password ->
                    // Validate user input
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        // Call the server using FuelClient
                        FuelClient.login(
                            context = this,
                            playerName = username,
                            playerPassword = password,
                            onSuccess = { message ->
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                                // Navigate to the next activity
                                val intent = Intent(this, GameActivity::class.java)
                                startActivity(intent)
                                finish() // Close the login screen
                            },
                            onFailure = { errorMessage ->
                                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    } else {
                        Toast.makeText(this, "Please enter valid credentials!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}