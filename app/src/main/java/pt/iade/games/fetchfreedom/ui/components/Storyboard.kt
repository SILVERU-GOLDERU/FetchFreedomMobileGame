package pt.iade.games.fetchfreedom.ui.components

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.games.fetchfreedom.Vote1Trigger

//unsued for now-later
@Composable
fun Page1(title: String, branching: String,){
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Choice",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Another piece of text",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Text("Tutorial takes 3 minutes", fontWeight = FontWeight.Light, fontSize = 9.sp)

        }

    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        val context = LocalContext.current
        Button(
            onClick = { context.startActivity(Intent(context, Vote1Trigger::class.java)) },
            modifier = Modifier.padding(start = 90.dp)
        ) { Text("Yes") }
        Button(
            onClick = { context.startActivity(Intent(context, Vote1Trigger::class.java)) },
            modifier = Modifier.padding(start = 90.dp)
        ) { Text("No") }
    }
}



@Composable
fun Page2(title: String, branching: String,){

        Column (){
            Text(text = "This 123", fontSize = 9.sp)
            Text("Text Beginning")
            Row(verticalAlignment = Alignment.CenterVertically) {
                val context = LocalContext.current
                Text(text = "Amogus", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Button(
                    onClick = { context.startActivity(Intent(context, Vote1Trigger::class.java)) },
                    modifier = Modifier.padding(start = 90.dp)
                ) { Text("vote") }
            }
            Text("Text End")
        }

}

@Composable
fun Page3() {
    Text("Story 3 NIUNB")
}

@Composable
fun Page4() {
    Text("Story 4")
}

@Composable
fun Page5() {
    Text("Story 5")
}


