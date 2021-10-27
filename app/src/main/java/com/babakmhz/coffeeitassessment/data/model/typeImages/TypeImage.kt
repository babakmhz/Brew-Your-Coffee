package com.babakmhz.coffeeitassessment.data.model.typeImages

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class TypeImage(
    val results: List<Result>
)

data class Result(
    var urls: Urls
)

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)
