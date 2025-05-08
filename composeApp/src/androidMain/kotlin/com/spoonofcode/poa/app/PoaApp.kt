package com.spoonofcode.poa.app

import android.app.Application

class PoaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}