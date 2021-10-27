package com.babakmhz.coffeeitassessment.data.model.device

import com.google.gson.annotations.Expose
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Device(
    @Id @Expose(serialize = false,deserialize = false ) var id: Long = 0L,
    var _id: String = "",
    var extras: List<Extra> = arrayListOf(),
    var sizes: List<Size> = arrayListOf(),
    var types: List<Type> = arrayListOf()
)