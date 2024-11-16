package com.desafiolatam.weatherlatam.data

import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.model.WeatherDto

// Ejemplo: esta función es una extensión de la clase Weather to, es capaz de tomar una clase DTO y transformarla en Entity

/*
fun WeatherDto.toEntity(): WeatherEntity = WeatherEntity(
    id = id,
    currentTemp = currentTemp,
    maxTemp = maxTemp,
    minTemp = minTemp,
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    sunrise = sunrise,
    sunset = sunset,
    cityName = cityName,
)
*/
fun WeatherDto.toEntity(): WeatherEntity = WeatherEntity(
    id = id,
    currentTemp = currentTemp,
    maxTemp = maxTemp,
    minTemp = minTemp,
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    sunrise = sunrise,
    sunset = sunset,
    cityName = cityName,
)

fun entityToDto(entity: WeatherEntity): WeatherDto = WeatherDto(
    id = entity.id,
    currentTemp = entity.currentTemp,
    maxTemp = entity.maxTemp,
    minTemp = entity.minTemp,
    pressure = entity.pressure,
    humidity = entity.humidity,
    windSpeed = entity.windSpeed,
    sunrise = entity.sunrise,
    sunset = entity.sunset,
    cityName = entity.cityName,
)

fun entityListToDtoList(list: List<WeatherEntity>): List<WeatherDto> = list.map(::entityToDto)
