package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.dao.TodoDao
import com.example.todoapp.entity.Todo


@Database(entities = [Todo:: class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): TodoDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, TodoDatabase::class.java,
                    "todo_database"
                )
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}