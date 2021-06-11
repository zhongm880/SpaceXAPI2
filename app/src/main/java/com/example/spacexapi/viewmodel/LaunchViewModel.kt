package com.example.spacexapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapi.network.SpaceXRepository
import com.example.spacexapi.model.LaunchesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object LaunchViewModel: ViewModel() {

    val launchLiveData: MutableLiveData<LaunchesResponse> = MutableLiveData()

    private val spacexRepository = SpaceXRepository()

    private var launchJob: Job? = null

    suspend fun getLaunchData(flightnum: Int){

        launchJob = viewModelScope.launch(Dispatchers.IO) {

            try {
                val launchResponse = spacexRepository.getLaunchData(flightnum).await()

                launchResponse?.let {
                    launchLiveData.postValue(it)
                }
            } catch (e: Exception) {
                Log.d("TAG_ERROR", "getLaunchData: ${e.localizedMessage}")
            }
        }
    }
}