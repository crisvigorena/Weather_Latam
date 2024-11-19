package com.desafiolatam.weatherlatam.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiolatam.weatherlatam.R
import com.desafiolatam.weatherlatam.WeatherApplication
import com.desafiolatam.weatherlatam.data.CELSIUS
import com.desafiolatam.weatherlatam.data.ITEM_ID
import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.databinding.FragmentHomeBinding
import com.desafiolatam.weatherlatam.model.WeatherDto
import com.desafiolatam.weatherlatam.view.adapter.WeatherAdapter
import com.desafiolatam.weatherlatam.view.viewmodel.WeatherViewModel
import com.desafiolatam.weatherlatam.view.viewmodel.WeatherViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var tempUnit: String

    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory((activity?.application as WeatherApplication).repository)
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.getString(getString(R.string.settings_temperature_unit), CELSIUS)?.let {
            tempUnit = it
        }

        adapter = WeatherAdapter(
            weatherList = emptyList(),
            inCelsius = tempUnit == CELSIUS
        )

        isFirstTimeRunning()
        populateRecyclerView()
        getWeatherData()
        initRecyclerView()
        navigateToDetails()
        navigateToSettings()
    }

    private fun isFirstTimeRunning():Boolean {
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        return if (sharedPreferences?.getBoolean("Primer_Inicio",true) == true) {
            with(sharedPreferences.edit()) {
                putBoolean("Primer_Inicio", false)
                apply()
            }
            true
        }else {
            false
        }
    }


    private fun populateRecyclerView() {
        lifecycleScope.launchWhenCreated {
            if (isFirstTimeRunning()) {
                viewModel.insertData()
            }
        }
    }




    @SuppressLint("NotifyDataSetChanged")
    private fun getWeatherData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getWeather()
                // Evita emisiones redundantes
                .collectLatest { list ->
                    if (!list.isNullOrEmpty()) {
                        binding.cityName.text = list.last().cityName
                        adapter.weatherList = list
                        adapter.inCelsius = tempUnit == CELSIUS
                        adapter.run { notifyDataSetChanged() }

         }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvWeather.layoutManager = LinearLayoutManager(context)
        binding.rvWeather.adapter = adapter
    }

    private fun navigateToDetails() {
        adapter.onClick = {
            val bundle = bundleOf(ITEM_ID to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }

    private fun navigateToSettings() {
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        super.onDestroyView()
        _binding = null
    }
}


