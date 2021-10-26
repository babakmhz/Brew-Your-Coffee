package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Type(
    @Id(assignable = true) val _id: String,
    val extras: List<String>,
    val name: String,
    val sizes: List<String>
)