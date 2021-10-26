package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id


@Entity
data class Device(
    @Id(assignable = true) val _id: String,
    val extras: List<Extra>,
    val sizes: List<Size>,
    val types: List<Type>
)