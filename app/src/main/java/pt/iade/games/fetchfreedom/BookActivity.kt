package pt.iade.games.fetchfreedom

import android.content.Context
import android.content.Intent
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
import com.innoveworkshop.gametest.GameActivity
import pt.iade.games.fetchfreedom.ui.components.BookPage
import pt.iade.games.fetchfreedom.ui.components.RelatedItem
import androidx.compose.foundation.verticalScroll

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
    defaultOnSwipeRight: () -> Unit = {},
    onRelatedItemClickGlobal: (String) -> Unit = {}
): List<@Composable () -> Unit> {
    return listOf(
        {
            BookPage(

                imageId = R.drawable.foodbackground,
                enlargedImageId = R.drawable.food,
                title = "Dog Food - Snowy Snax",
                description = "Snowy Snax, it was your favorite. The smell, the taste, the way your owner used to pour it with a smile. You forgot that back in the day the snacks came in a red can. Now, the scent drifts faintly through the sewer, and it makes your chest ache more than your stomach growl. Funny how it still makes you feel warm inside, as if he is still here...\n" +
                        "\n It doesnt look tasty though, you rather chew some rat bones instead to pass the hunger...",
                sentimentalValue = 0.33f,
                onClickVisitGameBoy = null,
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight,
                relatedItems = listOf(
                    RelatedItem("bone", R.drawable.bone, "Bone"),
                ),
                onRelatedItemClick = { item ->
                    onRelatedItemClickGlobal(item.id)
                }
            )

        },
        {
            BookPage(
                imageId = R.drawable.collarbackground,
                enlargedImageId = R.drawable.collar,
                title = "The Dog Collar",
                description = "It is your old collar, It excites you seeing this after a long time. It was your favourite after all. It was stylish and the red suited you. Maybe that is why your current collar is red.  But now that you look at it... you prefer the one you have now.\n" +
                        "\n Why does it smell like food? *sniff* 'I want some Snowy Snax'",
                sentimentalValue = 0.81f,
                onClickVisitGameBoy = null,
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight,
                relatedItems = listOf(
                    RelatedItem("food", R.drawable.food, "Snacks"),
                ),
                onRelatedItemClick = { item ->
                    onRelatedItemClickGlobal(item.id)
                }

            )
        },
        {
            BookPage(
                imageId = R.drawable.bonebackground,
                enlargedImageId = R.drawable.bone,
                title = "The Chewing Bone",
                description = "You remember this bone like it was yesterday. It was stuck in the grime of the sewer, but it felt like treasure when your teeth sank into it. Back then, it was the only comfort you had back then. Is it the same bone though?\n" +
                        "\n *Laugh* You remember a specific day where the bone was so dirty it looked green like your old tennis ball! Did it have something to do with spilled food? Hopefully it wasn't the Snowy Snax!",
                sentimentalValue = 0.73f,
                onClickVisitGameBoy = null,
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight,
                relatedItems = listOf(
                    RelatedItem("food", R.drawable.food, "Snacks"),
                    RelatedItem("tennis", R.drawable.tennis, "Tennis")
                ),
                onRelatedItemClick = { item ->
                    onRelatedItemClickGlobal(item.id)
                }

            )
        },
        {
            BookPage(
                imageId = R.drawable.tennisbackground,
                enlargedImageId = R.drawable.tennis,
                title = "The Tennis Ball",
                description = "This tennis was bright green, now its yellow, faded and scratched, but it still bounced just right. As you nudge it with your nose, it feels smaller somehow, like it belongs to a different time. You are enormous, no wonder your old collar doesn't fit you anymore! You can almost swallow this ball whole!\n" +
                        "\nSome of your best memories is playing games. You always preferred ones that made you run like playing catch. You have a little moment of introspection and notice how big you have gotten along the years... 'Is that why he abandoned me?'",
                sentimentalValue = 0.23f,
                onClickVisitGameBoy = null,
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight,
                relatedItems = listOf(
                    RelatedItem("gameBoy", R.drawable.gameboy, "GameBoy"),
                    RelatedItem("collar", R.drawable.collar, "Collar")
                ),
                onRelatedItemClick = { item ->
                    onRelatedItemClickGlobal(item.id)
                }
            )
        },
        {
            BookPage(
                imageId = R.drawable.gameboybackground,
                enlargedImageId = R.drawable.gameboy,
                title = "The GameBoy",
                description = "The first memory you have is of you watching your playing with this toy. You always wanted to try it. Unfortunatly looking at it reminds you of the day when still saw your owner in high regards. \n" +
                        "\nBack then you were happy enough by just playing with your little bone. Nowadays dogs only play video games *sigh*\n" +
                        "\nIt is a grey 1989 GameBoy. You wonder why is it here in the sewers, can you still play it? *click* It turns on! you cant contain your excitement! What was the game anyway? Something about bricks? No, it was a retro game about throwing bones to as many dogs as you can below! Feels like bricks thought...",
                sentimentalValue = 0.99f,
                onSwipeLeft = defaultOnSwipeLeft,
                onSwipeRight = defaultOnSwipeRight,
                relatedItems = listOf(
                    RelatedItem("bone", R.drawable.bone, "Bone"),
                ),
                onRelatedItemClick = { item ->
                    onRelatedItemClickGlobal(item.id)
                },
                onClickVisitGameBoy = {

                    val intent = Intent(context, GameActivity::class.java)
                    context.startActivity(intent)
                },
            )
        },

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier = Modifier) {
    var pageIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    val itemIdToPageIndex = mapOf(
        "gameBoy" to 4,
        "tennis" to 3,
        "bone" to 2,
        "collar" to 1,
        "food" to 0,
    )

    fun onRelatedItemClick(itemId: String) {
        itemIdToPageIndex[itemId]?.let { newIndex ->
            pageIndex = newIndex
        } ?: run {
            Toast.makeText(context, "Unknown item: $itemId", Toast.LENGTH_SHORT).show()
        }
    }


    val numPages = bookPages(context).size
    val pages = bookPages(
        context = context,
        defaultOnSwipeLeft = {
            if (pageIndex < (numPages - 1)) pageIndex++
        },
        defaultOnSwipeRight = {
            if (pageIndex > 0) pageIndex--
        },
        onRelatedItemClickGlobal = ::onRelatedItemClick
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