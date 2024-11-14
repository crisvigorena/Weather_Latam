package com.desafiolatam.weatherlatam.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafiolatam.weatherlatam.data.WeatherRepositoryImp
import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepositoryImp) : ViewModel() {

    private val _data: MutableStateFlow<List<WeatherDto>> = MutableStateFlow(emptyList())
    val weatherListStateFlow: StateFlow<List<WeatherDto>> = _data.asStateFlow()
    private val dispatcherIO : CoroutineDispatcher = Dispatchers.IO



    fun saveCityName(cityName:String){

    }


    //Este ejemplo muestra como se puede llamar a la clase repository
     suspend fun getWeather() = repository.getWeatherData().stateIn(viewModelScope)
    //

    init {
        viewModelScope.launch {
            repository.getWeatherData().collectLatest {
                if (it != null) {
                    _data.value = it
                }
            }
        }
    }
    // Función para agregar una nueva tarea
    suspend fun addTask(weatherEntity: WeatherEntity) {
        viewModelScope.launch(dispatcherIO) {
            repository.insertData(weatherEntity)
            // Volver a cargar la lista después de agregar
           // loadTasks()
        }
    }


}

