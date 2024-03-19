package com.example.currencyconvert

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var toEuroRadioButton: RadioButton
    private lateinit var toUsdRadioButton: RadioButton
    private lateinit var convertButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        toEuroRadioButton = findViewById(R.id.toEuroRadioButton)
        toUsdRadioButton = findViewById(R.id.toUsdRadioButton)
        convertButton = findViewById(R.id.convertButton)

        convertButton.setOnClickListener {
            val amount = amountEditText.text.toString().toDoubleOrNull()
            if (amount != null) {
                val convertedAmount = if (toEuroRadioButton.isChecked) {
                    convertUsdToEuro(amount)
                } else {
                    convertEuroToUsd(amount)
                }
                showToastIfNeeded(convertedAmount)
            } else {
                showToast("Invalid input")
            }
        }
    }

    private fun convertUsdToEuro(usd: Double): Double {
        // Conversion rate from USD to Euro
        return usd * 0.85 // Assuming the conversion rate is 0.85
    }

    private fun convertEuroToUsd(euro: Double): Double {
        // Conversion rate from Euro to USD
        return euro * (1 / 0.85) // Assuming the conversion rate is 0.85
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showToastIfNeeded(amount: Double) {
        if (amount > 10000.00) {
            val formattedAmount = NumberFormat.getCurrencyInstance().format(amount)
            showToast("Wow! That's over $10,000.00! ($formattedAmount)")
        } else {
            val formattedAmount = NumberFormat.getCurrencyInstance().format(amount)
            showToast(formattedAmount)
        }
    }
}