package me.mufaddal.unclejohnpizzas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.OrderSummary
import me.mufaddal.unclejohnpizzas.data.models.Pizza
import me.mufaddal.unclejohnpizzas.data.models.PizzaSize
import me.mufaddal.unclejohnpizzas.data.models.Topping

class OrderDetailFragment : Fragment() {

    private lateinit var pizza: Pizza

    private var orderSummaries: ArrayList<OrderSummary> = ArrayList()
    private var total: Float = 0.00f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        processOrder()

        val view: View = inflater.inflate(R.layout.fragment_order_detail, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
        val btnNext: Button = view.findViewById(R.id.btnNext)

        val mLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = mLayoutManager
        val mAdapter = OrderDetailAdapter(requireContext(), orderSummaries)
        recyclerView.adapter = mAdapter

        btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_itemDetailsFragment_to_checkoutFragment)
        }

        tvTotal.text = getString(R.string.price, String.format("%.2f", total))

        return view
    }

    private fun processOrder(){
        if(arguments?.getString("PIZZA") != null){
            pizza = Json.decodeFromString(arguments?.getString("PIZZA")!!)
        }
        pizza.pizzaSize?.let {
            orderSummaries.add(OrderSummary(it.name, it.price))
            total += it.price
        }
        pizza.pizzaCrust?.let {
            orderSummaries.add(OrderSummary(it.name, it.price))
            total += it.price
        }
        for (topping: Topping in pizza.toppings){
            orderSummaries.add(OrderSummary(topping.name, topping.price))
            total += topping.price
        }
    }
}