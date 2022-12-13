package com.mubeen.multibankgroupassessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var state: String = "",
    var date: Long = 0
)