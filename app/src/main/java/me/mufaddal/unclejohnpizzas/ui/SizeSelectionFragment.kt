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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.Pizza
import me.mufaddal.unclejohnpizzas.data.models.PizzaSize
import java.math.RoundingMode
import java.text.DecimalFormat

class SizeSelectionFragment : Fragment() {

    private val pizza: Pizza = Pizza()

    private var sizes: ArrayList<PizzaSize> = ArrayList()

    init {
        sizes.add(PizzaSize(1,"Small",8.00f, "small"))
        sizes.add(PizzaSize(2,"Medium",10.00f, "medium"))
        sizes.add(PizzaSize(3,"Large",12.00f, "large"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_size_selection, container, false)
        val tvTotal: TextView = view.findViewById(R.id.tvTotal)
        val imgPizza: ImageView = view.findViewById(R.id.imgPizza)
        val tvCardTitle: TextView = view.findViewById(R.id.tvCardTitle)
        val cgSizes: ChipGroup = view.findViewById(R.id.cgSizes)
        val btnNext: Button = view.findViewById(R.id.btnNext)

        tvCardTitle.text = HtmlCompat.fromHtml(getString(R.string.label_choose_your_size) + "<b> Size</b>", HtmlCompat.FROM_HTML_MODE_COMPACT)

        val df = DecimalFormat("##.##")
        df.roundingMode = RoundingMode.FLOOR

        for (size: PizzaSize in sizes){
            val chipView = inflater.inflate(R.layout.item_chip, cgSizes, false) as Chip
            chipView.id = size.id
            chipView.text = size.name
            cgSizes.addView(chipView)
            if(size.name == "Medium"){
                chipView.isChecked = true
                pizza.pizzaSize = size
                tvTotal.text = getString(R.string.price, String.format("%.2f", size.price))
            }
        }

        cgSizes.setOnCheckedChangeListener { _, checkedId ->
            for (size: PizzaSize in sizes){
                if(size.id == checkedId){
                    pizza.pizzaSize = size
                    tvTotal.text = getString(R.string.price, String.format("%.2f", size.price))
                    val uri = "@drawable/size_"+size.resourceName
                    val imgRes = requireContext().resources.getIdentifier(uri, null, requireContext().packageName)
                    imgPizza.setImageDrawable(requireContext().getDrawable(imgRes))
                    break
                }
            }
        }

        btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("PIZZA", Json.encodeToString(pizza))
            findNavController().navigate(R.id.action_sizeSelectionFragment_to_crustSelectionFragment, bundle)
        }

        return view
    }
}