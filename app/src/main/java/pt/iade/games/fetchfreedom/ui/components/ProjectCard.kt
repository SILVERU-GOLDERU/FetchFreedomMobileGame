package pt.iade.games.fetchfreedom.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.games.fetchfreedom.R
import pt.iade.games.fetchfreedom.ProjectDetailActivity
import pt.iade.games.fetchfreedom.Vote1Trigger

@Composable
fun ProjectCard(title: String, votes: Int,
                modifier: Modifier = Modifier){
    val context = LocalContext.current
    Card (modifier = modifier.fillMaxWidth(),
        onClick = {
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ProjectDetailActivity::class.java)
            context.startActivity(intent)
        }
        ){
        Box(

            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(R.drawable.placeholder_cover_image),
                contentDescription = "cover image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column (modifier = Modifier.padding(10.dp)) {

                Text(text = "This $title, $votes 123", fontSize = 15.sp, color = Color.White)

                Text("Text Beginning",)
                Text("Text End")
            }
            Row (
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                val context = LocalContext.current

                Text(text = "Amogus baby boy wow",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical=20.dp),
                    maxLines =1,
                    overflow = TextOverflow.Ellipsis)

                Button(onClick = { context.startActivity(Intent(context, Vote1Trigger::class.java)) },
                    modifier = Modifier
                        .padding(start = 90.dp)
                        .weight( weight = 1f)
                ) { Text("vote") }
            }
        }

    }

}
