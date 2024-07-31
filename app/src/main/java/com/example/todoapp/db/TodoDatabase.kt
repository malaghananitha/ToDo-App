package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.dao.TodoDao
import com.example.todoapp.entity.Todo


@Database(entities = [Todo:: class], version = 2)
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
                    .addMigrations(MIGRATION_1_2)
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
// Define the migration from version 1 to version 2
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add the new column 'notes' to the 'todo_table'
        database.execSQL("ALTER TABLE todo_table ADD COLUMN notes TEXT")
    }
}