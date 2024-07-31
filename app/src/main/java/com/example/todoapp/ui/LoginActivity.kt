package com.example.todoapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityLoginBinding
import com.example.todoapp.util.DialogUtils

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Data binding:bind UI components directly to data sources, and it also
        generates binding classes for the layouts.
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //View Binding: Using the generated binding classes to interact with the views in login.xml files.
        with(binding) {
            inclCompletedTaskToolbar.toolbarTitle.text = getString(R.string.login)
            // Set the text of the button
            inclLoginForm.editTextField1.hint = "Username"
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
            DialogUtils.showAlert(
                context = this, title = "Input Error",
                message = "Username and password cannot be empty"
            )

        } else if (username == "username" && password == "password") {
            // Navigate to the TodoActivity
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        } else {
            // Show an alert dialog for invalid credentials
            DialogUtils.showAlert(
                context = this, title = "Login Failed",
                message = "Invalid username and password"
            )
        }
    }
}
