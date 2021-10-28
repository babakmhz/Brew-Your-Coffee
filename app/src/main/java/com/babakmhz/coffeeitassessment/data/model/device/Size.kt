package com.babakmhz.coffeeitassessment.data.model.device

import com.google.gson.annotations.Expose
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class Size(
    @Id @Expose(serialize = false,deserialize = false )var id: Long = 0L,
    var __v: Int = 0,
    var _id: String = "",
    var name: String = ""
) {
    var selected = false
}