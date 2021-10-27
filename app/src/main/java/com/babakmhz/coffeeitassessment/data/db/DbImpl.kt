package com.babakmhz.coffeeitassessment.data.db

import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.data.model.typeImages.Urls
import io.objectbox.BoxStore
import javax.inject.Inject

class DbImpl @Inject constructor(private val boxStore: BoxStore) : DbHelper {

    private val typesBoxStore = boxStore.boxFor(Type::class.java)
    private val sizesBoxStore = boxStore.boxFor(Size::class.java)
    private val deviceBoxStore = boxStore.boxFor(Device::class.java)
    private val extraBoxStore = boxStore.boxFor(Extra::class.java)

    override fun putAllData(device: Device): Long {
        clearTables()
        val id = deviceBoxStore.put(device)
        sizesBoxStore.put(device.sizes)
        extraBoxStore.put(device.extras)
        typesBoxStore.put(device.types)

        return id
    }

    override fun getSizesForType(type: Type): List<Size> {
        val query = sizesBoxStore.query().filter {
            type.sizes.contains(it._id)
        }.build()
        return query.find()
    }

    override fun getExtrasForType(type: Type): List<Extra> {
        val query = extraBoxStore.query().filter {
            type.extras.contains(it._id)
        }.build()
        return query.find()
    }

    override fun getImageUrlForType(type: Type): String {
        return typesBoxStore[type.id].imageUrl
    }


    override fun clearTables() {
        deviceBoxStore.removeAll()
        sizesBoxStore.removeAll()
        extraBoxStore.removeAll()
        typesBoxStore.removeAll()
    }

}