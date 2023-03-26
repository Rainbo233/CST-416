package com.example.tip_calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tip_calculator.ui.theme.Tip_CalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tip_CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp(){

        var mealCost by remember { mutableStateOf(10f) }
        var numberofFriends by remember { mutableStateOf(2) }
        var tipPercent by remember { mutableStateOf(10f) }
        var tipValue by remember { mutableStateOf(0f) }
        var totalBill by remember { mutableStateOf(0f) }
        var totalPerPerson by remember { mutableStateOf(0f) }

        Column(modifier = Modifier.padding(10.dp)){

            ShowMealCostDataEntry(mealCost, updateMealPrice = {

                mealCost = it
                totalBill = mealCost + tipValue
                totalPerPerson = totalBill / numberofFriends

            })
            ShowNumberOfFriendsEntry(numberofFriends) {

                numberofFriends = it
                totalBill = mealCost + tipValue
                totalPerPerson = totalBill / numberofFriends

            }
            ShowTipPercentSlider( tipPercent, tipValue ) {

                tipPercent = it
                tipValue = mealCost * tipPercent / 100
                totalBill = mealCost + tipValue
                totalPerPerson = totalBill / numberofFriends

            }

            ShowTotals(totalBill, totalPerPerson)

        }

    }

    @Composable
    private fun ShowMealCostDataEntry(mealCost: Float, updateMealPrice: (Float) -> Unit){

        TextField(value = mealCost.toString(),
            label = { Text("Cost of meal: $10") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.h5,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {

                val inputVal = it.toFloatOrNull()
                if(inputVal != null){

                    updateMealPrice(inputVal)

                } else{

                    Log.d("ShowMealCost", "Could not convert to Float.")

                }

            })


    }

    @Composable
    private fun ShowNumberOfFriendsEntry(numberofFriends: Int, updateFriendsCount: (Int) -> Unit) {

        Card(

            elevation = 10.dp,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {

            Column(

                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                var counterState by remember { mutableStateOf(1) }



                Text(
                    text = "Number of people in group:",
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Row(

                    modifier = Modifier.padding(bottom = 15.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Button(onClick = {

                        if (counterState > 1) {

                            counterState--

                        }

                        updateFriendsCount(counterState)

                    },

                        modifier = Modifier.size(40.dp),
                        shape = CircleShape

                    ) {

                        Text(text = "-")

                    }

                    Text(text = "$counterState",
                        Modifier.padding(10.dp),
                        style = MaterialTheme.typography.h5)

                    Button(onClick = {

                        if(counterState < 10){

                            counterState++

                        }

                        updateFriendsCount(counterState)

                    },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape

                    ) {
                        
                        Text(text = "+")
                        
                    }

                }


            }

        }

    }

    @Composable
    private fun ShowTipPercentSlider(
        tipPercent: Float,
        tipValue: Float,
        updatePercent: (Float) -> Unit
    ) {

        var sliderState = remember {

            mutableStateOf(0f)

        }

        Card(

            elevation = 5.dp,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)

        ){

            Column(modifier = Modifier.padding(10.dp)) {

                val str = String.format(

                    "Tip Percent: %.1f%% Tip Value: " + NumberFormat.getCurrencyInstance().format(tipValue), tipPercent

                )
                Text(str)
                Slider(
                    value = sliderState.value,
                    valueRange = 0f..100f,
                    onValueChange = {

                        sliderState.value = it
                        updatePercent( sliderState.value )

                    })

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {


                    Button(onClick = {

                        sliderState.value = 10f
                        updatePercent( sliderState.value )

                    }) {

                        Text(text = "10%")

                    }

                    Button(onClick = {

                        sliderState.value = 15f
                        updatePercent( sliderState.value )

                    }) {

                        Text(text = "15%")

                    }

                    Button(onClick = {

                        sliderState.value = 20f
                        updatePercent( sliderState.value )

                    }) {

                        Text(text = "20%")

                    }

                    Button(onClick = {

                        sliderState.value = 25f
                        updatePercent( sliderState.value )

                    }) {

                        Text(text = "25%")

                    }


                }

            }

        }

    }

    @Composable
    private fun ShowTotals(totalBill: Float, totalPerPerson: Float) {

       Card(

           elevation = 5.dp,
           modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 10.dp, top = 10.dp)

       ) {

           Column(

               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .background(Color.LightGray)
                   .padding(10.dp)

           ) {

               Text(

                   style = MaterialTheme.typography.h4,
                   text = "Each person pays:"

               )

               val formattedPerPerson = NumberFormat.getCurrencyInstance().format(totalPerPerson)
               Text(

                   style = MaterialTheme.typography.h4,
                   text = "${formattedPerPerson}"

               )

           }

       }

        Card(

            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)

        ) {

            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(10.dp)

            ) {

                Text(

                    style = MaterialTheme.typography.h6,
                    text = "Total Price: "

                )
                val formattedTotal = NumberFormat.getCurrencyInstance().format(totalBill)
                Text(

                    style = MaterialTheme.typography.h4,
                    text = "${formattedTotal}"

                )

            }

        }

    }



}

