package com.babakmhz.coffeeitassessment.data.model.device

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class Size(
    @Id var id: Long = 0L,
    var __v: Int = 0,
    var _id: String = "",
    var name: String = ""
) {
}