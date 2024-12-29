package pt.iade.games.fetchfreedom

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.compose.AppTheme
import com.innoveworkshop.gametest.GameActivity
import pt.iade.games.fetchfreedom.ui.components.LoginScreen
import pt.iade.games.fetchfreedom.ui.components.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                //here!!!!
                MainScreen { navigateToBookActivity() }
            }
        }
    }

    // Function to navigate to BookActivity
    private fun navigateToBookActivity() {
        startActivity(Intent(this, BookActivity::class.java))
        finish() // Close MainActivity so it doesn't stay in the back stack
    }

    private fun navigateToGameActivity() {
        startActivity(Intent(this, GameActivity::class.java))
        finish() // Close MainActivity so it doesn't stay in the back stack
    }
}