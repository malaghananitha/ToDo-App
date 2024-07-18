package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        with(binding) {
            inclCompletedTaskToolbar.toolbarTitle.text = getString(R.string.login)
            // Set the text of the button
            inclLoginForm.editTextField1.hint= "Username"
            inclLoginForm.editTextField2.hint = "Password"
            inclLoginForm.buttonAction.text = getString(R.string.login)
            inclLoginForm.buttonAction.setOnClickListener {
                performLogin()
            }
        }


    }

    private fun performLogin() {
        val username: String
        val password: String

        with(binding.inclLoginForm) {
            username = editTextField1.text.toString()
            password = editTextField2.text.toString()
        }

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
