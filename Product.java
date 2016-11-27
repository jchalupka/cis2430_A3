package edu.onlineStore;

/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class Product {
  protected String type;
  protected String id;
  protected String name;
  protected String price;
  protected String year;

  public static final int ID_LENGTH = 6;
  public static final int MIN_YEAR_VALUE = 1000;
  public static final int MAX_YEAR_VALUE = 9999;

  // Constructors
  public Product (String id, String name, String year, String price) {
  	invariantChecker(id, name, year, price);
    this.id = id;
    this.name = name;
    this.year = year;
    this.price = price;
  }

  /**
   * checks to see if the inputs are acceptable for products
   * @param  id    product id
   * @param  name  product name
   * @param  year  product year
   * @param  price product price
   * @return       if the inputs are acceptable for products
   */
  public static boolean invariantChecker(String id, String name, String year, String price) {
  	// Check that the value is in between the min and max inclusive
    if (!isValidId(id))
    	throw new IllegalArgumentException("invalid product id");

    if (!isValidName(name))
    	throw new IllegalArgumentException("invalid produce name");

    if (!isValidYear(year))
    	throw new IllegalArgumentException("invalid product year");

    if (!isValidPrice(price))
    	throw new IllegalArgumentException("invalid product price");

    return true;
  }

  // Methods
  
  public String getType () {
    return type;
  }

  public void setType (String newType) {
    if(isValidType(newType))
      this.type = type;
  }

  public String getId () {
    return id;
  }

  public void setId(String newId) {
   	if(isValidId(newId)) 
    	this.id = newId;
  }

  public String getName () {
    return name;
  }

  public void setName (String newName) {
    if(isValidName(newName))
    	this.name = newName;
  }

  public String getYear () {
    return year;
  }

  public void setYear (String newYear) {
  	if(isValidYear(newYear))
    	this.year = newYear;
  }

  public String getPrice () {
    return price;
  }

  public void setPrice (String newPrice) {
    if(isValidPrice(newPrice))
    	this.price = newPrice;
  }

  public String toString () {
    return "type = \"" + getType() + "\"\n" +
           "productID = \"" + getId() + "\"\n" +
           "name = \"" + getName() + "\"\n" +
           "price = \"" + getPrice() + "\"\n" +
           "year = \"" + getYear() + "\""; 
  }

  public boolean equals (Product otherProduct) {
    if (otherProduct == null)
      return false;

    if (this.id.equals(otherProduct.id) && 
        this.name.equals(otherProduct.name) && 
        this.year.equals(otherProduct.year) &&
        this.price.equals(otherProduct.price))
      return true;
    else
      return false;
  }

  // Helper Methods
  private static boolean inRange (Integer x, Integer low, Integer high, Integer inclusive) {
    if(x > low - inclusive && x < high + inclusive)
      return true;
    return false;
  }

  public static boolean isValidType (String type) {
    if (type != null && (type.equals("book") || type.equals("electronic")))
      return true;
    return false;
  }

  public static boolean isValidId (String id) {
    boolean valid = id.length() == ID_LENGTH  && id.matches("[0-9]{6}");
    if (!valid) {
      System.out.format("Error: the Product ID must be %d digits.\n", ID_LENGTH);
      return false;
    }
    return true;
  }

  // Year must be from 1000 to 9999
  public static boolean isValidYear (String year) {
    boolean valid = year.matches("[1-9][0-9]{3}") /*&& inRange(Integer.parseInt(year), MIN_YEAR_VALUE, MAX_YEAR_VALUE,1)*/;
    if (!valid) {
      System.out.println("Error: the year must be a number between 1000 and 9999.\n");
      return false;
    }
    return true;
  }

  public static boolean isValidName (String name) {
    // No restrictions set, just need an input of any kind.
    boolean valid = name.matches(".+");
    if (!valid) {
      System.out.println("Error: product name is required.");
      return false;
    }
    return true;
  } 

  // Price must be a number with an integer component and can have two decimal places
  public static boolean isValidPrice (String price) {
    boolean valid = price.matches("^[0-9]+[.]?[0-9]{0,2}$|^$");
    if (!valid) {
      System.out.println("Error: please enter a valid price.");
      return false;
    }
    return true;
  }


  // Main
  public static void main (String[] args) {

  }
}
