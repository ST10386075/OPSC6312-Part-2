// File: app/src/main/java/com/example/doodlenote/MyApplication.kt
package com.example.doodlenote

import android.app.Application

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
