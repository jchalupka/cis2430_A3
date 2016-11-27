package edu.onlineStore;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
/**
 * Made By: Jordan Chalupka
 * Course: CIS*2430
 * Semester: F 2016
 *
 * Assignment 2
 */
public class WriteToFile {
	/**
	 * Write to a file
	 * @param  store     product store
	 * @param  fileName  file name to write to
	 * @throws Exception could not write to file
	 */
	public static void writeFile(ArrayList<Product> store, String fileName)
		throws Exception {

		PrintWriter writer = null;
		try  {
			writer = new PrintWriter(fileName, "UTF-8");
			Iterator itr = store.iterator();
			while(itr.hasNext()) {
				Product cur = (Product)itr.next();
				writer.write(cur.toString() + "\n\n");
			}
			

		} catch (Exception e) {
			throw new Exception("Error writing to file.");
		} finally {
			writer.close();
		}

	}

	public static void main(String[] args) {

	}
}