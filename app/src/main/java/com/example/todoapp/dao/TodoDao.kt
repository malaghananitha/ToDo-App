package com.example.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo):Long

    @Query("UPDATE todo_table SET title = :title, subtitle = :subtitle, isCompleted = :isCompleted, timestamp = :timestamp WHERE id = :id")
    fun updateTask(id: Long, title: String, subtitle: String, isCompleted: Boolean, timestamp: Long): Int

    @Delete
    fun delete(todo:Todo):Int

    @Query("delete from todo_table")
    fun deleteAllTodos()

    @Query("select * from todo_table order by timestamp")
    fun getAllTodos():LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE isCompleted = 1 ORDER BY timestamp")
    fun getCompletedTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE isCompleted = 0 ORDER BY timestamp")
    fun getUnCompletedTodos(): LiveData<List<Todo>>

}