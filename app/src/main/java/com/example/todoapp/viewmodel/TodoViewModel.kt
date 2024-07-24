package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.TodoDatabase
import com.example.todoapp.entity.Todo
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoDao = TodoDatabase.getInstance(application).todoDao()
    private val repository = TodoRepository(application)

    // LiveData for observing todos
    private val _allTodos = MutableLiveData<List<Todo>>()
    val allTodos: LiveData<List<Todo>> get() = _allTodos


    fun fetchAllTodos(lifecycle: LifecycleOwner) {
        viewModelScope.launch {
            repository.getAllTodos().observe(lifecycle) {
                _allTodos.postValue(it)
            }

        }
    }
    fun fetchAllUncompletedTodos(lifecycle: LifecycleOwner) {
        viewModelScope.launch {
            repository.getUncomletedTodos().observe(lifecycle) {
                _allTodos.postValue(it)
            }
        }
    }

    suspend fun insert(todo: Todo) =
        viewModelScope.async {
            repository.insert(todo)
        }.await()

    suspend fun delete(position: Int):Int =
        viewModelScope.async {
            repository.delete(_allTodos.value!![position])

        }.await()


    suspend fun update(todo: Todo): Int =
        viewModelScope.async {
            repository.updateTask(todo)
        }.await()

    fun getCompletedTodos(): LiveData<List<Todo>> {
        return todoDao.getCompletedTodos()
    }


}


private fun <T> MutableLiveData<T>.postValue(allTodos: LiveData<T>) {

}