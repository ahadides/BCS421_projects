package com.example.registrationapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Question3 : AppCompatActivity() {
    lateinit var btnConfirm: Button
    lateinit var radioGroup: RadioGroup
    lateinit var correctAnswer: RadioButton
    lateinit var youEarnedText: TextView
    var youEarnedAmount: Int = 0
    var questionCorrect: Int = 0
    var previouslySelectedRB: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question3)

        // Initialize UI elements
        btnConfirm = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radio_group)
        correctAnswer = findViewById(R.id.radioButton4)
        youEarnedText = findViewById(R.id.textView)

        // Retrieve data from the previous question
        questionCorrect = intent.getIntExtra("questionCorrect", 0)
        youEarnedAmount = intent.getIntExtra("youEarned", 0)

        // Display the updated earnings
        youEarnedText.text = "You Earned: $ $youEarnedAmount"

        // Set up a listener for when radio buttons in the group are checked
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            if (previouslySelectedRB != null) {
                previouslySelectedRB!!.setBackgroundColor(Color.WHITE)
            }

            selectedRadioButton.setBackgroundColor(Color.YELLOW)
            previouslySelectedRB = selectedRadioButton
        }

        // Set up a click listener for the "Confirm" button
        btnConfirm.setOnClickListener {
            // Create a confirmation dialog
            val alertDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.activity_confirmation_dialog, null)

            val confirmationMessage = dialogView.findViewById<TextView>(R.id.confirmationMessage)
            val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)

            confirmationMessage.text = "Are you sure you want to confirm your answer?"

            val dialog = alertDialog.setView(dialogView).create()

            confirmButton.setOnClickListener {
                // Handle the confirmation
                handleConfirmation()
                dialog.dismiss()
            }

            cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun handleConfirmation() {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

        if (selectedRadioButtonId == correctAnswer.id) {
            // Update earnings for a correct answer
            youEarnedAmount += 500
            youEarnedText.text = "You Earned: $ $youEarnedAmount"
            questionCorrect++
            Toast.makeText(this, "This is the Correct answer, you earned $500", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
        }

        // Create an Intent to navigate to the next question (Question4 activity)
        val intent = Intent(this, Question4::class.java)
        intent.putExtra("youEarned", youEarnedAmount)
        intent.putExtra("questionCorrect", questionCorrect)
        startActivity(intent)
        finish() // Finish the current activity to prevent going back
    }
}
