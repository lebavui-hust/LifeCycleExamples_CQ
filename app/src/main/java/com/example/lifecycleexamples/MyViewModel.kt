package com.example.lifecycleexamples

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

class MyViewModel: ViewModel() {
    var count = MutableLiveData<Int>(0)
    fun doCount() {
        count.postValue(count.value!! + 1)
    }

    var text1 = MutableLiveData<String>("")
    val text2: LiveData<String> = text1.map {
        str -> str.reversed()
    }
    val condition = MutableLiveData<Boolean>(true)
    val text3 = condition.switchMap {
        isSelected -> if (isSelected) text1 else text2
    }

    override fun onCleared() {
        super.onCleared()
        Log.v("TAG", "MyViewModel: onCleared")
    }
}