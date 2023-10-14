package com.example.millionaireapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class Question4 : AppCompatActivity() {
    lateinit var btnConfirm: Button // Button for confirming the answer
    lateinit var radioGroup: RadioGroup // RadioGroup containing answer options
    lateinit var correctAnswer: RadioButton // The correct answer RadioButton
    lateinit var youEarnedText: TextView // TextView to display the earnings
    var youEarnedAmount: Int = 0 // Variable to track the total earnings
    var questionCorrect: Int = 0 // Variable to count the correct answers
    var previouslySelectedRB: RadioButton? = null // Track the previously selected RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question4) // Set the activity layout

        // Initialize UI elements by finding them in the layout XML
        btnConfirm = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radio_group)
        correctAnswer = findViewById(R.id.radioButton2)
        youEarnedText = findViewById(R.id.textView)

        // Retrieve earned amount and correct answers count from the previous question
        youEarnedAmount = intent.getIntExtra("youEarned", 0)
        questionCorrect = intent.getIntExtra("questionCorrect", 0)

        // Display the updated earnings
        youEarnedText.text = "You Earned: $ $youEarnedAmount"

        // Set up a listener for when radio buttons in the group are checked
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Highlight the selected radio button immediately
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            if (previouslySelectedRB != null) {
                // Reset the color of the previously selected radio button
                previouslySelectedRB!!.setTextColor(Color.BLACK)
            }

            selectedRadioButton.setTextColor(Color.BLUE) // Change the text color of the selected radio button
            previouslySelectedRB = selectedRadioButton
        }

        // Set up a click listener for the "Confirm" button
        btnConfirm.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

            // Check if the selected answer is correct
            if (selectedRadioButtonId == correctAnswer.id) {
                youEarnedAmount += 350 // Increase earnings for a correct answer
                youEarnedText.text = "You Earned: $ $youEarnedAmount" // Update earnings display
                questionCorrect += 1 // Increment the count of correct answers
                Toast.makeText(this, "This is the Correct answer, you earned $850", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }

            // Create an Intent to navigate to the next question (Question5 activity)
            val intent = Intent(this, Question5::class.java)
            intent.putExtra("youEarned", youEarnedAmount) // Pass the updated earned amount
            intent.putExtra("questionCorrect", questionCorrect) // Pass the count of correct answers
            startActivity(intent) // Start the next activity
            finish() // Finish the current activity to prevent going back
        }
    }
}