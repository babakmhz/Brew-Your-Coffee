package com.babakmhz.coffeeitassessment.data.model.device

import com.google.gson.annotations.Expose
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Subselection(
    @Id @Expose(serialize = false,deserialize = false )
    var id:Long = 0L,
    var _id: String = "",
    @Expose(serialize = false,deserialize = false )
    var extraId:String = "",
    var name: String = ""
){
    var selected = false
}