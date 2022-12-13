package com.mubeen.multibankgroupassessment.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mubeen.multibankgroupassessment.data.model.Order

@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "OrderDatabase"
        private var INSTANCE: OrderDatabase? = null

        fun getInstance(application: Application): OrderDatabase {
            synchronized(OrderDatabase.lock) {
                if (OrderDatabase.INSTANCE == null) {
                    OrderDatabase.INSTANCE =
                        Room.databaseBuilder(
                            application,
                            OrderDatabase::class.java,
                            OrderDatabase.DB_NAME
                        )
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}