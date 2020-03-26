package com.cn29.aac.ui.shopping.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Charles Ng on 23/10/2017.
 */
class ShoppingActivityViewModel : ViewModel() {
    private val queryData: MutableLiveData<String> = MutableLiveData()
    fun query(query: String) {
        queryData.value = query
    }

    fun getQueryData(): LiveData<String> {
        return queryData
    }

}