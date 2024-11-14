package com.desafiolatam.weatherlatam.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    // Ejemplo
    @Query("DELETE FROM weather")
    suspend fun clearAll()

    @Query("SELECT * FROM weather")
     fun getAll(): Flow<List<WeatherDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(weatherEntity: WeatherEntity)
}
