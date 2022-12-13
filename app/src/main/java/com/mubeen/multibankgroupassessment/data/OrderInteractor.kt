package com.mubeen.multibankgroupassessment.data

import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.db
import com.mubeen.multibankgroupassessment.domain.OrderState
import kotlin.concurrent.thread

class OrderInteractor: Interactor {

    private val orderDao = db.orderDao()

    override fun getData(): OrderState {
        var state: OrderState = OrderState.LoadingState

        if (orderDao == null) state = OrderState.ErrorState("Error")
        else {
            state = OrderState.LiveDataState(orderDao.getAll())
        }

        return state
    }

    override fun addOrder(order: Order) {
        thread { orderDao.insert(order) }
    }

    override fun deleteOrder(id: Int) {
        thread { orderDao.delete(id) }
    }

    override fun updateOrder(order: Order) {
        thread { orderDao.update(order) }
    }
}
