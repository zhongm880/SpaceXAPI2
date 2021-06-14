package com.example.spacexapi.network

import com.example.spacexapi.model.CompanyResponse
import com.example.spacexapi.util.Constants.Companion.URL
import com.example.spacexapi.model.LaunchesResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpaceXRepository {

    private var launchAPI: SpaceXEndpointService

    init {
        launchAPI = createlaunchAPI(createRetrofit())
    }

    private fun createlaunchAPI(retrofitInstance: Retrofit): SpaceXEndpointService {
        return retrofitInstance.create(SpaceXEndpointService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun getLaunchData(year: String?): Deferred<LaunchesResponse> = launchAPI.getLaunchDataAsync(year)
    fun getCompanyData(): Deferred<CompanyResponse> = launchAPI.getInfoAsync()
}