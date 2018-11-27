package com.bluecirclesquare.projecthelper2.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;


/**
 * This is the entity  Quote.class that lists all the fields, with the necessary setters and getters
 * needed to populate ProjectDatabase.class
 */

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

  /**
   * Thia is the getter for event
   * @return event date
   */
  public Date getEvent() {
    return event;
  }

  /**
   * Thia is the setter for event
   * @param event
   * @return date
   */
  public void setEvent(Date event) {
    this.event = event;
  }

  /**
   * Thia is the getter for id;
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * Thia is the setter for id
   * @param id
   * @return long
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Thia is the getter for quoteNumber
   * @return  a string quoteNumber
   */
  public String getQuoteNumber() {
    return quoteNumber;
  }

  /**
   * Thia is the getter for quoteNumber
   * @param quoteNumber
   */
  public void setQuoteNumber(String quoteNumber) {
    this.quoteNumber = quoteNumber;
  }

  /**
   * Thia is the getter for contact
   * @return contact
   */
  public String getContact() {
    return contact;
  }

  /**
   * Thia is the setter for contact
   * @param contact
   */
  public void setContact(String contact) {
    this.contact = contact;
  }

  /**
   * Thia is the getter for amount
   * @return amount
   */
  public long getAmount() {
    return amount;
  }

  /**
   * Thia is the setter for amount
   * @param amount
   */
  public void setAmount(long amount) {
    this.amount = amount;
  }

  /**
   * Thia is the getter for jobId
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Thia is the setter for description
   * @return description
   */
  public void setDescription(String description) {
    this.description = description;
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
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }
}

