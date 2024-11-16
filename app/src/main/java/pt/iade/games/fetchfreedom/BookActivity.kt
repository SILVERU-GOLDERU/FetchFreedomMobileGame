package pt.iade.games.fetchfreedom

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import pt.iade.games.fetchfreedom.ui.components.BookPage

class BookActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme  {
                MainView()
            }
        }
    }
}

@Composable
fun bookPages(
    context: Context,
    defaultOnSwipeLeft: () -> Unit = {},
    defaultOnSwipeRight: () -> Unit = {}
): List<@Composable () -> Unit> {
    return listOf(
        {
            BookPage(
                imageId = R.drawable.placeholder_cover_image,
                title = "The Good Surgeon",
                description = "OLLLAA A. Ruggieri has seen the good, the bad, and the ugly of his profession.",
                onClickVisitBook = {
                    Toast.makeText(context, "Visiting book...", Toast.LENGTH_LONG).show()
                },
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight
            )
        },
        {
            BookPage(
                imageId = R.drawable.placeholder_cover_image,
                title = "njndijnsd",
                description = "As an active surgeon and former department chairman, Dr. Paul A. Ruggieri has seen the good, the bad, and the ugly of his profession.",
                onClickVisitBook = {
                    Toast.makeText(context, "Visiting book...", Toast.LENGTH_LONG).show()
                },
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight
            )
        },
        {
            BookPage(
                imageId = R.drawable.placeholder_cover_image,
                title = "njndijnsd",
                description = "As an active surgeon and former department chairman, Dr. Paul A. Ruggieri has seen the good, the bad, and the ugly of his profession.",
                onClickVisitBook = {
                    Toast.makeText(context, "Visiting book...", Toast.LENGTH_LONG).show()
                },
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight
            )
        },
        {
            BookPage(
                imageId = R.drawable.placeholder_cover_image,
                title = "njndijnsd",
                description = "As an active surgeon and former department chairman, Dr. Paul A. Ruggieri has seen the good, the bad, and the ugly of his profession.",
                onClickVisitBook = {
                    Toast.makeText(context, "Visiting book...", Toast.LENGTH_LONG).show()
                },
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight
            )
        },
        {
            BookPage(
                imageId = R.drawable.placeholder_cover_image,
                title = "njndijnsd",
                description = "As an active surgeon and former department chairman, Dr. Paul A. Ruggieri has seen the good, the bad, and the ugly of his profession.",
                onClickVisitBook = {
                    Toast.makeText(context, "Visiting book...", Toast.LENGTH_LONG).show()
                },
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight
            )
        },

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier = Modifier) {
    var pageIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val numPages = bookPages(context).size
    val pages = bookPages(
        context = context,
        defaultOnSwipeLeft = {
            if (pageIndex < (numPages - 1))
                pageIndex++
        },
        defaultOnSwipeRight = {
            if (pageIndex > 0)
                pageIndex--
        }
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Fetch Freedom",
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 40.sp), // Title style
                        color = Color(0xFF001F29) // onPrimaryContainerLight
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFBAEAFF) // primaryContainerLight
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFF5FAFD)) // backgroundLight
        ) {
            pages[pageIndex]()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    AppTheme {
        MainView()
    }
}