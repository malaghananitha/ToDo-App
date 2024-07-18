package com.example.todoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompletedTasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_tasks)

        findViewById<TextView>(R.id.toolbarTitle).text = getString(R.string.completed_tasks_title)
    }
}
