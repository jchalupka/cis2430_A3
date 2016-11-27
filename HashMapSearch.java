import java.util.*;


/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class HashMapSearch {
	
	 
	public static HashMap<String,ArrayList<Integer>> nameMap = new HashMap<String,ArrayList<Integer>>();
	private static String cur = null;
	private static int currentPosition = 0;

	/**
	 * create a new hashmap (deletes the old one first)
	 * @param store products' names to be placed in the hashmap
	 */
	public static void createHashMap(ArrayList<Product> store) {	
		nameMap = new HashMap<String,ArrayList<Integer>>();	
		cur = null;
		currentPosition = 0;

		for (Product product: store) {
			updateHashMap(product);
		}

	}

	/**
	 * update the hashmap by inserting one new product
	 * @param product product to be inserted
	 */
	public static void updateHashMap(Product product) {
		StringTokenizer st = new StringTokenizer(product.name.toLowerCase());
		while (st.hasMoreTokens()) {
			cur = st.nextToken();
			try {
				nameMap.get(cur.toLowerCase()).add(currentPosition);
			} catch (NullPointerException e) {
				// If this string does not exist, add it to the map.
				nameMap.put(cur, new ArrayList<Integer>());
				nameMap.get(cur).add(currentPosition);
			}
		}
		currentPosition++;
	}

	/**
	 * search through the hashmap
	 * @param  search name to search for (as a string)
	 * @param  store  store to search through (incase create was not called)
	 * @return        products that match
	 */
	public static ArrayList<Product> searchHashMap(String search, ArrayList<Product> store) {
		search = search.toLowerCase();
		String[] searchWords = search.split(" ");

		return searchHashMap(searchWords, store);
	}

	/**
	 * search through the hashmap
	 * @param  searchWords search names to search for as an array of strings
	 * @param  store       store to search through
	 * @return             products that match
	 */
	public static ArrayList<Product> searchHashMap(String[] searchWords, ArrayList<Product> store) {
		ArrayList<String> p = new ArrayList<String>(Arrays.asList(searchWords));
		return searchHashMap(p, store);
	}

	/**
	 * search through the hash map
	 * @param  searchWords search names to search for as an arraylist of strings
	 * @param  store       store to search through
	 * @return             products that match
	 */
	public static ArrayList<Product> searchHashMap(ArrayList<String> searchWords, ArrayList<Product> store) {
		if (searchWords.size() == 0 || (searchWords.size() == 1 && searchWords.get(0).equals(""))) return store;
		// Incase the user forgot to do createHashMap
		for (String word : searchWords)
			word.toLowerCase();
		if (nameMap == null) createHashMap(store);
		

		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (String word: searchWords) {
			word = word.toLowerCase();
			// If there were no products found
			if (nameMap.get(word) == null) {
				return new ArrayList<Product>();
			}

			if (indexes.size() == 0)
				indexes = nameMap.get(word);
			else {
				indexes.retainAll(nameMap.get(word));
			}
		}

		ArrayList<Product> results = new ArrayList<Product>();
		for (Integer cur: indexes) {
			results.add(store.get(cur));
		}

		return results;
	}

	/**
	 * get a string of the hash map
	 * @return the hash map in string format
	 */
	public static String hashMapToString() {
		Set set = nameMap.entrySet();
		Iterator i = set.iterator();

		String output = "";
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			output = output + me.getKey() + ": ";
			output = output + me.getValue() + "\n";
		}

		return output;
	}

	public static void main(String[] args) {

	}
}