package com.yash.apps.clockwise

import android.app.Application
import com.yash.apps.clockwise.data.AppContainer
import com.yash.apps.clockwise.data.AppDataContainer

class ClockwiseApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(context = this)
    }
}