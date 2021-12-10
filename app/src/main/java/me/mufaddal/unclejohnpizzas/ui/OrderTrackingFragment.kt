package me.mufaddal.unclejohnpizzas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mufaddal.unclejohnpizzas.R
import me.mufaddal.unclejohnpizzas.data.models.OrderStatus
import me.mufaddal.unclejohnpizzas.data.models.TimeLineModel

class OrderTrackingFragment : Fragment() {

    private lateinit var mAdapter: TimeLineAdapter
    private val mDataList = ArrayList<TimeLineModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_order_tracking, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        setDataListItems()
        initRecyclerView()
        return view
    }

    private fun setDataListItems() {
        mDataList.add(TimeLineModel("Order Confirmed", "11:41 AM", "ic_confirmed", OrderStatus.COMPLETED))
        mDataList.add(TimeLineModel("Preparing...", "12:01 PM", "ic_preparing", OrderStatus.ACTIVE))
        mDataList.add(TimeLineModel("Dispatched", "", "ic_dispatched", OrderStatus.INACTIVE))
        mDataList.add(TimeLineModel("In Transit", "", "ic_in_transit", OrderStatus.INACTIVE))
        mDataList.add(TimeLineModel("Delivered", "", "ic_delivered", OrderStatus.INACTIVE))
    }

    private fun initRecyclerView() {
        mLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = mLayoutManager
        mAdapter = TimeLineAdapter(mDataList)
        recyclerView.adapter = mAdapter
    }
}