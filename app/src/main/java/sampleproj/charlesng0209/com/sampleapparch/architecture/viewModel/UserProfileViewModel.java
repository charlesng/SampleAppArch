package sampleproj.charlesng0209.com.sampleapparch.architecture.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import sampleproj.charlesng0209.com.sampleapparch.architecture.repository.UserRepository;
import sampleproj.charlesng0209.com.sampleapparch.src.model.User;

/**
 * Created by charlesng0209 on 18/6/2017.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;




    public void init(String userId) {
        if (this.user != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        userRepo = new UserRepository();
        user = userRepo.getUser(1);
    }

    public LiveData<User> getUser() {
        return user;
    }
}