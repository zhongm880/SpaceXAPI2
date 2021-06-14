package com.example.spacexapi.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.spacexapi.model.CompanyResponse
import com.example.spacexapi.network.SpaceXRepository
import com.example.spacexapi.model.LaunchesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "LaunchViewModel"
class LaunchViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<LaunchesResponse> = MutableLiveData()
    val launchLiveData: LiveData<LaunchesResponse> = mutableLiveData

    private val mutableLiveDataCompany: MutableLiveData<CompanyResponse> = MutableLiveData()
    val companyLiveData: LiveData<CompanyResponse> = mutableLiveDataCompany

    private val spacexRepository = SpaceXRepository()

    private var launchJob: Job? = null

    init {
        getLaunchData()
        getCompanyData()
    }

    private fun getLaunchData(year: String? = null) {
        launchJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val launchResponse = spacexRepository.getLaunchData(year).await()
                withContext(Dispatchers.Main) {
                    launchResponse?.let {
                        mutableLiveData.value = it
                    }
                }
            } catch (e: Exception) {
                Log.d("TAG_ERROR", "getLaunchData: ${e.localizedMessage}")
            }
        }
    }

    private fun getCompanyData() {
        launchJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val companyResponse = spacexRepository.getCompanyData().await()
                Log.d(TAG, "getCompanyData: $companyResponse")
                withContext(Dispatchers.Main) {
                    companyResponse?.let {
                        mutableLiveDataCompany.value = it
                    }
                }
            } catch (e: Exception) {
                Log.d("TAG_ERROR", "getLaunchData: ${e.localizedMessage}")
            }
        }
    }

    fun searchByYear(query: String?) {
        getLaunchData(query)
    }

    class LaunchViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LaunchViewModel() as T
        }
    }
}