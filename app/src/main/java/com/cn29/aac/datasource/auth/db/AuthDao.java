package com.cn29.aac.datasource.auth.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.cn29.aac.repo.user.LoginBean;

/**
 * Created by Charles Ng on 13/10/2017.
 */

@Dao
public interface AuthDao {

  @Query("SELECT * FROM Auth Where email = :email limit 1")
  LiveData<LoginBean> getLogin(String email);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(LoginBean loginBean);
}
