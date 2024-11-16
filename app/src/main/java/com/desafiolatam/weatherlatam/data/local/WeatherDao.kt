package com.desafiolatam.weatherlatam.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    // Ejemplo
   // @Query("DELETE FROM weather")
   /* suspend fun clearAll()

    @Query("SELECT * FROM weather")
     fun getAll(): Flow<List<WeatherDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather where ID")
    fun getWeatherDataById(): Flow<List<WeatherDto>>
}
*/


    @Query("SELECT * FROM weather")
    fun getWeatherData(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM weather WHERE id = :id")
    fun getWeatherDataById(id: Int): Flow<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCityName(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather")
    suspend fun clearAll()
}