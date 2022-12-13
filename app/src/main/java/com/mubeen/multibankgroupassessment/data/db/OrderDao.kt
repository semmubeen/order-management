package com.mubeen.multibankgroupassessment.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mubeen.multibankgroupassessment.data.model.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: Order)

    @Query("Select * FROM 'Order'")
    fun getAll(): LiveData<List<Order>>


    @Update
    fun update(order: Order)

    @Query("DELETE FROM 'Order' WHERE id = :id")
    fun delete(id: Int?)
}