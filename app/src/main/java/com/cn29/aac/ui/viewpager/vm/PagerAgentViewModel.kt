package com.cn29.aac.ui.viewpager.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by charlesng0209 on 19/6/2017.
 */
class PagerAgentViewModel : ViewModel() {
    private var messageContainerA: MutableLiveData<String>? = null
    private var messageContainerB: MutableLiveData<String>? = null
    fun init() {
        messageContainerA = MutableLiveData()
        messageContainerA?.value = "Default Message"
        messageContainerB = MutableLiveData()
        messageContainerB?.value = "Default Message"
    }

    fun sendMessageToB(msg: String) {
        messageContainerB?.value = msg
    }

    fun sendMessageToA(msg: String) {
        messageContainerA?.value = msg
    }

    fun getMessageContainerA(): LiveData<String>? {
        return messageContainerA
    }

    fun getMessageContainerB(): LiveData<String>? {
        return messageContainerB
    }
}