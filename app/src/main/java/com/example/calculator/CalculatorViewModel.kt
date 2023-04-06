package com.example.calculator

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())


    fun onAction(action: CalculatorAction) {

        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.PlusOrMinus -> plusOrMinus()

        }
    }

    private fun plusOrMinus() {
        if (state.number1.isNotBlank()){
            state = if (state.number1.startsWith("-")) {
                state.copy(number1 = state.number1.substring(1))

            }else{
                state.copy(number1 = "-" + state.number1)
            }
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }


    private fun calculate() {
        val number1 = state.number1.replace(",", ".").toDoubleOrNull()
        val number2 = state.number2.replace(",", ".").toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15).replace(".", ","),
                number2 = "",
                operation = null
            )
        }

    }

    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> {
                state = if (state.number1.length == 1) {
                    state.copy(number1 = "0")
                } else {
                    state.copy(number1 = state.number1.dropLast(1))
                }
            }
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(",") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + ","
            )
            return
        } else if(!state.number2.contains(",") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + ","
            )
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null) {
            if (state.number1 == "0" || state.number1 == "-0") {
                state = state.copy(
                    number1 = if (number != 0) number.toString() else state.number1
                )
            } else if (state.number1 == "-") {
                state = state.copy(
                    number1 = "-$number"
                )
            } else if(state.number1.length < MAX_NUM_LENGTH) {
                state = state.copy(
                    number1 = state.number1 + number
                )
            }
        } else if(state.number2.length < MAX_NUM_LENGTH) {
            state = state.copy(
                number2 = state.number2 + number
            )
        }
    }






    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}