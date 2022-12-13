package com.mubeen.multibankgroupassessment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mubeen.multibankgroupassessment.R
import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.view.listeners.OrderAdapterInteraction

class OrderListAdapter(private val orders: MutableList<Order>, private val orderAdapterInteraction: OrderAdapterInteraction): RecyclerView.Adapter<OrderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_main, parent, false)
        return OrderHolder(view)
    }

    override fun getItemCount(): Int = orders.size ?: 0


    fun setOrders(items: List<Order>) {
        this.orders.clear()
        this.orders.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val order = orders[position]

        holder.orderIdTv.text = "Order - ${order.id}"
        holder.orderState.text = order.state

        holder.orderState.setOnClickListener {
            orderAdapterInteraction.onStateClick(order)
        }

        holder.itemView.setOnClickListener{
            orderAdapterInteraction.onItemClick(order)
        }
    }

}

class OrderHolder(view: View) : RecyclerView.ViewHolder(view) {

    val orderIdTv: TextView =  view.findViewById(R.id.orderIdTv)
    val orderState: TextView =  view.findViewById(R.id.orderStatusTv)
}