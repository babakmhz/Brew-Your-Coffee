package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.BuildConfig
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.typeImages.TypeImage
import com.babakmhz.coffeeitassessment.utils.API_COFFEE_MACHINE_ENDPOINT
import com.babakmhz.coffeeitassessment.utils.LEX_ID
import com.babakmhz.coffeeitassessment.utils.UNSPLASH_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("$API_COFFEE_MACHINE_ENDPOINT/{deviceId}")
    suspend fun getDeviceCoffees(@Path("deviceId") deviceId: String = LEX_ID): Device

    @GET(UNSPLASH_ENDPOINT)
    suspend fun getTypeImage(
        @Query("query") name: String,
        @Query("client_id") clientId: String = BuildConfig.Unsplash_client_id
    ):TypeImage

}