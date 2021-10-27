package com.babakmhz.coffeeitassessment.data.db

import com.babakmhz.coffeeitassessment.data.model.device.*
import io.objectbox.BoxStore
import javax.inject.Inject

class DbImpl @Inject constructor(private val boxStore: BoxStore) : DbHelper {

    private val typesBoxStore = boxStore.boxFor(Type::class.java)
    private val sizesBoxStore = boxStore.boxFor(Size::class.java)
    private val deviceBoxStore = boxStore.boxFor(Device::class.java)
    private val extraBoxStore = boxStore.boxFor(Extra::class.java)
    private val subSelectionsBoxStore = boxStore.boxFor(Subselection::class.java)

    override fun putAllData(device: Device): Long {
        clearTables()
        val id = deviceBoxStore.put(device)
        sizesBoxStore.put(device.sizes)
        extraBoxStore.put(device.extras)
        typesBoxStore.put(device.types)
        for (extra in device.extras) {
            subSelectionsBoxStore.put(extra.subselections)
        }
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
        val types = typesBoxStore.query().filter {
            type.name == it.name
        }.build().find()
        return if (types.isEmpty()) "" else types[0].imageUrl
    }


    override fun clearTables() {
        deviceBoxStore.removeAll()
        sizesBoxStore.removeAll()
        extraBoxStore.removeAll()
        typesBoxStore.removeAll()
    }

}