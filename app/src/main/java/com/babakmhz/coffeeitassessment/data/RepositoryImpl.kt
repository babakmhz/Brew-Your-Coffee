package com.babakmhz.coffeeitassessment.data

import com.babakmhz.coffeeitassessment.data.db.DbHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.data.model.device.Type
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
            var url = dbHelper.getImageUrlForType(type)
            if (url.validString().not()) {
                url = getImageUrlForType(type.name)
            }
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

    override fun getSizesForType(type: Type): List<Size> {
        val sizes = dbHelper.getSizesForType(type)

        type.selectedSize?.let {
            for (size in sizes) {
                if (size == type.selectedSize){
                    size.selected = true
                }
            }
        }

        return sizes
    }

    override fun getExtrasForType(type: Type): List<Extra> {
        val extras = dbHelper.getExtrasForType(type)
        for (extra in extras) {
            extra.apply {
                subselections = dbHelper.getSubSelectionsForExtra(extra)
                for(sub in subselections){
                    if (type.selectedExtrasSubSelection.contains(sub))
                        sub.selected = true
                }
            }
        }
        return extras
    }


}