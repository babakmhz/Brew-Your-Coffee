package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.typeImages.Urls


interface ApiHelper {
    suspend fun getDeviceCoffees(): Device
    suspend fun getImageUrlForType(name:String):String
}