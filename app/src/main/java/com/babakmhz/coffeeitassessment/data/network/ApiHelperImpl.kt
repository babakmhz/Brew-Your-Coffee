package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.data.model.Device
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getDeviceCoffees(): Device {
        TODO("Not yet implemented")
    }

}