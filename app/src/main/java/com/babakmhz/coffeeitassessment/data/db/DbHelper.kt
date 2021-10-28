package com.babakmhz.coffeeitassessment.data.db

import com.babakmhz.coffeeitassessment.data.model.device.*
import com.babakmhz.coffeeitassessment.data.model.typeImages.Urls

interface DbHelper {

     fun putAllData(device: Device):Long
     fun getSizesForType(type: Type):List<Size>
     fun getExtrasForType(type: Type):List<Extra>
     fun getSubSelectionsForExtra(extra: Extra):List<Subselection>
     fun getImageUrlForType(type:Type):String
     fun clearTables()

     interface SharedMethods{
          fun getSizesForType(type:Type):List<Size>
          fun getExtrasForType(type: Type):List<Extra>
     }
}