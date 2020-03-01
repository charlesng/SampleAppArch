package com.cn29.aac.datasource.auth.db;

import com.cn29.aac.repo.user.LoginBean;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
