package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Extra(
    @Id var id: Long = 0L,
    var _id: String = "",
    var name: String = "",
    var subselections: List<Subselection> = arrayListOf()
){
}