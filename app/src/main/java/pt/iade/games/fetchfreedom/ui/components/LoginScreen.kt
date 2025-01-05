package pt.iade.games.fetchfreedom.ui.components
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import game.network.FuelClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Access context inside the composable
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // Title Text
            Text(
                text = "Log In",
                style = MaterialTheme.typography.displayMedium,
                color = Color(0xFF0B6780) // primaryLight
            )
            // Username Input Field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color(0xFF70787D)) }, // outlineLight
                modifier = Modifier.fillMaxWidth(),
            )
            // Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color(0xFF70787D)) }, // outlineLight
                modifier = Modifier.fillMaxWidth(),
            )
            // Login Button
            Button(
                onClick = {
                    // Pass the context explicitly to the login function
                    performLogin(context, username, password, onLogin)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Log In", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

private fun performLogin(
    context: Context,
    username: String,
    password: String,
    onLogin: (String, String) -> Unit
) {
    FuelClient.login(
        context = context,
        playerName = username,
        playerPassword = password,
        onSuccess = { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onLogin(username, password) // Trigger login logic in the parent
        },
        onFailure = { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    )
}
