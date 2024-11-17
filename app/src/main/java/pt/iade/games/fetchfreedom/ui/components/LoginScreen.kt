package pt.iade.games.fetchfreedom.ui.components
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5FAFD)), // backgroundLight
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
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF5FAFD), // backgroundLight
                    focusedBorderColor = Color(0xFF0B6780), // primaryLight
                    unfocusedBorderColor = Color(0xFF70787D), // outlineLight
                    cursorColor = Color(0xFF0B6780), // primaryLight
                    focusedLabelColor = Color(0xFF0B6780), // primaryLight
                    unfocusedLabelColor = Color(0xFF70787D) // outlineLight
                )
            )
            // Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color(0xFF70787D)) }, // outlineLight
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF5FAFD), // backgroundLight
                    focusedBorderColor = Color(0xFF0B6780), // primaryLight
                    unfocusedBorderColor = Color(0xFF70787D), // outlineLight
                    cursorColor = Color(0xFF0B6780), // primaryLight
                    focusedLabelColor = Color(0xFF0B6780), // primaryLight
                    unfocusedLabelColor = Color(0xFF70787D) // outlineLight
                )
            )
            // Login Button
            Button(
                onClick = { onLogin(username, password) },
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B6780), // primaryLight
                    contentColor = Color(0xFFFFFFFF) // onPrimaryLight
                )
            ) {
                Text("Log In", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLogin = { _, _ -> }
    )
}