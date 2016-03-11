package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDocument implements Document {
	private List<String> fileContents = new ArrayList<>(); // entire file in nested list format
	private Index index; // the document's index

	public FileDocument(String pathToFile, String pathToDictionary, String pathToIgnoreWords) {
		try {
			Dictionary d = new Dictionary(pathToDictionary); // creates dictionary from given path
			Set<String> ignoreWords = populateIgnoreWords(pathToIgnoreWords); // creates list of words to ignore
			fillContents(pathToFile); // fill document contents from file
			index = new MyIndex(d, ignoreWords, fileContents); // create index with dictionary, ignore words and fileContents
		} catch (IOException e) {
			System.out.println("There was an error creating the document.");
			System.out.println("Please check for the following files: ");
			System.out.println(pathToFile + ", " + pathToDictionary + ", " + pathToIgnoreWords);
		} // if there's an error with one or more of the files
	} // constructor

	private Set<String> populateIgnoreWords(String pathToIgnoreWords) throws IOException {
		Set<String> ignoreWords = new HashSet<>(); // start off empty
		BufferedReader reader = new BufferedReader(new FileReader(pathToIgnoreWords));
		String next;
		while ((next = reader.readLine()) != null) {
			ignoreWords.add(next.toUpperCase()); // add upper case of word
			// add each line of the file as a word to ignore and not add to the index
		}
		reader.close();
		return ignoreWords; // give back set
	}

	private void fillContents(String pathToFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		StringBuilder page = new StringBuilder(); // represents a full page
		String next;

		while ((next = reader.readLine()) != null) {

			for (int i = 0; i < 40; i++) {
				if (next != null) { // don't want to add nulls to end of text
					page.append(next + "\n"); // add 40 non null lines of text to the single string
				}
				next = reader.readLine();
			}
			fileContents.add(page.toString()); // add this page to the arraylist
			page = new StringBuilder(); // empty the string
		}
		reader.close();
	}

	@Override
	public int pageCount() {
		return index.getPages(); // total number of pages in the document
	}

	@Override
	public void printFullDocument() {
		for (int i = 0; i < fileContents.size(); i++) {
			System.out.println(fileContents.get(i)); // each string is a page
		}
	}

	@Override
	public void printSinglePage(int page) {
		if (page < 1 || page > fileContents.size()) { // prevent invalid option		
			System.out.println("Invalid page number. Please enter between 1 and " + fileContents.size());
		} else { // valid page number
			
			System.out.println("PAGE: " + page);
			System.out.println("===========================================================");
			System.out.println(fileContents.get(page - 1)); // page -1 so user enters 1 for index 0 etc
			System.out.println("===========================================================");
			
		}
	}

	@Override
	public List<Integer> pageNums(String word) {
		List<Integer> pageNumbers = new ArrayList<>(index.pageNums(word.toUpperCase()));
		Collections.sort(pageNumbers);
		return pageNumbers;
	}

	@Override
	public boolean contains(String word) {
		return index.contains(word); // delegate to index
	}

	@Override
	public int wordCount() {
		return index.getWordCount();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Word Count: " + wordCount() + "\n");
		sb.append("Num Pages: " + pageCount() + "\n");
		return sb.toString();
	}

	@Override
	public void printIndex() {
		System.out.println(index); // prints the toString from index
	}

	@Override
	public List<String> getDefinitions(String word) {
		return index.getDefinitions(word);
	}

	@Override
	public void printAllPagesWith(String word) {
		
		List<Integer> pageNumbers = pageNums(word);
		// get list of pages word is on
		for(Integer page: pageNumbers){
			printSinglePage(page); // print out all of them
		}
	}

}
