package com.mubeen.multibankgroupassessment.presenter

import androidx.lifecycle.Observer
import com.mubeen.multibankgroupassessment.data.OrderInteractor
import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.domain.OrderState
import com.mubeen.multibankgroupassessment.enums.MyEnums
import com.mubeen.multibankgroupassessment.view.MainView
import java.util.*
import kotlin.concurrent.schedule

class MainPresenter(private val orderInteractor: OrderInteractor) {
    private lateinit var view: MainView

    fun bind(view: MainView) {
        this.view = view
        displayOrders()
    }

    fun placeOrder() {
        orderInteractor.addOrder(
            Order(state = MyEnums.New.toString(), date = System.currentTimeMillis())
        )
    }

    fun updateOrderState(order: Order) {
        orderInteractor.updateOrder(order)

        if (order.state == MyEnums.Delivered.toString()) {
            Timer().schedule(15000) {
                orderInteractor.deleteOrder(order.id!!)
               }
        }
    }

    private fun displayOrders() {
        val state = orderInteractor.getData()
        when (state) {
            is OrderState.LoadingState -> view.render(state)
            is OrderState.ErrorState -> view.render(state)
            is OrderState.LiveDataState -> {
                state.data.observe(view, Observer { orders ->
                    view.render(OrderState.DataState(orders))
                })
            }
            else -> {}
        }
    }
}