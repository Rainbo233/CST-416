package com.example.dateconversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dateconversion.ui.theme.DateConversionTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateConversionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DateConverter()
                }
            }
        }
    }
}

@Composable
fun DateConverter() {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val (date1, setDate1) = remember { mutableStateOf("") }
    val (date2, setDate2) = remember { mutableStateOf("") }
    val (result, setResult) = remember { mutableStateOf("") }
    Column {
        Text("Enter two dates to calculate the difference in days, weeks, months, and years")
        Text("Date 1 (dd/MM/yyyy):")
        TextField(
            value = date1,
            onValueChange = { setDate1(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Date 2 (dd/MM/yyyy):")
        TextField(
            value = date2,
            onValueChange = { setDate2(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val parsedDate1 = dateFormat.parse(date1)
                val parsedDate2 = dateFormat.parse(date2)
                val days = ((parsedDate2.time - parsedDate1.time) / (1000 * 60 * 60 * 24)).toInt()
                val weeks = days / 7

                // Calculate difference in months and years using Calendar
                val cal1 = Calendar.getInstance()
                cal1.time = parsedDate1
                val cal2 = Calendar.getInstance()
                cal2.time = parsedDate2
                var months = 0
                var years = 0
                if (cal1.before(cal2)) {
                    while (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR) ||
                        (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                                cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH))) {
                        cal1.add(Calendar.MONTH, 1)
                        months++
                        if (cal1.get(Calendar.MONTH) == 0) {
                            years++
                        }
                    }
                } else if (cal1.after(cal2)) {
                    while (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR) ||
                        (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                                cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH))) {
                        cal1.add(Calendar.MONTH, -1)
                        months--
                        if (cal1.get(Calendar.MONTH) == 11) {
                            years--
                        }
                    }
                }

                setResult("The difference between $date1 and $date2 is: $days days, $weeks weeks, $months months, and $years years.")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Text(result)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DateConversionTheme {
        DateConverter()
    }
}
