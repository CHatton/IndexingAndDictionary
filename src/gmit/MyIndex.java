package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyIndex implements Index {

	private Set<String> ignoreWords = new HashSet<>(); // words that should NOT be included in the index
	//private Map<String, WordDetail> index = new HashMap<>(); // the index itself
	private Dictionary dictionary; // dictionary used to create the index
	private Map<String, Set<Integer>> index = new HashMap<>(); // a list of words to list of pages they are on
	private int pages; // number of pages in the document

	public MyIndex(String pathToFile, String pathToDictionary, String pathToIgnoreWords) {
		try {
			pages = 0;// initiall have 0 pages
			populateIgnoreWords(pathToIgnoreWords); // fill up the set of words to ignore
			this.dictionary = new Dictionary(pathToDictionary); // create the dictionary with the file provided
			generateIndex(pathToFile); // create the index using dictionary and ignore words
		} catch (IOException e) {
			System.out.println("There was an error creating the index for the file.");
		}
	}

	private void populateIgnoreWords(String pathToIgnoreWords) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToIgnoreWords));
		String next;
		while ((next = reader.readLine()) != null) {
			ignoreWords.add(next);
			// add each line of the file as a word to ignore and not add to the index
		}
		reader.close();
	}

	private void generateIndex(String pathToFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		StringBuilder currentWord = new StringBuilder(); // keeps track of each word

		// page goes up every 40 lines
		int lineCount = 0;
		String next = reader.readLine();
		while (next != null) {
			lineCount++; // one more line
			if (lineCount == 40) {
				pages++;// one next page for every 40 lines
				lineCount = 0; // reset the line count
			}

			for (int i = 0; i < next.length(); i++) {
				char ch = next.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
					// it's a letter we want to form a word with
					currentWord.append(ch);
				} else {
					String word = currentWord.toString();
					if (!ignoreWords.contains(word) && dictionary.contains(word)) {
						addToIndex(word); // add the word to the index
					}
					currentWord = new StringBuilder();
				}
			}
			next = reader.readLine(); // move onto next line
		}
		
		System.out.println(index);
		reader.close();
	}
	
	private void addToIndex(String word){
		if(index.get(word) == null){ // the word isn't in the index
			Set<Integer> pageNumbers = new HashSet<>();
			pageNumbers.add(pages); // pages is the current page we're on
			index.put(word, pageNumbers);
		} else{ // other wise we want to add the page number to the existing list
			index.get(word).add(pages);
		}
	}

	@Override
	public Set<Integer> pageNums(String word) {
		return index.get(word); // return a list of integers indicating page numbers the word can be found
	}

	@Override
	public boolean contains(String word) {
		return index.containsKey(word); // says if that word exists in the index
	}

}
