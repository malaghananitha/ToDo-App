package com.example.todoapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // Correct the type declaration for views
        val todoActivityView: View = findViewById(R.id.todo_app_toolbar)

        val todoActivityLeftIcon: ImageView = todoActivityView.findViewById(R.id.backpageButtonIcon)
        val todoActivityTitle: TextView = todoActivityView.findViewById(R.id.toolbarTitle)

        // Set the title for the toolbar
        todoActivityLeftIcon.visibility = View.GONE
        todoActivityTitle.text = "TODO APP"


    }
}