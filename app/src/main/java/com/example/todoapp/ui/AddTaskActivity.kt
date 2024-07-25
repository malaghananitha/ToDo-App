package com.example.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityAddTaskBinding
import com.example.todoapp.entity.Todo
import com.example.todoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)

        with(binding.inclAddTaskToolbar) {
            toolbarTitle.text = getString(R.string.add_task)
            backpageButtonIcon.visibility = View.VISIBLE
            backpageButtonIcon.setOnClickListener {
                finish()
            }
        }

        with(binding.inclAddTaskForm) {

            editTextField1.hint = "Todo Title"
            editTextField2.hint = "Todo Sub Title"
            buttonAction.text = getString(R.string.add)

            // Set click listener for the button
            buttonAction.setOnClickListener {
                    addTask()
                }
            }

        }

    private fun addTask() {


        val todoTitle: String
        val todoSubTitle: String

        with(binding.inclAddTaskForm) {
            todoTitle = editTextField1.text.toString().trim()
            todoSubTitle = editTextField2.text.toString().trim()
        }

        if (todoTitle.isEmpty() || todoSubTitle.isEmpty()) {
            // Show an alert dialog for empty fields
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Input Error")
            builder.setMessage("todoTitle and todoSubTitle cannot be empty")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val alert = builder.create()
            alert.show()
        } else {
            //Toast.makeText(this, "Working on database to store", Toast.LENGTH_LONG).show()

             lifecycleScope.launch {
                 val status :Long =  async {
                     todoViewModel.insert(
                         Todo(
                             title = todoTitle,
                             subtitle = todoSubTitle,
                             isCompleted = false
                         )
                     )
                 }.await()

                 if (status > 0) {
                     Toast.makeText(
                         this@AddTaskActivity,
                         "Task inserted successfully",
                         Toast.LENGTH_LONG
                     ).show()
                     finish()
                 }
             }





        }


    }

}