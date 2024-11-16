package com.desafiolatam.weatherlatam.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


 /*@Database(version = 1, entities = [WeatherEntity::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao


    private fun databaseBuilder(context: Context, klass: Class<WeatherDatabase>, name: String) {
        val db = databaseBuilder(
            context,WeatherDatabase::class.java, "weather_database"
        ).build()

    }

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                )
                    //.createFromAsset("weatherdatabase.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private fun Unit.build(): Any {
    TODO("Not yet implemented")
}
*/


import java.io.File
/*
@Database(version = 1, entities = [WeatherEntity::class])
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                // Ruta personalizada para la base de datos en almacenamiento externo
                val databasePath = File(context.getExternalFilesDir(null), "weather_database.db")

                // Crear la base de datos en la ruta especificada
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    databasePath.absolutePath // Usar la ruta completa como nombre de la base de datos
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}

*/
@Database(version = 1, entities = [WeatherEntity::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}