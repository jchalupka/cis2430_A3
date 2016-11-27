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

  public Electronic(Electronic toCopy) {
    super(toCopy);
    this.type = toCopy.type;
    this.maker = toCopy.maker;
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

  public boolean equals (Object otherElectronic) {
    if ((otherElectronic == null) || (otherElectronic.getClass() != this.getClass()))
      return false;
    Electronic electronic = (Electronic)otherElectronic;
    return super.equals(electronic) 
      && this.maker.equals(electronic.maker);
  }

  public static void main(String[] args) {

  }
}
// EOF
