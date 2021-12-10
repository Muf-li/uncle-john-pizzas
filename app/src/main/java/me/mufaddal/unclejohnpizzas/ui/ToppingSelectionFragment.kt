package me.mufaddal.unclejohnpizzas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.Pizza
import me.mufaddal.unclejohnpizzas.data.models.Topping

class ToppingSelectionFragment : Fragment() {

    private lateinit var pizza: Pizza

    private var toppings: ArrayList<Topping> = ArrayList()

    init {
        toppings.add(Topping("Pepperoni", 0.00f))
        toppings.add(Topping("Mushrooms", 0.00f))
        toppings.add(Topping("Black Olives", 0.00f))
        toppings.add(Topping("Sausages", 0.00f))
        toppings.add(Topping("Bacon", 0.00f))
        toppings.add(Topping("Extra Cheese", 0.00f))
        toppings.add(Topping("Green Peppers", 0.00f))
        toppings.add(Topping("Pineapples", 0.00f))
        toppings.add(Topping("Spinach", 0.00f))
        toppings.add(Topping("Onions", 0.00f))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arguments?.getString("PIZZA") != null){
            pizza = Json.decodeFromString(arguments?.getString("PIZZA")!!)
        }

        val view: View = inflater.inflate(R.layout.fragment_topping_selection, container, false)
        val tvSubTitle: TextView = view.findViewById(R.id.tvSubTitle)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
        val tvCardTitle: TextView = view.findViewById(R.id.tvCardTitle)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val btnNext: Button = view.findViewById(R.id.btnNext)

        tvSubTitle.text = HtmlCompat.fromHtml(
            "<span color='white'>"+pizza.pizzaSize?.name+", "+pizza.pizzaCrust?.name+"</span>"+", Toppings",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        tvCardTitle.text = HtmlCompat.fromHtml(
            getString(R.string.label_choose_up_to_7_toppings) + "<b> 7 toppings</b>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        tvTotal.text = getString(R.string.price, String.format("%.2f", pizza.pizzaSize!!.price + pizza.pizzaCrust!!.price))

        val mLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = mLayoutManager
        val mAdapter = ToppingAdapter(requireContext(), toppings)
        recyclerView.adapter = mAdapter

        btnNext.setOnClickListener {
            val bundle = Bundle()
            val newList: ArrayList<Topping> = ArrayList()
            for(topping: Topping in mAdapter.getToppingData()){
                if(topping.isSelected){
                    newList.add(topping)
                }
            }
            pizza.toppings = newList
            bundle.putString("PIZZA", Json.encodeToString(pizza))
            findNavController().navigate(R.id.action_toppingSelectionFragment_to_itemDetailsFragment, bundle)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ToppingSelectionFragment.
         */
        @JvmStatic
        fun newInstance() = ToppingSelectionFragment()
    }
}