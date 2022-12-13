package com.mubeen.multibankgroupassessment.view.listeners

import com.mubeen.multibankgroupassessment.data.model.Order

interface OrderAdapterInteraction {
    fun onStateClick(order: Order)
    fun onItemClick(order: Order)
}