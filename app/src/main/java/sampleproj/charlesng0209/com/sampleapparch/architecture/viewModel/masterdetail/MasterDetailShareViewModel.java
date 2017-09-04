package sampleproj.charlesng0209.com.sampleapparch.architecture.viewModel.masterdetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by charlesng0209 on 25/6/2017.
 */

public class MasterDetailShareViewModel extends ViewModel{
    private MutableLiveData<String> selectedEmailName = new MutableLiveData<>();

    public void init()
    {
        selectedEmailName = new MutableLiveData<>();
    }

    public void selectEmail(String emailName)
    {
        selectedEmailName.setValue(emailName);
    }


    public LiveData<String> getSelectedEmail(){
        return this.selectedEmailName;
    }
}
