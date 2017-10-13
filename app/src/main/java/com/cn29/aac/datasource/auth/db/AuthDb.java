package com.cn29.aac.datasource.auth.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.cn29.aac.repo.user.LoginBean;

/**
 * Created by Charles Ng on 13/10/2017.
 */
@Database(entities = {LoginBean.class}, version = 1)
public abstract class AuthDb extends RoomDatabase {

  public abstract AuthDao authDao();

}
