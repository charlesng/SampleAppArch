package sampleproj.charlesng0209.com.sampleapparch.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sampleproj.charlesng0209.com.sampleapparch.src.model.User;

/**
 * Created by charlesng0209 on 18/6/2017.
 */

@Singleton
public class UserRepository {

  public LiveData<User> getUser(int userId) {
    // This is not an optimal implementation, we'll fix it below
    final MutableLiveData<User> data = new MutableLiveData<>();
    Observable.interval(0, 2, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread()).map((Function<Long, Object>) aLong -> {
      User value = new User();
      value.setName(String.format("ABCD %d ", aLong));
      data.setValue(value);

      return aLong;
    }).subscribe();

    return data;
  }
}
