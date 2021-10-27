package com.babakmhz.coffeeitassessment.data.db

import com.babakmhz.coffeeitassessment.data.model.Device
import com.babakmhz.coffeeitassessment.data.model.Extra
import com.babakmhz.coffeeitassessment.data.model.Size
import com.babakmhz.coffeeitassessment.data.model.Type

interface DbHelper {

     fun putAllData(device:Device):Long
     fun getSizesForType(type: Type):List<Size>
     fun getExtrasForType(type: Type):List<Extra>
     fun clearTables()
}