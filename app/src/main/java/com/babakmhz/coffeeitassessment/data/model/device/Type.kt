package com.babakmhz.coffeeitassessment.data.model.device

import com.google.gson.annotations.Expose
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Type(
    @Id @Expose(serialize = false, deserialize = false) var id: Long = 0L,
    var _id: String = "",
    var name: String = "",
    var extras: List<String> = arrayListOf(),
    var sizes: List<String> = arrayListOf(),
    var imageUrl: String = ""
) {
}