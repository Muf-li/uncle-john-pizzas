package me.mufaddal.unclejohnpizzas.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PizzaCrust(
    var id: Int,
    var name: String,
    var price: Float,
    var resourceName: String,
)