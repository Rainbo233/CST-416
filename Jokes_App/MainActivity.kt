package com.example.jokes_2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.example.jokes_2.ui.theme.Jokes_2Theme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jokes_2Theme {
                MyApp()
            }
        }
    }
    }

data class JokeModel(

    val id: Int,
    var question: String,
    var punch_line: String,
    var answer_is_visible: Boolean

)

@Composable
fun MyApp(){

    var jokes = remember {
        mutableStateListOf(

            JokeModel(

                0,
                "Why did the Chicken cross the road?",
                "To get to the other side!",
                true

            ),
            JokeModel(

                1,
                "What's the difference between stupid and Texas?",
                "There is no difference!",
                true

            ),
            JokeModel(

                2,
                "Elon Musk.",
                "That's it. He's the joke.",
                false

            ),
            JokeModel(

                3,
                "Why was 6 afraid of 7?",
                "Because 7 ate 9.",
                true

            ),
            JokeModel(

                4,
                "Knock, knock!",
                "On second thought doing this in text isn't gonna work.",
                false

            )

        )
    }

    Surface(

        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background

    ) {
        LazyColumn() {

            items(jokes.size){

                    index -> joke_1(joke = jokes[index]) {
                Log.d(
                    "Joke Tag",
                    "MyApp: You clicked joke number $it"
                )
                jokes[it] = JokeModel(it, jokes[it].question, jokes[it].punch_line, !jokes[it].answer_is_visible)
            }

            }

        }
    }

}

@Composable
fun joke_1(joke: JokeModel, changeVisability: (id: Int) -> Unit) {

    Column() {

        Text(modifier = Modifier.padding(10.dp),
        text = "Joke number: ${joke.id}")

        Text(
            modifier = Modifier.padding(10.dp).clickable {
                changeVisability(joke.id)
                Log.d("Joke Tag", "joke_1: $joke") },
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            text = joke.question
        )

        if(joke.answer_is_visible){

            Text(
                modifier = Modifier.padding(10.dp),
                fontSize = 24.sp,
                text = joke.punch_line
            )

        }

        Divider()

    }
}

