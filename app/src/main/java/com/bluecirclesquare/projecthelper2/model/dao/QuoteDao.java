package com.bluecirclesquare.projecthelper2.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.bluecirclesquare.projecthelper2.model.entity.Quote;
import java.util.List;


/**
 * This is the dao QuoteDao.class that is used for accessing quote records
 */

@Dao
public interface QuoteDao {

    @Insert
    long insert(Quote quote);

    @Insert
    List<Long> insert(List<Quote> quotes);

    @Query("SELECT * FROM Quote ORDER BY event DESC")
    List<Quote> select();

    @Query("SELECT *  FROM Quote WHERE quote_id = :quoteId")
    Quote get(Long quoteId);

    @Update
    int update(Quote quote);

    @Delete
    int delete(Quote quote);

    @Delete
    int delete(List<Quote> quotes);
}

