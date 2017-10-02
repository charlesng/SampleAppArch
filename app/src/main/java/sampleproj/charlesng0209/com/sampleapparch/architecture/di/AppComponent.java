package sampleproj.charlesng0209.com.sampleapparch.architecture.di;

import android.app.Application;
import com.example.feedentry.di.ActivityBuilder;
import com.example.feedentry.di.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import sampleproj.charlesng0209.com.sampleapparch.architecture.MyApplication;


/**
 * Created by Charles Ng on 27/9/2017.
 */

@Component(modules = {
    AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    AppModule.class,
    ActivityBuilder.class})
public interface AppComponent {
  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

  void inject(MyApplication app);
}