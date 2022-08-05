package com.rafayet.paper.Database

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafayet.paper.Dao.NotesDao
import com.rafayet.paper.Model.Notes

@androidx.room.Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE:Database?=null

        fun getDatabaseInstance(context: Context):Database{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.
                databaseBuilder(context, Database::class.java, "Notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}