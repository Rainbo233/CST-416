package com.example.challenge_1

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
import com.example.jokes_2.ui.theme.Challenge_1Theme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge_1Theme {
                MyApp()
            }
        }
    }
    }

data class JokeModel(

    val id: Int,
    var question: String,
    var punch_line: String,
    var perks: Array<String>,
    var answer_is_visible: Boolean

)

@Composable
fun MyApp(){

    var jokes = remember {
        mutableStateListOf(

            JokeModel(

                0,
                "Freelance",
                "Be your own boss!",
                arrayOf("- Might get a job", "- 401k?", "- Follow your dreams", "- and then instantly get them crushed"),
                false

            ),
            JokeModel(

                1,
                "Startup",
                "Do you like having 0 job security?",
                arrayOf("- Gamble on your job", "- 401k?", "- Do you like being bossed around by buisiness majors?", "- Me niether."),
                false

            ),
            JokeModel(

                2,
                "Agency",
                "Hopefully not the federal kind.",
                arrayOf("- Get assigned a job", "- 401k", "- Get something kinda like your dream", "- Get ready to be taken advantage of"),
                false

            ),
            JokeModel(

                3,
                "Corporate",
                "Welcome to HELL.",
                arrayOf("- Oh god", "- Please no", "- NO", "- NOOOOOOOOOOOOOOOOOOOOOOO"),
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
            for(perk in joke.perks) {
            
              Text(
              
                modifier = Modifier.padding(10.do),
                text = perk
              
              )
            
            }
            Button(onClick = {/* YES, KILL! */}){
            
              Text(
              
                modifier = Modifier.padding(10.dp),
                text = "Subscribe!"
              
              )
              
            }

        }

        Divider()

    }
}

@Composable
fun joke_2 (joke: JokeModel, context: Context){
        Text(text = joke.question,
            Modifier
                .padding(20.dp)
                .clickable {
                    Toast
                        .makeText(context, joke.punch_line, Toast.LENGTH_LONG)
                        .show()
                })
        Divider()
    }



