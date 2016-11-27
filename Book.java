/*This is new code*/
/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class Book extends Product {
  protected String author;
  protected String publisher;

  public Book (String id, String name, String year, String price, String author,  String publisher) {
    super(id, name, year, price);
    if (this == null)
    	throw new NullPointerException("invalid null product");
    else {
      this.type = "book";
    	this.author = author;
    	this.publisher = publisher;
    }
  }

  public Book(Book toCopy) {
    super(toCopy);
    this.type = toCopy.type;
    this.author = toCopy.author;
    this.publisher = toCopy.publisher;
  }

  public String getAuthor () {
    return author;
  }

  public String getPublisher () {
    return publisher;
  }

  public void setAuthor (String newAuthor) {
    this.author = newAuthor;
  }  

  public void setPublisher (String newPublisher) {
    this.publisher = newPublisher;
  } 

  public String toString () {
    return super.toString() + "\nauthors = \"" + getAuthor() + "\"\npublisher = \"" + getPublisher() + "\"";
  }

  public boolean equals (Object otherBook) {
    if ((otherBook == null) || (otherBook.getClass() != this.getClass()))
      return false;
    Book book = (Book) otherBook;
    return (super.equals(book) 
      && this.author.equals(book.author) 
      && this.publisher.equals(book.publisher));
  }

}
// EOF