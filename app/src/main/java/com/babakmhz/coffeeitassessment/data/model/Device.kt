package com.babakmhz.coffeeitassessment.data.model

data class Device(
    val _id: String,
    val extras: List<Extra>,
    val sizes: List<Size>,
    val types: List<Type>
)