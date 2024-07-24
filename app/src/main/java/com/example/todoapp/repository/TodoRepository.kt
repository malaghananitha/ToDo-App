package com.example.todoapp.repository


import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoapp.db.TodoDatabase
import com.example.todoapp.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(application: Application) {

    private val todoDao = TodoDatabase.getInstance(application).todoDao()


    suspend fun insert(todo: Todo): Long = withContext(Dispatchers.IO) {
        return@withContext todoDao.insert(todo)
    }

    suspend fun updateTask(todo: Todo):Int = withContext(Dispatchers.IO) {
        return@withContext todoDao.updateTask(
            id = todo.id,
            title = todo.title,
            subtitle = todo.subtitle,
            isCompleted = todo.isCompleted,
            timestamp = todo.timestamp
        )
    }

    suspend fun delete(todo: Todo): Int = withContext(Dispatchers.IO) {
        return@withContext todoDao.delete(todo)

    }

    suspend fun getAllTodos(): LiveData<List<Todo>>{
        return todoDao.getAllTodos()

    }

    fun getUncomletedTodos():LiveData<List<Todo>> {
        return todoDao.getUnCompletedTodos()

    }
}