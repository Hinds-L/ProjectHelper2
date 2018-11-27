package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/*
This is the Invoice class with the entity fields for creating the invoice database.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(entity = Job.class, parentColumns = "job_id", childColumns = "job_id", onDelete = ForeignKey.RESTRICT)
    }
)
public class Invoice {

  @ColumnInfo(name = "invoice_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "job_id", index = true)
  private long jobId;

  private Date completed;

  @NonNull
  private Date invoiced = new Date();

  private long price;

  private String address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCompleted() {
    return completed;
  }

  public void setCompleted(Date completed) {
    this.completed = completed;
  }

  @NonNull
  public Date getInvoiced() {
    return invoiced;
  }

  public void setInvoiced(@NonNull Date invoiced) {
    this.invoiced = invoiced;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getJobId() {
    return jobId;
  }

  public void setJobId(long jobId) {
    this.jobId = jobId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
