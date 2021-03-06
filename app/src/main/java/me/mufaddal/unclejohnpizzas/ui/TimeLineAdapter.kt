package me.mufaddal.unclejohnpizzas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.OrderStatus
import me.mufaddal.unclejohnpizzas.data.models.TimeLineModel
import me.mufaddal.unclejohnpizzas.utils.VectorDrawableUtils

class TimeLineAdapter(private val mFeedList: List<TimeLineModel>) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    private lateinit var mLayoutInflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        if(!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }
        return TimeLineViewHolder(mLayoutInflater.inflate(R.layout.item_timeline, parent, false), viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        val timeLineModel = mFeedList[position]

        val uri = "@drawable/"+timeLineModel.resourceName
        val imgRes = holder.itemView.context.resources.getIdentifier(uri, null, holder.itemView.context.packageName)
        holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, imgRes)

        if(timeLineModel.status == OrderStatus.COMPLETED
            || timeLineModel.status == OrderStatus.ACTIVE){
            holder.timeline.setStartLineColor(R.color.red_500, getItemViewType(position))
            holder.timeline.setEndLineColor(R.color.red_500, getItemViewType(position))
        }

        if (timeLineModel.date.isNotEmpty()) {
            holder.date.visibility = View.VISIBLE
            holder.date.text = timeLineModel.date
        } else {
            holder.date.visibility = View.GONE
        }

        holder.message.text = timeLineModel.message
    }

    override fun getItemCount() = mFeedList.size

    inner class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.text_timeline_date)
        val message = itemView.findViewById<TextView>(R.id.text_timeline_title)
        val timeline = itemView.findViewById<TimelineView>(R.id.timeline)
        init {
            timeline.initLine(viewType)
        }
    }
}