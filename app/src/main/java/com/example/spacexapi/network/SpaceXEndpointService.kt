package com.example.spacexapi.network

import com.example.spacexapi.model.CompanyResponse
import com.example.spacexapi.util.Constants.Companion.LAUNCHES_END_POINT
import com.example.spacexapi.model.LaunchesResponse
import com.example.spacexapi.util.Constants.Companion.INFO_END_POINT
import com.example.spacexapi.util.Constants.Companion.QUERY_LAUNCHES_YEAR
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXEndpointService {

    @GET(LAUNCHES_END_POINT)
    fun getLaunchDataAsync(
        @Query(QUERY_LAUNCHES_YEAR) queryYear: String? = null): Deferred<LaunchesResponse>

    @GET(INFO_END_POINT)
    fun getInfoAsync(): Deferred<CompanyResponse>
}