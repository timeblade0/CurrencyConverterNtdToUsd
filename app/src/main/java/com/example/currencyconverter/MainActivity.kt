package com.example.currencyconverter

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import android.widget.TableRow.LayoutParams

// launch app and run /res/layout/activity_main.xml
// /res/layout/activity_main.xml = R.layout.activity_main

class MainActivity : ComponentActivity() {
    // define exchange rate
    private val exchangeRate = 32

    // perform conversion function definition
    private fun performConversion() {
        // Get the value from the EditText
        val ntdAmount = findViewById<EditText>(R.id.textInput).text.toString().toDoubleOrNull()

        // Check if the input is valid
        if (ntdAmount != null) {
            // Perform the conversion
            val usdAmount = ntdAmount / exchangeRate
            val formattedUsdAmount = String.format("%.2f", usdAmount)
            val formattedNtdAmount = String.format("%.0f", ntdAmount)

            val historyTableLayout = findViewById<TableLayout>(R.id.historyTableLayout)
            val tableRow = TableRow(this)
            tableRow.addView(TextView(this).apply {
                text = "NTD: $$formattedNtdAmount"
                layoutParams = TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
                textSize = 24f
            })
            tableRow.addView(TextView(this).apply {
                text = "USD: $$formattedUsdAmount"
                layoutParams = TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
                textSize = 24f
            })

            historyTableLayout.addView(tableRow, 0)

        } else {
            // Handle invalid input (e.g., show an error message)
            Toast.makeText(this, "Please enter a valid NTD amount", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define what enter key does
        val ntdEditText = findViewById<EditText>(R.id.textInput)
        ntdEditText.requestFocus()
        ntdEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performConversion()
                ntdEditText.text.clear()
                true
            } else {
                false
            }
        }
    }
}
