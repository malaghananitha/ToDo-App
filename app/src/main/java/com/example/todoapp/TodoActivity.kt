package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapp.databinding.ActivityTodoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo)


        //Section 1: Toolbar related code
        with(binding.inclTodoAppToolbar){
            toolbarTitle.text = getString(R.string.todo_title)
            calendarIcon.visibility = View.VISIBLE
        }
        //Section 2: Recycler view related code for adding the tasks


        //Section 3: Floating action button related code for adding items
        binding.fabAddTask.setOnClickListener{
            addItem()
        }

        //Section 4: for bottom navigation related code
        with(binding.bottomNavigationTodo){
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_all -> {
                        // Show all the tasks
                        true
                    }
                    R.id.navigation_completed -> {
                        // Start CompletedTaskActivity
                        val intent = Intent(this@TodoActivity, CompletedTasksActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
                return@setOnItemSelectedListener true
            }

        }

    }

    private fun addItem() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivity(intent)

    }
}