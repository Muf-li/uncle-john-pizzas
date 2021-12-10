package me.mufaddal.unclejohnpizzas.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.OrderSummary
import me.mufaddal.unclejohnpizzas.data.models.Topping

class OrderDetailAdapter(
    private val context: Context,
    private val list: MutableList<OrderSummary>
) : RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_order_details, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: OrderSummary = list[position]
        holder.tvItemName.text = data.itemName
        holder.tvPrice.text = context.getString(R.string.price, String.format("%.2f", data.price))
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvItemName: TextView = view.findViewById(R.id.tvItemName)
        var tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }
}