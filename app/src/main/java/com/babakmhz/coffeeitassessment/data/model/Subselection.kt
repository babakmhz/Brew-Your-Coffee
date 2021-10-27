package com.babakmhz.coffeeitassessment.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Subselection(
   @Id  var id:Long = 0L,
    var _id: String = "",
    var name: String = ""
)