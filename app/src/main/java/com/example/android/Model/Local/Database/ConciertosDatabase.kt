package com.example.android.Model.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.Model.Local.Dao.Dao
import com.example.android.Model.Local.Entities.DetalleLocal
import com.example.android.Model.Local.Entities.ListaLocal


@Database(
        entities = [ListaLocal::class,DetalleLocal::class], version = 1, exportSchema = false)
    abstract class ConciertosDatabase : RoomDatabase(){

        abstract fun getDao(): Dao

        companion object {


            @Volatile
            private var INSTANCE: ConciertosDatabase? = null

            fun getdatabase(context: Context):ConciertosDatabase {

                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConciertosDatabase::class.java,
                        "plant_database"
                    ).build()
                    INSTANCE = instance
                    return instance

                }
            }
        }
    }




