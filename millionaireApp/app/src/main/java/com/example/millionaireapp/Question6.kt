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

class Question6 : AppCompatActivity() {
    lateinit var btnConfirm: Button
    lateinit var radioGroup: RadioGroup
    lateinit var correctAnswer: RadioButton
    lateinit var youEarnedText: TextView
    var youEarnedAmount: Int = 0
    var questionCorrect: Int = 0
    var previouslySelectedRB: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question6)

        btnConfirm = findViewById(R.id.button)
        radioGroup = findViewById(R.id.radio_group)
        correctAnswer = findViewById(R.id.radioButton5)
        youEarnedText = findViewById(R.id.textView)
        youEarnedAmount = intent.getIntExtra("youEarned", 0)
        questionCorrect = intent.getIntExtra("questionCorrect", 0)
        youEarnedText.text = "You Earned: $ $youEarnedAmount"
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Highlight the selected radio button immediately
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            if (previouslySelectedRB != null) {
                // Reset the color of the previously selected radio button
                previouslySelectedRB!!.setTextColor(Color.BLACK)
            }

            selectedRadioButton.setTextColor(Color.BLUE) // You can change this color
            previouslySelectedRB = selectedRadioButton

        }

        btnConfirm.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

            if (selectedRadioButtonId == correctAnswer.id) {
                youEarnedAmount += 350
                youEarnedText.text = "You Earned: $ $youEarnedAmount"
                questionCorrect+=1
                Toast.makeText(this, "This is the Correct answer, you earned $1550", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, Question7::class.java)
            intent.putExtra("youEarned", youEarnedAmount)
            intent.putExtra("questionCorrect",questionCorrect)
            startActivity(intent)
            finish()
        }
    }
}