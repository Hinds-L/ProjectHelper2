package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * This is the entity invoice.class that lists all the fields, with the necessary setters and getters
 * needed to populate ProjectDatabase.class the database
 */

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Job.class, parentColumns = "job_id", childColumns = "job_id", onDelete = ForeignKey.RESTRICT)
    }
)
/**
 * Holds the fields necessary to populate the invoice database
 */
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

  /**
   *  This is the getter for id
   * @return id to display
   */
  public long getId() {
    return id;
  }

  /**
   * This is the setter for id
   * @param  id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Thia is the getter for completed
   * @return completed
   */
  public Date getCompleted() {
    return completed;
  }

  /**
   * Thia is the setter for completed
   * @return completed
   */
  public void setCompleted(Date completed) {
    this.completed = completed;
  }

  /**
   * Thia is the getter for invoiced
   * @return invoiced
   */
  @NonNull
  public Date getInvoiced() {
    return invoiced;
  }
  /**
   * Thia is the getter for invoiced
   * @return invoiced
   */
  public void setInvoiced(@NonNull Date invoiced) {
    this.invoiced = invoiced;
  }

  /**
   * Thia is the getter for price
   * @return price
   */
  public long getPrice() {
    return price;
  }

  /**
   * Thia is the setter for price
   * @return price
   */
  public void setPrice(long price) {
    this.price = price;
  }

  /**
   * Thia is the getter for JobId
   * @return jobId
   */
  public long getJobId() {
    return jobId;
  }

  /**
   * Thia is the setter for jobId
   * @return jobId
   */
  public void setJobId(long jobId) {
    this.jobId = jobId;
  }

  /**
   * Thia is the getter for address
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Thia is the setter for address
   * @return address
   */
  public void setAddress(String address) {
    this.address = address;
  }
}
