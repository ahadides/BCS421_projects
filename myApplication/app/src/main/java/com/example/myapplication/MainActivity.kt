package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var isClearButton = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val computeButton = findViewById<Button>(R.id.computeButton)
        val gpaDisplay = findViewById<TextView>(R.id.gpaDisplay)
        val rootDisplay = findViewById<LinearLayout>(R.id.Root)
        val course1Grade = findViewById<EditText>(R.id.course1Grade)
        val course2Grade = findViewById<EditText>(R.id.course2Grade)
        val course3Grade = findViewById<EditText>(R.id.course3Grade)
        val course4Grade = findViewById<EditText>(R.id.course4Grade)
        val course5Grade = findViewById<EditText>(R.id.course5Grade)

         computeButton.setOnClickListener(View.OnClickListener {
            // Calculate GPA here
            if(isClearButton) {
                findViewById<EditText>(R.id.course1Grade).text.clear()
                findViewById<EditText>(R.id.course2Grade).text.clear()
                findViewById<EditText>(R.id.course3Grade).text.clear()
                findViewById<EditText>(R.id.course4Grade).text.clear()
                findViewById<EditText>(R.id.course5Grade).text.clear()
                rootDisplay.setBackgroundColor(Color.WHITE)
                "Compute GPA".also { computeButton.text = it }
                "GPA: ".also { gpaDisplay.text = it }
            }else {
                val grade1 = course1Grade.text.toString().toDoubleOrNull()
                val grade2 = course2Grade.text.toString().toDoubleOrNull()
                val grade3 = course3Grade.text.toString().toDoubleOrNull()
                val grade4 = course4Grade.text.toString().toDoubleOrNull()
                val grade5 = course5Grade.text.toString().toDoubleOrNull()





                // Check for empty or invalid fields
                if (grade1 == null) {
                    course1Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error:Missing Course Grade", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course1Grade.setBackgroundColor(Color.TRANSPARENT)
                }

                if (grade2 == null) {
                    course2Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error:Missing Course Grade", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course2Grade.setBackgroundColor(Color.TRANSPARENT)
                }

                if (grade3 == null) {
                    course3Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error:Missing Course Grade", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course3Grade.setBackgroundColor(Color.TRANSPARENT)
                }

                if (grade4 == null) {
                    course4Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error:Missing Course Grade", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course4Grade.setBackgroundColor(Color.TRANSPARENT)
                }

                if (grade5 == null) {
                    course5Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: Missing Course Grade", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                if (course1Grade.length() > 2) {
                    course1Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: More than two digits", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                if (course2Grade.length() > 2) {
                    course2Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: More than two digits", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                if (course3Grade.length() > 2) {
                    course3Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: More than two digits", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                if (course4Grade.length() > 2) {
                    course4Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: More than two digits", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                if (course5Grade.length() > 2) {
                    course5Grade.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Error: More than two digits", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                } else {
                    course5Grade.setBackgroundColor(Color.TRANSPARENT)
                }
                //GPA calulator
                val totalGPA = (grade1!! + grade2!! + grade3!! + grade4!! + grade5!!) / 5.0
                "Clear Form".also { computeButton.text = it }
                // Display GPA
                "GPA: $totalGPA".also { gpaDisplay.text = it }

                // Set background color based on GPA
                when {
                    totalGPA < 60 -> rootDisplay.setBackgroundColor(Color.RED)
                    totalGPA in 61.0..79.0 -> rootDisplay.setBackgroundColor(Color.YELLOW)
                    totalGPA in 80.0..100.0 -> rootDisplay.setBackgroundColor(Color.GREEN)
                    else -> rootDisplay.setBackgroundColor(Color.WHITE)
                }

            }

             isClearButton = !isClearButton
             computeButton.text = if (isClearButton) "Clear Form" else "Compute GPA"
        })
    }
}