package sampleproj.charlesng0209.com.sampleapparch.architecture.viewModel.viewpager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import sampleproj.charlesng0209.com.sampleapparch.src.model.User;

/**
 * Created by charlesng0209 on 19/6/2017.
 */

public class PagerAgentViewModel extends ViewModel {
    private MutableLiveData<User> userInfo;

    public void init()
    {
        userInfo = new MutableLiveData<>();
        User value = new User();
        value.setAge(0);
        value.setName("Default");
        userInfo.setValue(value);
    }

    public void setNewName(String name)
    {
        User user = new User();
        user.setName(name);
        userInfo.setValue(user);
    }
    public LiveData<User> getUserInfo() {
        return userInfo;
    }

}
