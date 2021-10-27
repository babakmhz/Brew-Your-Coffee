package com.babakmhz.coffeeitassessment.data

import com.babakmhz.coffeeitassessment.data.db.DbHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.network.ApiService
import com.babakmhz.coffeeitassessment.utils.validString
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dbHelper: DbHelper
) : RepositoryHelper {

    // the api that i'm using for getting images has a
    // rate-limit in free plan so i decided to cache images of types for
    // avoiding extra api calls
    override suspend fun getDeviceCoffees(): Device {
        val device = apiService.getDeviceCoffees()
        for (type in device.types) {
            if (dbHelper.getImageUrlForType(type).validString().not()){
                val url = getImageUrlForType(type.name)
                type.apply {
                    imageUrl = url
                }
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