package com.bluecirclesquare.projecthelper2.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import java.util.List;

/**
 * This is the dao InvoiceDao.class that is used for accessing invoice records
 */

@Dao
public interface InvoiceDao {

  @Insert
  long insert(Invoice invoice);

  @Insert
  List<Long> insert(List<Invoice> invoice);

  @Query("SELECT * FROM Invoice WHERE invoice_id = :invoiceId")
  Invoice get(long invoiceId);

  @Query("SELECT * FROM Invoice ORDER BY invoiced DESC")
  List<Invoice> select();

  @Update
  int update(Invoice invoice);

  @Delete
  int delete(Invoice invoice);

  @Delete
  int delete(List<Invoice> invoices);

}
