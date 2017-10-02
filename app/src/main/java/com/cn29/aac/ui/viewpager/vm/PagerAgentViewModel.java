package com.cn29.aac.ui.viewpager.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by charlesng0209 on 19/6/2017.
 */

public class PagerAgentViewModel extends ViewModel {
    private MutableLiveData<String> messageContainerA;
    private MutableLiveData<String> messageContainerB;

    public void init()
    {
        messageContainerA = new MutableLiveData<>();
        messageContainerA.setValue("Default Message");
        messageContainerB = new MutableLiveData<>();
        messageContainerB.setValue("Default Message");
    }

    public void sendMessageToB(String msg)
    {
        messageContainerB.setValue(msg);
    }
    public void sendMessageToA(String msg)
    {
        messageContainerA.setValue(msg);

    }
    public LiveData<String> getMessageContainerA() {
        return messageContainerA;
    }

    public LiveData<String> getMessageContainerB() {
        return messageContainerB;
    }
}
