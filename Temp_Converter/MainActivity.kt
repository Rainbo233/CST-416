package com.example.tempconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempconvert.ui.theme.TempConvertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempConvertTheme {
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
fun MyApp() {
    var viewModel: ViewModelForTemp = viewModel()

    MainScreen(
        isFah = viewModel.isFah,
        result = viewModel.convertTemp,
        convertTemp = { viewModel.calculateConvertion(it)},
        doToggle = { viewModel.doSwitchToggle()}
    )
}

@Composable
fun MainScreen(isFah: Boolean, result: String,
               convertTemp: (String) -> Unit,
               doToggle: () -> Unit) {

    var inputTextState by remember {
        mutableStateOf("")
    }

    val onTextChange = {text : String ->
        inputTextState = text
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        Text("Temperature Converter",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.h5
        )

        Card(elevation = 10.dp) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)){
                Switch(
                    checked = isFah,
                    onCheckedChange = {
                        doToggle()
                    }
                )
                OutlinedTextField(
                    value = inputTextState,
                    onValueChange = { onTextChange(it)},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    label = { Text("Enter degrees")},
                    modifier = Modifier.padding(12.dp),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                    trailingIcon = {
                        if(isFah){
                            Text("\u2109", style = MaterialTheme.typography.h4)
                        } else {
                            Text("\u2103", style = MaterialTheme.typography.h4)
                        }

                    },
                )
            }
        }

        Text(result,
            modifier = Modifier.padding(18.dp),
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight(5)
        )

        Button(onClick = {convertTemp(inputTextState)},
                Modifier.padding(20.dp)
            ) {
            if(isFah){
                Text(text = "Convert to C")
            }else{
                Text(text = "Convert to F")
            }

        }
    }
}

