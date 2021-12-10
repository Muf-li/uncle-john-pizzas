package me.mufaddal.unclejohnpizzas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import me.mufaddal.unclejohnpizzas.R

class CheckoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_checkout, container, false)
        val btnNext: Button = view.findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_orderDetailsFragment)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CheckoutFragment.
         */
        @JvmStatic
        fun newInstance() = CheckoutFragment()
    }
}