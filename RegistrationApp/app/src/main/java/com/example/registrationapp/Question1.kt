package com.example.registrationapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Question1 : AppCompatActivity() {
    // Declare variables for UI elements
    lateinit var btnConfirm: Button
    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton
    lateinit var radioButton3: RadioButton
    lateinit var radioButton4: RadioButton
    lateinit var youEarnedText: TextView

    var youEarnedAmount: Int = 0
    var questionCorrect: Int = 0

    var radioButtons: List<RadioButton> = ArrayList() // Create a list of RadioButton elements

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        // Initialize UI elements
        btnConfirm = findViewById(R.id.confirmButton)
        radioButton1 = findViewById(R.id.radioButton1)
        radioButton2 = findViewById(R.id.radioButton2)
        radioButton3 = findViewById(R.id.radioButton3)
        radioButton4 = findViewById(R.id.radioButton4)
        youEarnedText = findViewById(R.id.textView)

        // Add radio buttons to the list
        radioButtons = listOf(radioButton1, radioButton2, radioButton3, radioButton4)

        // Set up a click listener for each radio button
        for (radioButton in radioButtons) {
            radioButton.setOnClickListener {
                // Highlight the background of the selected radio button
                radioButton.setBackgroundColor(Color.YELLOW)
            }
        }

        // Set up a click listener for the "Confirm" button
        // Set up a click listener for the "Confirm" button
        btnConfirm.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.activity_confirmation_dialog, null)

            // Get references to the dialog elements
            val confirmationMessage = dialogView.findViewById<TextView>(R.id.confirmationMessage)
            val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)

            // Set the message in the dialog
            confirmationMessage.text = "Are you sure you want to confirm your answer?"

            // Create the actual AlertDialog instance
            val dialog = alertDialog.setView(dialogView).create()

            // Set up a click listener for the "Confirm" button in the dialog
            confirmButton.setOnClickListener {
                // Handle the confirmation here
                handleConfirmation()
                dialog.dismiss() // Call dismiss on the dialog instance
            }

            // Set up a click listener for the "Cancel" button in the dialog
            cancelButton.setOnClickListener {
                dialog.dismiss() // Call dismiss on the dialog instance
            }

            dialog.show() // Show the dialog
        }

    }

    private fun handleConfirmation() {
        var correctAnswers = 0
        var incorrectAnswers = 0

        // Check each radio button's state
        for (radioButton in radioButtons) {
            if (radioButton.isChecked) {
                // Check if the selected answer is correct
                if ((radioButton.id == R.id.radioButton2) || (radioButton.id == R.id.radioButton3)) {
                    correctAnswers++
                } else {
                    incorrectAnswers++
                }
            }
        }

        // Check if at least one correct answer was selected
        if (correctAnswers > 0 && incorrectAnswers == 0) {
            youEarnedAmount += 100
            youEarnedText.text = "You Earned: $ $youEarnedAmount"
            questionCorrect++
            Toast.makeText(this, "This is the Correct answer, you earned $100", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
        }

        // Create an Intent to navigate to the next question (Question2 activity)
        val intent = Intent(this, Question2::class.java)
        intent.putExtra("youEarned", youEarnedAmount)
        intent.putExtra("questionCorrect", questionCorrect)
        startActivity(intent)
        finish()
    }
}
