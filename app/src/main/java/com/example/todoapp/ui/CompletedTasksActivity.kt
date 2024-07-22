package com.example.todoapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityCompletedTasksBinding

class CompletedTasksActivity : AppCompatActivity() {
    //2
    private lateinit var binding: ActivityCompletedTasksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //3
        binding = DataBindingUtil.setContentView(this, R.layout.activity_completed_tasks)

        binding.inclCompletedTaskToolbar.toolbarTitle.text = getString(R.string.completed_tasks_title)
        binding.inclCompletedTaskToolbar.backpageButtonIcon.visibility = View.VISIBLE

        binding.inclCompletedTaskToolbar.backpageButtonIcon.setOnClickListener{
            finish()
        }

    }
}
