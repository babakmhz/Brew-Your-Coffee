package com.babakmhz.coffeeitassessment.data.model.device

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Type(
    @Id var id: Long = 0L,
    var _id: String = "",
    var name: String = "",
    var extras: List<String> = arrayListOf(),
    var sizes: List<String> = arrayListOf(),
    var imageUrl: String = ""
) {
}