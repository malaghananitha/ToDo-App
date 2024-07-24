package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.entity.Todo

class TodoAdapter(
    private val onEditClick: (Todo) -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onCompleteClick: (Todo) -> Unit
) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo, onEditClick, onDeleteClick, onCompleteClick, position)
    }

    class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            todo: Todo,
            onEditClick: (Todo) -> Unit,
            onDeleteClick: (Int) -> Unit,
            onCompleteClick: (Todo) -> Unit,
            position: Int
        ) {
            binding.data = todo
            binding.tvTodoTitle.text = todo.title
            binding.tvTodoSubTitle.text = todo.subtitle
            binding.buttonEdit.setOnClickListener { onEditClick(todo) }
            binding.buttonDelete.setOnClickListener { onDeleteClick(position) }
            binding.buttonCompleted.setOnClickListener { onCompleteClick(todo) }

            if (todo.isCompleted) {
                //binding.root.alpha = 0.7f // Grayout the task
                //binding.root.setBackgroundColor(binding.root.context.getColor(com.google.android.material.R.color.material_grey_600))
                binding.buttonCompleted.visibility = View.GONE // Hide the complete button
            } else {
               // binding.root.alpha = 1.0f
                binding.buttonCompleted.visibility = View.VISIBLE
            }

            binding.executePendingBindings()
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}
