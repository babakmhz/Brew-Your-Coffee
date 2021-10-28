package com.babakmhz.coffeeitassessment.data.model.device

import android.os.Parcelable
import com.google.gson.annotations.Expose
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Type(
    @Id @Expose(serialize = false, deserialize = false) var id: Long = 0L,
    var _id: String = "",
    var name: String = "",
    var extras: List<String> = arrayListOf(),
    var sizes: List<String> = arrayListOf(),
    var imageUrl: String = ""
) :Parcelable{
    @Transient
    var selectedSize:Size? = null
    @Transient
    var selectedExtras :HashSet<Extra> = hashSetOf()
}