package edu.onlineStore;

import java.util.*;

/**
 * 
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 *
 */
public class Search {
	public ArrayList<Product> results;

	public String id;
	public String startYear;
	public String endYear;
	public ArrayList<String> keyWords;

	/**
	 * Constructor for search
	 * @param  id        id to search for
	 * @param  startYear year to search for at and after
	 * @param  endYear   year to search at and before
	 * @param  keyWords  key words in name field to search for
	 */
	public Search(String id, String startYear, String endYear, ArrayList<String> keyWords) {
		if (id == null || id.equals("")) this.id = null;
		else this.id = id;

		if (startYear == null || startYear.equals("")) this.startYear = null;
		else this.startYear = startYear;

		if (endYear == null || endYear.equals("")) this.endYear = null;
		else this.endYear = endYear;
		
		if (keyWords == null || keyWords.size() == 0) this.keyWords = null;
		else this.keyWords = keyWords;
	}

	/**
	 * perform a search
	 * @param  store all the products in the store (or maybe a subset if you want)
	 * @return       products that match the search request
	 */
	public ArrayList<Product> doSearch(ArrayList<Product> store) {
		this.results = store;
		
		if (keyWords != null)
			this.filterKeyWords();
		if (id != null)
			this.filterId();
		if (startYear != null || endYear != null)
			this.filterYear();
		
		return results;
	}

	/**
	 * remove all products that do not match the id [sequentially]
	 */
	private void filterId() {
		Iterator<Product> iter = this.results.iterator();
		while(iter.hasNext()) {
			Product product = iter.next();
			if(!this.id.equals(product.id))
				iter.remove();
		}
	}

	/**
	 * remove all products that do not match the year [sequentially]
	 */
	private void filterYear() {
		Iterator<Product> iter = this.results.iterator();
		while (iter.hasNext()) {
			Product product = iter.next();
			if((startYear != null )&& (Integer.valueOf(this.startYear) > Integer.valueOf(product.year)))
				// results.remove(index);
				iter.remove();
			else if((endYear != null )&& (Integer.valueOf(this.endYear) < Integer.valueOf(product.year)))
				// results.remove(index);
				iter.remove();
		}
	}

	/**
	 * remove all products that do not match the key words, this can either be done sequentially or iteratively, but is being done iteravely at this time.
	 */
	private void filterKeyWords() {
		//iterativeKeyWordSearch();
		hashKeyWordSearch();
	}

	/**
	 * search for products names using a hashmap
	 */
	public void hashKeyWordSearch() {
		// By the end this.results should be equal to the output of the search.
		HashMapSearch search = new HashMapSearch();
		search.createHashMap(this.results);

		this.results = search.searchHashMap(this.keyWords, this.results);
	}

	/**
	 * search for products names iteravely
	 */
	public void iterativeKeyWordSearch() {
		String cur;
		Iterator<Product> iter = this.results.iterator();
		while(iter.hasNext()) {
			Product product = iter.next();
			// There is not a word in the keywords that is not in the product
			for (String word : this.keyWords) {
				if (!product.getName().toLowerCase().contains(word.toLowerCase())) {
					iter.remove();
					break;
				}
			}
		}
	}

	/**
	 * make string of the items returning
	 * @return string of the items returning
	 */
	public String toString() {
		String toReturn = "";
		toReturn += "Returning " + this.results.size() + " items:\n";
		for (Product result : this.results) {
			toReturn += result.toString() + "\n";
		}

		return toReturn;
	}

	public static void main(String[] args) {
		Search search = new Search(null, null, null, null);
	}

}





