package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Device(
    @Id  var id:Long = 0L,
    var _id: String = "",
    var extras: List<Extra> = arrayListOf(),
    var sizes: List<Size> = arrayListOf(),
    var types: List<Type> = arrayListOf()
)