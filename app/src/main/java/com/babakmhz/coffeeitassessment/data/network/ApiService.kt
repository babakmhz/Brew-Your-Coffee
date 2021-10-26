package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.data.model.Device
import com.babakmhz.coffeeitassessment.utils.API_COFFEE_MACHINE_ENDPOINT
import com.babakmhz.coffeeitassessment.utils.LEX_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("$API_COFFEE_MACHINE_ENDPOINT/{deviceId}")
    suspend fun getDeviceCoffees(@Path("deviceId") deviceId: String = LEX_ID): Device

}