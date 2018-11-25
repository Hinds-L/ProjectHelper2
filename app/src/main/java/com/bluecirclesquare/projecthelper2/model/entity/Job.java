package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = @ForeignKey(entity = Quote.class, parentColumns = "quote_id", childColumns = "quote_id", onDelete = ForeignKey.RESTRICT)
)
public class Job {

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getJobNumber() {
    return jobNumber;
  }

  public void setJobNumber(String jobs) {
    this.jobNumber = jobNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEvent() {
    return event;
  }

  public void setEvent(Date event) {
    this.event = event;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getQuoteId() {
    return quoteId;
  }

  public void setQuoteId(Long quoteId) {
    this.quoteId = quoteId;
  }
}
