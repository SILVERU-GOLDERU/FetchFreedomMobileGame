package pt.iade.games.fetchfreedom.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

@Composable
fun MainScreen(onLoginSuccess: () -> Unit) {
    var isLoggedIn by remember { mutableStateOf(false)} // Simulating login state

    if (!isLoggedIn) {
        // Show LoginScreen
        LoginScreen { username, password ->
            if (username.isNotEmpty() && password.isNotEmpty()) {
                isLoggedIn = true // Update login state

                onLoginSuccess() // Trigger navigation to BookActivity
            }
        }
    } else {
        // Optional placeholder while navigating
        Text("Logging in...", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true, name = "Main Screen Preview")
@Composable
fun MainScreenPreview() {
    AppTheme() {
        MainScreen(onLoginSuccess = {

        })
    }
}