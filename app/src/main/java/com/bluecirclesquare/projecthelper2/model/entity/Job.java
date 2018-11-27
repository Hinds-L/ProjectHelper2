package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

/**
* This is the entity job.class that lists all the fields, with the necessary setters and getters
 * needed to populate ProjectDatabase.class
 */

@Entity(
    foreignKeys = @ForeignKey(entity = Quote.class, parentColumns = "quote_id", childColumns = "quote_id", onDelete = ForeignKey.RESTRICT)
)
public class Job {

  /**
   * Fields for entity job.class
   */

  @ColumnInfo(name = "job_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "quote_id", index = true)
  private Long quoteId;

  @ColumnInfo(name = "job_number", index = true, collate = ColumnInfo.NOCASE)
  private String jobNumber;

  private String description;

  private Date event;

  private String address;

  /**
   * This is the getter for the id field
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * This is the setter for the id field
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * This is the getter for the id field
   * @return jobNumber
   */
  public String getJobNumber() {
    return jobNumber;
  }

  /**
   * This is the getter for the id field
   * @param jobs
   */
  public void setJobNumber(String jobs) {
    this.jobNumber = jobNumber;
  }

  /**
   * This is the getter for the description field
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * This is the setter for the description field
   * @param description
   *
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * This is the getter for the event field
   * @return event
   */
  public Date getEvent() {
    return event;
  }

  /**
   * This is the setter for the event field
   * @param event
   */
  public void setEvent(Date event) {
    this.event = event;
  }

  /**
   * This is the getter for the address field
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * This is the setter for the address field
   * @param address
   *
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * This is the getter for the quoteid field
   * @return quoteId
   */
  public Long getQuoteId() {
    return quoteId;
  }

  /**
   * This is the setter for the quoteid field
   * @param quoteId
   */
  public void setQuoteId(Long quoteId) {
    this.quoteId = quoteId;
  }
}
