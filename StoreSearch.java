import java.util.*;
/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class StoreSearch {
	static ArrayList<Product> productAList = new ArrayList<Product>();

	private static Scanner sc = new Scanner(System.in);

	private ArrayList<Product> products = new ArrayList<Product>();

	/**
	* Create two empty arraylists.
	* One for Books and Electronics respectively.
	*/
	public StoreSearch() {
		// Two ArrayLists
		productAList = new ArrayList<Product>();
	}

	/**
	* Add a Product to the StoreSearch object.
	* The Product is checked to be guarenteed to have a unique ID to all other Products before being added.
	* @param productToAdd is the Product to be added.
	*/
	public static void addToStore (Product productToAdd) {
		if (productToAdd == null)
			throw new IllegalArgumentException("Invalid Product");
		// Check for duplicates of the given productID
		if (isUniqueId(productToAdd.getId())) {
		  productAList.add(productToAdd);
		  HashMapSearch.updateHashMap(productToAdd);
		}
		else {
		  System.out.println("This ID has already been added.  Please provide a unique ID.");
		  throw new IllegalArgumentException("This ID has already been added");
		}
		return;
	}


	/**
	* Print out all of the products in the store.
	*/
	public void showProducts () {
		Iterator currentProduct = productAList.iterator();
		while (currentProduct.hasNext()) {
			System.out.println(currentProduct.next());
		}
	}

	/**
	* If the product is unique add it to the search results
	* @param newProduct A product
	* @param results    Search results
	*/
	private void addUnique (Product newProduct, ArrayList<Product> results) {
		if (isUniqueId(newProduct.getId(), results)) {
		  results.add(newProduct);
		}
	}

	/**
	* Print all products in the results arraylist
	* @param results [contains products added by a search]
	*/
	public static void printSearchResults (ArrayList<Product> results) {
		Iterator<Product> currentResults = results.iterator();

		while (currentResults.hasNext()) {
		  System.out.println(currentResults.next().toString() + "\n");
		}
	}

	/**
	* Check if the ID is unique in the context of the store.
	* @param  id Product Identification Number
	* @return    True if the id is unique to the store object.
	*/
	public static boolean isUniqueId (String id) {
		// Check for id in books
		Iterator<Product> currentProduct = productAList.iterator();
		while (currentProduct.hasNext()) {
		  if(currentProduct.next().getId().equals(id)) {
			return false;
		  }
		}

		return true;
	}

	/**
	* Checks if the id is unique in the context of search results.
	* @param  id      Product ID
	* @param  results Search Results
	* @return         If the ID is unique
	*/
	public boolean isUniqueId (String id, ArrayList<Product> results) {
		// Check for id previous results
		Iterator<Product> current = results.iterator();
		while (current.hasNext()) {
		  if(current.next().getId().equals(id)) {
			return false;
		  }
		}

		return true;
	}

	/**
	* Check if the ID is unique.
	* @param  id Product Identification Number
	* @return    True if the id is unique to the store object.
	*/
	public boolean isUniqueId (Integer id) {
		return isUniqueId(String.valueOf(id));
	}

	/**
	 * get the products in the store
	 * @return arraylist of products
	 */
	public static ArrayList<Product> getStoreProducts() {
		return productAList;
	}

	/**
	* StoreSearch
	* Class of a search for a store.
	*
	* Each store has 2 ArrayLists, representing the types
	* of products that can be sold in the store:
	*
	* Books
	* Electronics
	*
	* This class allows users to:
	*  -verify if an ID is unique to the store
	*  -search for products
	*  -add items to the store
	*  -list items in the store
	* 
	* @param args Unused
	*/
	public static void main (String[] args) {

	}
}

