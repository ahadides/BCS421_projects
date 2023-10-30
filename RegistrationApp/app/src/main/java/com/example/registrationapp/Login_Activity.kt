package com.example.registrationapp

import Users
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login_Activity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passWord: EditText
    private lateinit var bntLogin: Button
    private lateinit var bntSignup: Button
    val registrationRequestCode = 1
    private val usersStore = ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI elements
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passWord = findViewById(R.id.editTextTextPassword)
        bntLogin = findViewById(R.id.button)
        bntSignup = findViewById(R.id.button2)
        loadUsersFromSharedPreferences()
        bntSignup.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, registrationRequestCode)
        }


        bntLogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val pass = passWord.text.toString()

            if (isValidEmail(email) && isValidPassword(pass)) {
                // Check if the email and password match a user in the list
                val user = usersStore.find { it.email == email && it.password == pass }

                if (user != null) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    DataRepository.user = user
                    val intent = Intent(this, Rules::class.java)
                    intent.putExtra("User", user)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid email or password",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == registrationRequestCode && resultCode == Activity.RESULT_OK) {
            // Data is returned from the RegistrationActivity
            val newUser = data?.getSerializableExtra("newUser") as? Users
            if (newUser != null) {
                // Add the new user to the list of all users
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                usersStore.add(newUser)
                storeUserData(newUser) // Store user data in SharedPreferences
            }
        }
    }

    private fun storeUserData(user: Users) {
        // Store user data in SharedPreferences with keys based on email
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("firstName_${user.email}", user.firstName)
        editor.putString("familyName_${user.email}", user.lastName)
        editor.putString("dateOfBirth_${user.email}", user.dateOfBirth)
        editor.putString("email_${user.email}", user.email)
        editor.putString("password_${user.email}", user.password)
        editor.putInt("moneyEarned_${user.email}", user.moneyEarned)
        editor.putInt("questionCorrect_${user.email}", user.questionCorrect)

        editor.apply()
    }
    fun loadUsersFromSharedPreferences() {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Iterate through the SharedPreferences keys
        val sharedPreferencesKeys = sharedPreferences.all.keys
        for (key in sharedPreferencesKeys) {
            if (key.startsWith("email_")) {
                val email = key.substring("email_".length) // Extract email from the key
                val firstName = sharedPreferences.getString("firstName_$email", "")
                val familyName = sharedPreferences.getString("familyName_$email", "")
                val dateOfBirth = sharedPreferences.getString("dateOfBirth_$email", "")
                val password = sharedPreferences.getString("password_$email", "")
                val moneyEarned = sharedPreferences.getInt("moneyEarned_$email", 0)
                val questionCorrect = sharedPreferences.getInt("questionCorrect_$email", 0)
                if (!firstName.isNullOrEmpty() && !familyName.isNullOrEmpty() && !dateOfBirth.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    val user = Users(firstName, familyName, dateOfBirth, email, password,moneyEarned,questionCorrect)
                    usersStore.add(user)
                    Toast.makeText(this, "Users loaded", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }



    private fun isValidEmail(email: String): Boolean {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Define your password validation rules here
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()

        return passwordPattern.matches(password)
    }
}
