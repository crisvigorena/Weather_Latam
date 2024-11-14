package com.desafiolatam.weatherlatam.data

import com.desafiolatam.weatherlatam.data.local.WeatherDao
import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImp(
    private val weatherDao: WeatherDao
) : WeatherRepository {

    // Este es un ejemplo de llamado de una función definida en WeatherDao
     override suspend fun clearAll() = weatherDao.clearAll()

    //override suspend fun getWeatherData(): Flow<List<WeatherDto>?> {
      //  TODO("Not yet implemented")
   // }
    override suspend fun getWeatherData(): Flow<List<WeatherDto>?> {
        return weatherDao.getAll()
    }

    override suspend fun insertData(weatherEntity: WeatherEntity )= weatherDao.insertData(weatherEntity)



}