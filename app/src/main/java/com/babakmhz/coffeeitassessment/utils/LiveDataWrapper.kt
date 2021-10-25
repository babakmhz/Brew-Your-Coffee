package com.babakmhz.coffeeitassessment.utils

sealed class State<out T : Any>{
    object Loading : State<Nothing>()
    data class Error(val error: Throwable?=null) : State<Nothing>()
    data class Success<out T : Any>(val data: T) : State<T>()
}