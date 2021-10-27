package com.babakmhz.coffeeitassessment.data

import com.babakmhz.coffeeitassessment.data.db.DbHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.network.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dbHelper: DbHelper
) : RepositoryHelper {


    override suspend fun getDeviceCoffees(): Device {
        val device = apiService.getDeviceCoffees()
        for (type in device.types){
            val url = getImageUrlForType(type.name)
            type.apply {
                imageUrl = url
            }
        }
        dbHelper.putAllData(device)
        return device
    }

    override suspend fun getImageUrlForType(name: String): String {
        // we just wanna sample image, so first record would be fine
        return apiService.getTypeImage(name).results[0].urls.regular
    }


}