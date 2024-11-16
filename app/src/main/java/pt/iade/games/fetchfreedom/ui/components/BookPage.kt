package pt.iade.games.fetchfreedom.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.games.fetchfreedom.R

@Composable
fun BookPage(
    @DrawableRes imageId: Int,
    title: String,
    description: String,
    onClickVisitBook: () -> Unit,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    var offset by remember { mutableFloatStateOf(0f) }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset = 0f },
                    onDragEnd = {
                        if (offset < -300) {
                            onSwipeLeft()
                        } else if (offset > 300) {
                            onSwipeRight()
                        }
                    }
                ) { _, dragAmount ->
                    offset += dragAmount
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Card(
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = {}
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(imageId),
                        contentDescription = "Book Cover",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(8.dp)
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = onClickVisitBook,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Visit book")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BookPagePreview() {
    BookPage(
        imageId = R.drawable.placeholder_cover_image,
        title = "This goes under the image",
        description = "A bunch of text that is underneath the button. It is a description of the image above.",
        onClickVisitBook = {},
        onSwipeRight = {},
        onSwipeLeft = {}
    )
}