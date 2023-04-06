package com.example.calculator

data class CalculatorState(
    var number1: String = "0",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
