package edu.onlineStore;

import java.util.*;
import java.io.*;
/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class CommandLineInterface {
  public static StoreSearch store = new StoreSearch();
  public static Scanner sc = new Scanner(System.in);
  public static String fileName = null;

  public static final String[] addWords = {"add", "a", "ad"};
  public static final String[] searchWords = {"search", "s", "serch", "sarch", "seach"};
  public static final String[] quitWords = {"quit", "q", "qu"};

  // Show all of the options the user has in the menu
  public static void showCommandOptions () {
    System.out.println("Would you like to:\nAdd\nSearch\nQuit\n");
  }

  // Gather the information to add a product to the store
  public static void add () {
    boolean isBook = false;
    boolean isElectronic = false;

    // This string will hold the users current input
    String input = null;

    do {
      System.out.println("Enter type of product: [book or electronic]");
      input = sc.nextLine();
      if(input.equalsIgnoreCase("Book")) {
        // Must be a book
        isBook = true;
      }
      else if (input.equalsIgnoreCase("Electronic")) {
        // Must be an electronic
        isElectronic = true;
      }
      else {
        System.out.println("Error: the product must be either an Electronic or Book.");
      }
    } while (!isBook && !isElectronic);    
    String productType = input;

    boolean isUnique = false;
    do {
      System.out.format("Enter ID of product: [%d digit number]\n", Product.ID_LENGTH);
      input = sc.nextLine();
      if (!input.equals(""))
        isUnique = store.isUniqueId(input);
        if (!isUnique)
          System.out.println("This ID has already been added.  Please provide a unique ID.");
    } while (!Product.isValidId(input) || !isUnique);
    String productId = input;

    do {
      System.out.println("Enter name of product:");
      input = sc.nextLine();
    } while (!Product.isValidName(input));
    String productName = input;

    // Price is not mandatory    
    System.out.println("Enter price of product:");
    input = sc.nextLine();

    String productPrice = input;

    do {
      System.out.format("Enter year of product: [Number between %d and %d]\n", Product.MIN_YEAR_VALUE, Product.MAX_YEAR_VALUE);
      input = sc.nextLine();
    } while (!Product.isValidYear(input));
    String productYear = input;

    //Verify the product information is correct with sub functions
    if (productType.equalsIgnoreCase("Book")) {
      System.out.println("Enter author of product:");
      input = sc.nextLine();
      String productAuthor = input;

      System.out.println("Enter a publisher of product:");
      input = sc.nextLine();
      String productPublisher = input;

      Book newBook = new Book(productId, productName, productYear, productPrice, productAuthor, productPublisher);

      // Add the book to the store
      store.addToStore(newBook);
    }
    else if (productType.equalsIgnoreCase("Electronic")) {
      System.out.println("Enter maker of product:");
      input = sc.nextLine();
      String productMaker = input;

      Electronic newElectronic = new Electronic(productId, productName, productYear, productPrice, productMaker);

      // Add the electronic to the store
      store.addToStore(newElectronic);
    }
    else {
      System.out.println("Invalid product type.");
    }

    // Give extra new line at the end
    System.out.println("");
  }

  /**
   * allow the user to search
   */
  public static void search () {
    String id = null;
    String startYear = null;
    String endYear = null;
    ArrayList<String> keyWords = null;

    String input;

    boolean emptyType = false;
    boolean emptyYear = false;
    boolean emptyId = false;

    do {
      System.out.println("What is the ID of the product?");
      input = sc.nextLine();
      id = input;
      if (id.equals("")) {
        emptyId = true;
        id = null;
      } 
      else if (!Product.isValidId(id)) {
        id = null;
      }
    } while (!emptyId && id == null);
    
    String[] years;

    do {
      System.out.println("What time period is this product from?");
      input = sc.nextLine();
      years = input.split("-");
      if (years.length > 2) {
        System.out.println("Error, at most only provide the start and end years.");
      }
      startYear = years[0];
      if (years.length > 1)
      endYear = years[1];


      // For simplicity reset empty fields to null
      if (startYear != null && startYear.equals(""))
        startYear = null;
      if (endYear != null && endYear.equals(""))
        endYear = null;

      if (startYear == null && endYear == null)
        emptyYear = true;

      // check if the years are valid
      if (startYear != null) {
        if (!startYear.matches("^[- ]?[0-9]+$|^[0-9]+[- ]?$")) {
          System.out.println("Error: Invalid year.");
          startYear = null;
          endYear = null;
          continue;
        }
      }
      if (endYear != null) {
        if (!endYear.matches("^[- ]?[0-9]+$|^[0-9]+[- ]?$")) {
          System.out.println("Error: Invalid year.");
          startYear = null;
          endYear = null;
          continue;
        }
      }

      if (startYear  != null && endYear != null) {
        if (Integer.parseInt(startYear) > Integer.parseInt(endYear)) {
          System.out.println("Error: the start year cannot be greater than the ending year.");
          startYear = null;
          endYear = null;

          continue;
        }
      }
    } while (!emptyYear && !(startYear  != null || endYear != null));

    // if (!emptyYear) {
    //   System.out.println("startYear: " + startYear + " endyear: " + endYear);
    // }

    System.out.println("What are some keywords for the product? Seperate them by spaces.");
    input = sc.nextLine();
    System.out.println("\n");
    keyWords = new ArrayList<String>(Arrays.asList(input.split(" +")));
    
    Search search = new Search(id, startYear, endYear, keyWords);

    search.results = search.doSearch(store.productAList);

    StoreSearch.printSearchResults(search.results);
  }

  public static boolean containsIgnoreCase (String s, String[] list) {
    for (String string : list) {
      if (string.equalsIgnoreCase(s))
          return true;
    }
    return false;
  }

  public static void fileStep(String[] args) {
    boolean fileExists = false;
    String input = null;
    File file = null;

    if (args.length < 1)
      System.out.println("No file specified from command line, choose a file: ");
    else {
      file = new File(args[0]);
      if (file.exists()) {
        fileName = args[0];
        fileExists = true;
      }
      else
        System.out.println("File not found, choose a file:");
    }

    while(fileExists == false) {
      input = sc.nextLine();
      file = new File(input);
      if (file.exists()) {
        fileName = input;
        fileExists = true;
      }
      else
        System.out.println("File not found, please try again: ");
    }

    System.out.println("\n");

    // Now that we have a valid file, read it
    ArrayList<Product> storeUpload = new ArrayList<Product>();

    try{
      storeUpload = ReadFromFile.readFile(fileName);
    } catch (IOException e) {
      // Since file exists was already verified, this should not happen
      System.out.println("Error reading file, terminating program.");
      System.exit(0);
    }

    for (Product p: storeUpload) {
      store.addToStore(p);
    }

  }

  /**
   * quit and save the store to file
   */
  public static void doQuitOperations() {
    WriteToFile writer = new WriteToFile();
    System.out.println("Updating file: " + fileName);
    try {
      writer.writeFile(store.getStoreProducts(), fileName);
    } catch (Exception e) {
      System.out.println("An error occured when writing to file: " + e.getMessage());
    }
    System.exit(0);
  }

  public static void main (String[] args) {
    // Command line user interface
    fileStep(args);

    boolean run = true;
    while (run) {
      showCommandOptions();
      String input = sc.nextLine();

      if (containsIgnoreCase(input, addWords)) {
        System.out.println("Adding");
        add();
      }
      else if (containsIgnoreCase(input, searchWords)) {
        System.out.println("Searching");
        search();
      }
      else if (containsIgnoreCase(input, quitWords)) {
        System.out.println("Quiting");
        doQuitOperations();
      }
      else {
        System.out.println("Incorrect Input");
      }
    }
  }
}
// EOF
