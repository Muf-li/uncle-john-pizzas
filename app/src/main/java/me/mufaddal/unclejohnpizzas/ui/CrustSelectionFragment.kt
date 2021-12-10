package me.mufaddal.unclejohnpizzas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.Pizza
import me.mufaddal.unclejohnpizzas.data.models.PizzaCrust

class CrustSelectionFragment : Fragment() {

    private lateinit var pizza: Pizza

    private var crusts: ArrayList<PizzaCrust> = ArrayList()

    init {
        crusts.add(PizzaCrust(1,"Thin",2.00f, "thin"))
        crusts.add(PizzaCrust(2,"Thick",4.00f, "thick"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arguments?.getString("PIZZA") != null){
            pizza = Json.decodeFromString(arguments?.getString("PIZZA")!!)
        }

        val view: View = inflater.inflate(R.layout.fragment_crust_selection, container, false)
        val tvSubTitle: TextView = view.findViewById(R.id.tvSubTitle)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
        val imgPizza: ImageView = view.findViewById(R.id.imgPizza)
        val tvCardTitle: TextView = view.findViewById(R.id.tvCardTitle)
        val cgCrusts: ChipGroup = view.findViewById(R.id.cgCrusts)
        val btnNext: Button = view.findViewById(R.id.btnNext)

        tvSubTitle.text = HtmlCompat.fromHtml(
            "<span color='white'>"+pizza.pizzaSize?.name+"</span>"+", Crust, Toppings",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        tvCardTitle.text = HtmlCompat.fromHtml(
            getString(R.string.label_choose_your_size) + "<b> Crust</b>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )

        for (crust: PizzaCrust in crusts){
            val chipView = inflater.inflate(R.layout.item_chip, cgCrusts, false) as Chip
            chipView.id = crust.id
            chipView.text = crust.name
            cgCrusts.addView(chipView)
            if(crust.name == "Thin"){
                chipView.isChecked = true
                pizza.pizzaCrust = crust
                tvTotal.text = getString(R.string.price, String.format("%.2f", pizza.pizzaSize!!.price + crust.price))
            }
        }

        cgCrusts.setOnCheckedChangeListener { _, checkedId ->
            for (crust: PizzaCrust in crusts){
                if(crust.id == checkedId){
                    pizza.pizzaCrust = crust
                    tvTotal.text = getString(R.string.price, String.format("%.2f", pizza.pizzaSize!!.price + crust.price))
                    val uri = "@drawable/crust_"+crust.resourceName
                    val imgRes = requireContext().resources.getIdentifier(uri, null, requireContext().packageName)
                    imgPizza.setImageDrawable(requireContext().getDrawable(imgRes))
                    break
                }
            }
        }

        btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("PIZZA", Json.encodeToString(pizza))
            findNavController().navigate(R.id.action_crustSelectionFragment_to_toppingSelectionFragment, bundle)
        }

        return view
    }
}