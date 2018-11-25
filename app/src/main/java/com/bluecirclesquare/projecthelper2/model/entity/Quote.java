package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity
public class Quote {

  @ColumnInfo(name = "quote_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "quote_number", index = true, collate = ColumnInfo.NOCASE)
  private String quoteNumber;

  private String contact;

  private long amount;

  private String description;

  private Date event;

  private String address;

  public Date getEvent() {
    return event;
  }

  public void setEvent(Date event) {
    this.event = event;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getQuoteNumber() {
    return quoteNumber;
  }

  public void setQuoteNumber(String quoteNumber) {
    this.quoteNumber = quoteNumber;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}

