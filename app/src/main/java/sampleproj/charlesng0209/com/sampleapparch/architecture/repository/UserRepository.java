package sampleproj.charlesng0209.com.sampleapparch.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import sampleproj.charlesng0209.com.sampleapparch.src.model.User;

/**
 * Created by charlesng0209 on 18/6/2017.
 */


public class UserRepository {

  public LiveData<User> getUser(int userId) {
    // This is not an optimal implementation, we'll fix it below
    final MutableLiveData<User> data = new MutableLiveData<>();
    return data;
  }
}
