package com.example.todoapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.databinding.ActivityCompletedTasksBinding
import com.example.todoapp.entity.Todo
import com.example.todoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompletedTasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompletedTasksBinding
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var completedTodoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_completed_tasks)

        setupToolbar()
        setupRecyclerView()
        observeCompletedTodos()
    }

    private fun setupToolbar() {
        with(binding.inclCompletedTaskToolbar) {
            toolbarTitle.text = getString(R.string.completed_tasks_title)
            backpageButtonIcon.visibility = View.VISIBLE
            backpageButtonIcon.setOnClickListener {
                finish()
            }
        }
    }

    private fun setupRecyclerView() {


        completedTodoAdapter = TodoAdapter(
            onEditClick = { /* Handle edit click if necessary */ },
            onDeleteClick = { todo ->
                // Handle delete click
                lifecycleScope.launch(Dispatchers.IO) {
                    todoViewModel.delete(todo)
                    withContext(Dispatchers.Main) {
                        val position = completedTodoAdapter.currentList.indexOf(todo as Todo)
                        if (position != -1) {
                            completedTodoAdapter.notifyItemRemoved(position)
                        }
                    }
                }
            },
            onCompleteClick = {   /* Handle complete click if necessary */ }
        )
        binding.recyclerViewCompletedTasks.apply {
            adapter = completedTodoAdapter
            layoutManager = LinearLayoutManager(this@CompletedTasksActivity)
        }
    }

    private fun observeCompletedTodos() {
        todoViewModel.getCompletedTodos().observe(this, Observer { completedTodos ->
            completedTodos?.let {
                completedTodoAdapter.submitList(it)
            }
        })
    }
}
