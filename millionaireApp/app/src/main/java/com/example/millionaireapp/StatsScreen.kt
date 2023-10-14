package com.example.millionaireapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StatsScreen : AppCompatActivity() {
    lateinit var playAgainButton: Button
    lateinit var youEarnedText: TextView
    lateinit var questionsResultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_screen)

        playAgainButton = findViewById(R.id.button2)
        youEarnedText = findViewById(R.id.textView)
        questionsResultText = findViewById(R.id.textView2)

        val youEarnedAmount = intent.getIntExtra("youEarned", 0)
        val questionCorrect = intent.getIntExtra("questionCorrect", 0)

        // Display the total earnings and the number of correct questions in your TextViews
        youEarnedText.text = "You Earned: $ $youEarnedAmount"
        questionsResultText.text = "Questions results: $questionCorrect out of 7"

        // Set up a click listener for the "Play Again" button
        playAgainButton.setOnClickListener {
            // Start the first question activity
            val intent = Intent(this, Question1::class.java)
            startActivity(intent)
            finish()
        }
    }
}