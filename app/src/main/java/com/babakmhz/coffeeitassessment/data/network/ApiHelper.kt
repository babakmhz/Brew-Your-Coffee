package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.data.model.Device


interface ApiHelper {
    suspend fun getDeviceCoffees():Device
}