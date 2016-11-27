package edu.onlineStore;

/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class Electronic extends Product {
  protected String maker;

  public Electronic (String id, String name, String year, String price, String maker) {
    super(id, name, year, price);
        if (this.id != null) {
            this.type = "electronic";
            this.maker = maker;
        }
  }

  public String getMaker () {
    return maker;
  }

  public void setMaker (String newMaker) {
    this.maker = newMaker;
  }

  public String toString () {
    return super.toString() + "\nmaker = \"" + getMaker() +"\"";
  }

  public boolean equals (Electronic otherElectronic) {
    return super.equals(otherElectronic) && this.maker.equals(otherElectronic.maker);
  }

  public static void main(String[] args) {

  }
}
// EOF
