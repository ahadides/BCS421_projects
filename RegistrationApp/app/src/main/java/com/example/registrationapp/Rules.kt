package com.example.registrationapp

import Users
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Rules : AppCompatActivity() {
    private lateinit var btnPlay: Button

    private lateinit var welcomeText: TextView
    private lateinit var lastGameResultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        btnPlay = findViewById(R.id.button3)
        welcomeText = findViewById(R.id.textView4)
        lastGameResultText = findViewById(R.id.textView11)
        val user = DataRepository.user
        val userName = user.firstName +" " + user.lastName
        val moneyEarned = user.moneyEarned
        val questionCorrect = user.questionCorrect

        // Display the welcome message with user's name and last game result
        welcomeText.text = "Welcome, $userName!"
        lastGameResultText.text = "Last Game Result: Money Earned - $moneyEarned, Correct Answers - $questionCorrect out of 5"

        btnPlay.setOnClickListener {
            val intent = Intent(this,  Question1::class.java)
            startActivity(intent)
        }
    }
}