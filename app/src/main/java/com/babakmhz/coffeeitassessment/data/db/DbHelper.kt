package com.babakmhz.coffeeitassessment.data.db

import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.data.model.typeImages.Urls

interface DbHelper {

     fun putAllData(device: Device):Long
     fun getSizesForType(type: Type):List<Size>
     fun getExtrasForType(type: Type):List<Extra>
     fun clearTables()
}