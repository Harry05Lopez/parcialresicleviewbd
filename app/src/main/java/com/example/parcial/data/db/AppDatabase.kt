package com.example.parcial.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parcial.data.model.UserModel
import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Room


@Database(entities = [UserModel::class], version = 2, exportSchema = false)

abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao

    companion object {
        @Volatile
        private  var INSTANCE:AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "base_database"
                ).build()
                INSTANCE=instance
                instance

            }
        }

    }

}

