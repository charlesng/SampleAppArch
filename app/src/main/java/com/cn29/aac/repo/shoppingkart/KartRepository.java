package com.cn29.aac.repo.shoppingkart;

import com.cn29.aac.AppExecutors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 25/10/2017.
 */

@Singleton
public class KartRepository {

  private AppExecutors appExecutors;


  @Inject
  public KartRepository(AppExecutors appExecutors) {
    this.appExecutors = appExecutors;
  }


}
