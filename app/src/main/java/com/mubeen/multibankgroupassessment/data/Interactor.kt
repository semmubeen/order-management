package com.mubeen.multibankgroupassessment.data

import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.domain.OrderState

interface Interactor {
    fun getData(): OrderState
    fun addOrder(order: Order)
    fun deleteOrder(id: Int)
    fun updateOrder(order: Order)
}