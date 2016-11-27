package edu.onlineStore;

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

  public boolean equals (Book otherBook) {
    return (super.equals(otherBook) 
      && this.author.equals(otherBook.author) 
      && this.publisher.equals(otherBook.publisher));
  }
  // public static void main (String[] args) {
  //   // Do testing here
  //   int testsCorrect = 0;
  //   int NUMTESTS = 8;

  //   String testId = "123456";
  //   String testName = "Harry Potter";
  //   String testYear = "2009";
  //   String testPrice = "12.49";
  //   String testAuthor = "JK Rowling";
  //   String testPublisher = "Bloomsbury";

  //   System.out.println("Creating a valid Book with all attributes:");
  //   Book book = new Book(testId, testName, testYear, testPrice, testAuthor, testPublisher);
  //   System.out.println("Testing the correctness of all Book attributes");
  //   if (book.id.equals(testId)) {
  //     System.out.println("Id Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Id Error.");
  //   }

  //   if (book.name.equals(testName)) {
  //     System.out.println("Name Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Name Error.");
  //   }

  //   if (book.year.equals(testYear)) {
  //     System.out.println("Year Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Year Error.");
  //   }

  //   if (book.price.equals(testPrice)) {
  //     System.out.println("Price Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Price Error.");
  //   }

  //   if (book.author.equals(testAuthor)) {
  //     System.out.println("Author Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Author Error.");
  //   }

  //   if (book.publisher.equals(testPublisher)) {
  //     System.out.println("Publisher Correct.");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("Publisher Error.");
  //   }

  //   System.out.println("Changing the author attribute:");
  //   String newAuthor = "Not JK Rowling";
  //   book.setAuthor(newAuthor);

  //   if (book.getAuthor().equals(newAuthor)) {
  //     System.out.println("New Author Correct");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("New Author Incorrect.");
  //   }

  //   System.out.println("Changing the publisher attribute:");
  //   String newPublisher = "Fake publisher";
  //   book.setPublisher(newPublisher);
  //   if (book.getPublisher().equals(newPublisher)) {
  //     System.out.println("New Publisher Correct");
  //     testsCorrect++;
  //   }
  //   else {
  //     System.out.println("New Publisher Incorrect.");
  //   }

  //   System.out.println("Displaying toString for Electronic:");
  //   System.out.println(book.toString());

  //   System.out.format("Total number of tests correct = %d/%d.\n", testsCorrect, NUMTESTS);
  // }

}
// EOF