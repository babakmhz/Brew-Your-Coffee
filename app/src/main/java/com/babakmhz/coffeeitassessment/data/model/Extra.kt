package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Extra(
    @Id(assignable = true) val _id: String,
    val name: String,
    val subselections: List<Subselection>
)