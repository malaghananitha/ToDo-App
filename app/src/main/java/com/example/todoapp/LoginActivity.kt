package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.editTextField1)
        val passwordEditText = findViewById<EditText>(R.id.editTextField2)
        val loginButton = findViewById<Button>(R.id.buttonAction)

        // Set the text of the button
        loginButton.text = "Login"

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                // Show an alert dialog for empty fields
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Input Error")
                builder.setMessage("Username and password cannot be empty")
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.show()
            } else if (username == "username" && password == "password") {
                // Navigate to the TodoActivity
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
            } else {
                // Show an alert dialog for invalid credentials
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Login Failed")
                builder.setMessage("Invalid username and password")
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.show()
            }
        }
    }
}
