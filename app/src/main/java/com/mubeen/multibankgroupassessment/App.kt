package com.mubeen.multibankgroupassessment

import android.app.Application
import com.mubeen.multibankgroupassessment.data.db.OrderDatabase

lateinit var db: OrderDatabase
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        db = OrderDatabase.getInstance(this)
        instance = this
    }
}