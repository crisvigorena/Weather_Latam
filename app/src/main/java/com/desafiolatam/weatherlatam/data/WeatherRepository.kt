package com.desafiolatam.weatherlatam.data

import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    //abstract fun getWeatherData(): Any

    //
    //Estos son ejemplos de funciones, recuerda que Room debería entregar clases tipo Entity y con la ayuda de un mapper deberías transforma esa classe a una tipo DTO

    suspend fun getWeatherData(): Flow<List<WeatherDto>?>
  //  suspend fun getWeatherDataById(id: Int): Flow<WeatherDto?>
    suspend fun insertData(weatherEntity: WeatherEntity)
    suspend fun clearAll()
    //suspend fun saveCityName(weatherDto: WeatherDto)
    //
}