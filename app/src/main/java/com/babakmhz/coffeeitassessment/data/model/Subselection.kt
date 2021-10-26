package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Subselection(
    @Id(assignable = true)val _id: String,
    val name: String
)