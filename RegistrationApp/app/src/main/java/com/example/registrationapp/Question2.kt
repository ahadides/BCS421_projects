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

class Question2 : AppCompatActivity() {
    lateinit var btnConfirm: Button
    lateinit var radioGroup: RadioGroup
    lateinit var correctAnswer: RadioButton
    lateinit var youEarnedText: TextView
    var youEarnedAmount: Int = 0
    var questionCorrect: Int = 0
    var previouslySelectedRB: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)

        btnConfirm = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radio_group)
        correctAnswer = findViewById(R.id.radioButton3)
        youEarnedText = findViewById(R.id.textView)

        youEarnedAmount = intent.getIntExtra("youEarned", 0)
        questionCorrect = intent.getIntExtra("questionCorrect", 0)

        youEarnedText.text = "You Earned: $ $youEarnedAmount"

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            if (previouslySelectedRB != null) {
                previouslySelectedRB!!.setBackgroundColor(Color.WHITE)
            }

            selectedRadioButton.setBackgroundColor(Color.YELLOW)
            previouslySelectedRB = selectedRadioButton
        }

        btnConfirm.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.activity_confirmation_dialog, null)

            val confirmationMessage = dialogView.findViewById<TextView>(R.id.confirmationMessage)
            val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)

            confirmationMessage.text = "Are you sure you want to confirm your answer?"

            val dialog = alertDialog.setView(dialogView).create()

            confirmButton.setOnClickListener {
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
            youEarnedAmount += 250
            youEarnedText.text = "You Earned: $ $youEarnedAmount"
            questionCorrect++
            Toast.makeText(this, "This is the Correct answer, you earned $250", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, Question3::class.java)
        intent.putExtra("youEarned", youEarnedAmount)
        intent.putExtra("questionCorrect", questionCorrect)
        startActivity(intent)
        finish()
    }
}
