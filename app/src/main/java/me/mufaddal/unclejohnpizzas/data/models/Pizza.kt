package me.mufaddal.unclejohnpizzas.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    var pizzaSize: PizzaSize? = null,
    var pizzaCrust: PizzaCrust? = null,
    var toppings: List<Topping> = ArrayList(),
)
