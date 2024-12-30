package pt.iade.games.fetchfreedom.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.games.fetchfreedom.R
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator

@Composable
fun BookPage(
    @DrawableRes imageId: Int,
    @DrawableRes enlargedImageId: Int,
    title: String,
    description: String,
    onClickVisitGameBoy: (() -> Unit)? = null,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    relatedItems: List<RelatedItem> = emptyList(),
    onRelatedItemClick: (RelatedItem) -> Unit = {},

    sentimentalValue: Float = 0f
) {
    var offset by remember { mutableFloatStateOf(0f) }
    var showLargeImage by remember { mutableStateOf(false) }
    val paragraphs = description.split("\n\n")
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable { showLargeImage = true },
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = Color(0xFFBAEAFF) // primaryContainerLight
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(imageId),
                        contentDescription = "Collectible Cover",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF001F29), // onPrimaryContainerLight
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF70787D),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(12.dp) // space between the border & text
            ) {
                Column {
                    paragraphs.forEachIndexed { index, para ->
                        Text(
                            text = para,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF171C1F),
                        )
                        if (index < paragraphs.size - 1) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            if (sentimentalValue > 0f) {
                SentimentalValueBar(sentimentalValue)
            }
            if (relatedItems.isNotEmpty()) {
                Text(
                    text = "Related Items",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                )
                RelatedItemsRow(
                    relatedItems = relatedItems,
                    onItemClick = onRelatedItemClick
                )
            }
            if (onClickVisitGameBoy != null) {
                Button(
                    onClick = onClickVisitGameBoy,
                    modifier = Modifier.padding(top = 16.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCFE6F1), // secondaryContainerLight
                        contentColor = Color(0xFF071E26) // onSecondaryContainerLight
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Play?",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
        if (showLargeImage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showLargeImage = false }
            ) {
                Image(
                    painter = painterResource(enlargedImageId),
                    contentDescription = "Enlarged Different Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }
        }

    }
}



// An optional composable to show a small label + a progress bar
@Composable
fun SentimentalValueBar(value: Float) {
    // You can clamp 'value' to 0..1 if you want
    val clampedValue = value.coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sentimental Value",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = clampedValue,
            modifier = Modifier
                .fillMaxWidth(0.8f)  // 80% width
                .height(8.dp),
            color = Color(0xFF00B0FF)  // or MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "${(clampedValue * 100).toInt()}%",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}