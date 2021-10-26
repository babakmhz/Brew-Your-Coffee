package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Size(
    val __v: Int,
    @Id(assignable = true) val _id: String,
    val name: String
)