package com.example.distanceconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.distanceconvert.ui.theme.DistanceConvertTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempconvert.ViewModelForTemp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistanceConvertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DistanceConvertTheme {
        Greeting("Android")
    }
}

@Composable
fun DropdownDemo(isInput: Boolean) {
    var viewModel: ViewModelForTemp = viewModel()
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Meters", "Kilometers", "Centimeter", "Feet", "Inches", "Miles", "Parsec")
    var selectedIndex by remember { mutableStateOf(0) }
    Box() {
        Text(items[selectedIndex],modifier = Modifier.clickable(onClick = { expanded = true }).background(
            Color.White), fontSize = 30.sp)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.White)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    if(isInput){
                        viewModel.getMeasureIn(selectedIndex)
                    }else{
                        viewModel.getMeasureOut(selectedIndex)
                    }
                }) {
                    Text(text = s, fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var viewModel: ViewModelForTemp = viewModel()

    MainScreen(
        isFah = viewModel.isFah,
        result = viewModel.convertDist,
        convertDist = { viewModel.convert(it)},
        doToggle = { viewModel.doSwitchToggle()}
    )
}

@Composable
fun MainScreen(isFah: Boolean, result: Double,
               convertDist: (String) -> Unit,
               doToggle: () -> Unit) {

    var inputTextState by remember {
        mutableStateOf("")
    }

    val onTextChange = {text : String ->
        inputTextState = text
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        Text("Distance Converter",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.h5
        )

        Card(elevation = 10.dp) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)) {
                OutlinedTextField(
                    value = inputTextState,
                    onValueChange = { onTextChange(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    label = { Text("Enter distance") },
                    modifier = Modifier.padding(12.dp),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),

                    )
            }
        }
        Card(elevation = 10.dp) {
            DropdownDemo(true)
        }

        Text(result.toString(),
            modifier = Modifier.padding(18.dp),
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight(5)
        )
        Card(elevation = 10.dp) {
            DropdownDemo(false)
        }

        Button(onClick = {convertDist(inputTextState)},
            Modifier.padding(20.dp)
        ) {
            Text(text = "Convert")

        }
    }
}
