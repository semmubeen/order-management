package com.mubeen.multibankgroupassessment.view

import androidx.lifecycle.LifecycleOwner
import com.mubeen.multibankgroupassessment.domain.OrderState

interface MainView : LifecycleOwner {
    fun render(state: OrderState)
}