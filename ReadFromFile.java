package edu.onlineStore;

import java.util.*;
import java.util.regex.*;
import java.io.*;

/**
 * 
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 *
 */
public class ReadFromFile {
	/**
	 * read a file
	 * @param  fileName    name of the file
	 * @return             arraylist of the products parced from the file
	 * @throws IOException error when reading/parsing the file
	 */
	public static ArrayList<Product> readFile (String fileName) throws IOException {
		ArrayList<Product> fileProducts = new ArrayList<Product>();
		Pattern r = Pattern.compile("(\\w+)\\s?=?\\s?\"(.*)\"$");

		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		try {

			String line;

			int numMatches = 0;
			int numLines = 0;

			String type = null;
			String id = null;
			String name = null;
			String price = null;
			String year = null;

			String author = null;
			String publisher = null;
			String maker = null;

			Product newProduct = null;

			// Keep reading while there are still lines
		
			while ((line = reader.readLine()) != null) {
				if (line.equals("")) continue;
				Matcher m = r.matcher(line);
				while(m.find()){
					//System.out.println("Group 0: " + m.group(1) + " Group 1: " + m.group(2));
					if (m.group(1).equals("type"))
						type = m.group(2);
					else if (m.group(1).equals("productID"))
						id = m.group(2);
					else if (m.group(1).equals("name"))
						name = m.group(2);
					else if (m.group(1).equals("price"))
						price = m.group(2);
					else if (m.group(1).equals("year"))
						year = m.group(2);
					else if (m.group(1).equals("maker"))
						maker = m.group(2);
					else if (m.group(1).equals("authors"))
						author = m.group(2);
					else if (m.group(1).equals("publisher")) 
						publisher = m.group(2);

					numMatches ++;
				}
				numLines ++;
				if (numMatches != numLines) {
					System.out.println("Error Reading Line: [" + line + "] on line " + numLines);
					// Right now we won't worry about fixing errors, just return to the calling program
	
					throw new IOException("Error Reading Line: [" + line + "] on line " + numLines);

					// numMatches = numLines;
				}

				// Now check if 
				if (bookReady(type, id, name, price, year, author, publisher) || electronicReady(type, id, name, price, year, maker)) {
					if (type.equals("book"))
						newProduct = new Book(id, name, year, price, author, publisher);
					else if (type.equals("electronic"))
						newProduct = new Electronic(id, name, year, price, maker);
					else
						System.out.println("Unknown Type: " + type);
				}

				if (newProduct != null) {
					fileProducts.add(newProduct);
					
					type = null;
					id = null;
					name = null;
					price = null;
					year = null;

					author = null;
					publisher = null;
					maker = null;
					newProduct = null;
					continue;
				}

			}
		} catch (IOException e) {
			throw e;
		} finally {
			reader.close();
		}

		// Finished reading
		return fileProducts;

		
	}

	/**
	 * all of the elements are available that are needed for a book
	 * @param  type      type of product
	 * @param  id        id of product
	 * @param  name      name of product
	 * @param  price     price of product
	 * @param  year      year of product
	 * @param  author    author of product
	 * @param  publisher publisher of product
	 * @return           if the book is ready
	 */
	public static boolean bookReady (String type, String id, String name, String price, String year, String author, String publisher) {
		if (!(type == null || id == null || name == null || price == null || year == null || author == null || publisher == null))
			return true;
		return false;
	}

	/**
	 * all of the elements are available that are needed for a book
	 * @param  type      type of product
	 * @param  id        id of product
	 * @param  name      name of product
	 * @param  price     price of product
	 * @param  year      year of product
	 * @param  maker	 maker of product
	 * @return           if the book is ready
	 */
	public static boolean electronicReady (String type, String id, String name, String price, String year, String maker) {
		if (!(type == null || id == null || name == null || price == null || year == null || maker == null))
			return true;
		return false;
	}
	/**
	 * simple test
	 * @param args not used
	 */
	public static void main (String[] args) {
		System.out.println("Enter the file you would like to open:");
		
		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();

		System.out.println("Opening: " + input);

		try {
			readFile(input);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
