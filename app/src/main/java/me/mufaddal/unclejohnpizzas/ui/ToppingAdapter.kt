package me.mufaddal.unclejohnpizzas.ui

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.Topping

class ToppingAdapter(
    private val context: Context,
    private val list: MutableList<Topping>
) : RecyclerView.Adapter<ToppingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_pizza_topping, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: Topping = list[position]

        if(position%2 == 0){
            holder.imgItem.setImageDrawable(context.getDrawable(R.drawable.peproni))
        } else{
            holder.imgItem.setImageDrawable(context.getDrawable(R.drawable.mashrooms))
        }

        holder.tvName.text = data.name
        holder.tvPrice.text = context.getString(R.string.price, String.format("%.2f", data.price))

        holder.imgSwitcher.setFactory {
            val myView = ImageView(context.applicationContext)
            myView.scaleType = ImageView.ScaleType.FIT_CENTER
            myView.layoutParams = FrameLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            myView
        }
        holder.imgSwitcher.setImageResource(R.drawable.ic_unchecked)
        holder.imgSwitcher.setOnClickListener {
            data.isSelected = !data.isSelected
            if(data.isSelected){
                holder.imgSwitcher.setImageResource(R.drawable.ic_checked)
            } else{
                holder.imgSwitcher.setImageResource(R.drawable.ic_unchecked)
            }
        }
    }

    fun getToppingData(): MutableList<Topping>{
        return list
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgItem: ImageView = view.findViewById(R.id.imgItem)
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvPrice: TextView = view.findViewById(R.id.tvPrice)
        var imgSwitcher: ImageSwitcher = view.findViewById(R.id.imgSwitcher)
    }
}