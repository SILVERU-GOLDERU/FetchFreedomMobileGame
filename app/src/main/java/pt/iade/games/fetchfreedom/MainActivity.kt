package pt.iade.games.fetchfreedom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import pt.iade.games.fetchfreedom.ui.components.Page1
import pt.iade.games.fetchfreedom.ui.components.Page2
import pt.iade.games.fetchfreedom.ui.components.Page3
import pt.iade.games.fetchfreedom.ui.components.Page4
import pt.iade.games.fetchfreedom.ui.components.Page5
import pt.iade.games.fetchfreedom.ui.components.ProjectCard


//make function that does page++ for a button then call it on storyboard.kt

//make function that goes to page 2A
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(name: String, modifier: Modifier = Modifier) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("hellow")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    )
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),

        ){
            ProjectCard(title = "AMONGING", votes = 3455,
                modifier = Modifier.padding(vertical = 10.dp))
            ProjectCard(title = "BIUB", votes = 70000)
        }

}
    var page: Int by remember { mutableStateOf(1) }
    Column {
        if (page == 1) {
            Page1(title = "Intro", branching = "1")
        }else if (page == 2) {
            Page2(title = "", branching = "2")
        }else if (page == 3) {
            Page3()
        }else if (page == 4) {
            Page4()
        }else if (page == 5) {
            Page5()
        }else if (page == 6) {
            Page6()
        }

        Button(onClick = {page++}){ Text("Next page") }


    }
}
//hi im just a little guy that is checking if things work

@Composable
fun Page6() {
    TODO("Not yet implemented")
}

@Preview()
@Composable
fun MainViewPreviewOld() {
    AppTheme {  MainView("Android") }
}