package com.example.todoapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "todo_table")
data class Todo(
                @PrimaryKey(autoGenerate = true) val id: Long = 0,
                val title: String,
                val subtitle: String,
                val isCompleted: Boolean,
                val timestamp:Long = System.currentTimeMillis()

): Serializable