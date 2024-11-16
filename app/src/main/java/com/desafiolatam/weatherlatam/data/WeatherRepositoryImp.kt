package com.desafiolatam.weatherlatam.data

import com.desafiolatam.weatherlatam.data.local.WeatherDao
import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
class WeatherRepositoryImp(
    private val weatherDao: WeatherDao
) : WeatherRepository {

    // Este es un ejemplo de llamado de una función definida en WeatherDao
     override suspend fun clearAll() = weatherDao.clearAll()

    override suspend fun getWeatherDataById(): Flow<List<WeatherDto>> {
        return weatherDao.getWeatherDataById()
    }

    override suspend fun getWeatherData(): Flow<List<WeatherDto>?> {
        return weatherDao.getAll()
    }

    override suspend fun getWeatherDataById(id: Int): Flow<WeatherDto?> {
       return weatherDao.getWeatherDataById()
    }

    override suspend fun insertData(weatherEntity: WeatherEntity )= weatherDao.insertData(weatherEntity)



}*/
class WeatherRepositoryImp(
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherData(): Flow<List<WeatherDto>?> = weatherDao.getWeatherData().map {
            entity -> entity?.let {
        entityListToDtoList(it)
    }
    }

    override suspend fun getWeatherDataById(id: Int): Flow<WeatherDto?> = weatherDao.getWeatherDataById(id).map {
            entity -> entity?.let {
        entityToDto(it)
    }
    }

    override suspend fun insertData(weatherDto: WeatherDto) = weatherDao.insertWeather(weatherDto.toEntity())

    override suspend fun clearAll() = weatherDao.clearAll()

    override suspend fun saveCityName(weatherDto: WeatherDto) = weatherDao.updateCityName(weatherDto.toEntity())

}