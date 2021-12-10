package me.mufaddal.unclejohnpizzas.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Topping(
    var name: String = "",
    var price: Float = 1.00f,
    var isFree: Boolean = false,
    var isSelected: Boolean = false
)
