package com.example.tempconvert

import android.icu.util.Measure
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class ViewModelForTemp : ViewModel() {
    var isFah by mutableStateOf(true)

    var convertTemp by mutableStateOf("")

    var measureIn by mutableStateOf(0)
    var measureOut by mutableStateOf(0)

    val distConvert = listOf(1.0, 1000.0, .01, 0.3048, 0.0254, 1609.34, 299792458.0 )
    var convertDist by mutableStateOf(.0)


    fun doSwitchToggle(){
        isFah = !isFah
    }

    fun calculateConvertion(inputValue: String){

        try{
            val temp = inputValue.toInt()

            if (isFah){
                convertTemp = ((temp - 32) * 5/9.0).roundToInt().toString() + "\u2103"
            }else{
                convertTemp = ((temp * 9/5f) + 32).roundToInt().toString() + "\u2109"
            }
        } catch (e: Exception){
            convertTemp = "Error"
        }

    }

    fun getMeasureIn(m: Int){
        measureIn = m
    }

    fun getMeasureOut(m: Int){
        measureOut = m
    }

    fun convert(inputValue: String){
        try{
            val dist = inputValue.toDouble()
            convertDist = dist * (distConvert[measureIn] / distConvert[measureOut])

        } catch (e: Exception){
            convertDist = -1.0
        }
    }
}
