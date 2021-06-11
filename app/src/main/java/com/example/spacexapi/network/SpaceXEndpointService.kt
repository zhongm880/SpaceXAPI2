package com.example.spacexapi.network
import com.example.spacexapi.util.Constants.Companion.LAUNCHES_END_POINT
import com.example.spacexapi.model.LaunchesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXEndpointService {

    @GET(LAUNCHES_END_POINT)
    fun getLaunchDataAsync(@Query("flight_number") flightnum: Int) : Deferred<LaunchesResponse>
}