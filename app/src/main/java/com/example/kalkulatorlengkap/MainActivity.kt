package com.example.kalkulatorlengkap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var lastNumber = ""
    private var lastOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Set number buttons
        setNumberButtonListeners()

        // Set operator buttons
        setOperatorButtonListeners()
    }

    private fun setNumberButtonListeners() {
        val numberButtons = listOf<Button>(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        for (button in numberButtons) {
            button.setOnClickListener { onNumberClicked(it) }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener { onDotClicked() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClearClicked() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { onDeleteClicked() }
    }

    private fun setOperatorButtonListeners() {
        val operatorButtons = listOf<Button>(
            findViewById(R.id.btnPlus),
            findViewById(R.id.btnMinus),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDivide)
        )

        for (button in operatorButtons) {
            button.setOnClickListener { onOperatorClicked(it) }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { onEqualClicked() }
    }

    private fun onNumberClicked(view: View) {
        val button = view as Button
        currentInput += button.text
        updateDisplay(currentInput)
    }

    private fun onDotClicked() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            updateDisplay(currentInput)
        }
    }

    private fun onOperatorClicked(view: View) {
        val button = view as Button
        if (currentInput.isNotEmpty()) {
            lastNumber = currentInput
            currentInput = ""
            lastOperator = button.text.toString()
        }
    }

    private fun onEqualClicked() {
        if (lastNumber.isNotEmpty() && currentInput.isNotEmpty()) {
            val result = performCalculation(lastNumber.toDouble(), currentInput.toDouble(), lastOperator)
            updateDisplay(result.toString())
            currentInput = result.toString()
            lastNumber = ""
            lastOperator = ""
        }
    }

    private fun onClearClicked() {
        currentInput = ""
        lastNumber = ""
        lastOperator = ""
        updateDisplay("0")
    }

    private fun onDeleteClicked() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            updateDisplay(if(currentInput.isEmpty()) "0" else currentInput)
        }
    }

    private fun updateDisplay(value: String) {
        tvDisplay.text = value
    }

    private fun performCalculation(num1: Double, num2: Double, operator: String): Double {
        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else 0.0
            else -> 0.0
        }
    }
}