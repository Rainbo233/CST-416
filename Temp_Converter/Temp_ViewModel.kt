package com.example.temperature_conversion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt


class TempViewModel : ViewModel() {

    var isF by mutableStateOf(true)

    var convertedTemp by mutableStateOf("")

    fun doSwitchToggle(){

        isF != isF

    }

    fun calculateConversion(inputValue: String){

        convertedTemp = try{

            val temp = inputValue.toInt()

            if(isF){
                ((temp - 32) * 5/9.0).roundToInt().toString() + "\u2103"
            } else {
                ((temp * 9/5f) + 32).roundToInt().toString() + "\u2109"
            }

        } catch (e: Exception){

            "Error"

        }

    }

}

