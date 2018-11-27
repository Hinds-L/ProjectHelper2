package com.bluecirclesquare.projecthelper2.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.bluecirclesquare.projecthelper2.model.entity.Job;
import java.util.List;

/**
 * This is the dao JobDao.class that is used for accessing job records
 */

@Dao
public interface JobDao {

  @Insert
  long insert(Job job);
  @Insert
  List<Long> insert(List<Job> jobs);

  @Query("SELECT * FROM Job ORDER BY event DESC")
  List<Job> select();

  @Update
  int update(Job job);

  @Delete
  int delete(Job job);

  @Delete
  int delete(List<Job> jobs);
}
