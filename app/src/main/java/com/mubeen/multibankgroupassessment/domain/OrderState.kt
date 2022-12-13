package com.mubeen.multibankgroupassessment.domain

import androidx.lifecycle.LiveData
import com.mubeen.multibankgroupassessment.data.model.Order

sealed class OrderState {
    object LoadingState: OrderState()
    data class LiveDataState(val data: LiveData<List<Order>>): OrderState()
    data class DataState(val data: List<Order>): OrderState()
    data class ErrorState(val data: String): OrderState()
}