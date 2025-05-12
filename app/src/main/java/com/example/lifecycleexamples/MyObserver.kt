package com.example.lifecycleexamples

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver: DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.v("TAG", "Observer: onCreate")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.v("TAG", "Observer: onPause")
    }
}