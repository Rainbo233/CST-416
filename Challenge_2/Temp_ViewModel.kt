package com.example.challenge_2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt


class TempViewModel : ViewModel() {

    var isF by mutableStateOf(true)

    var convertedTemp by mutableStateOf("")
    var m1 by mutableStateOf(0)
    var m2 by mutableStateOf(0)
    val distList = listOf(1.0, 1000.0, .01, 0.3048, 0.0254, 1609.34, 299792458.0 )
    var convertedDist by mutableStateOf(.0)

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

    fun getM1(m: Int){
        m1 = m
    }

    fun getM2(m: Int){
        m2 = m
    }

    fun convert(inputValue: String){
        convertedDist = try{
            val dist = inputValue.toDouble()
            dist * (distList[m1] / distList[m2])

        } catch (e: Exception){
            -1.0
        }
    }

}
