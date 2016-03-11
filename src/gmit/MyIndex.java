package gmit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyIndex implements Index {

	private Set<String> ignoreWords = new HashSet<>(); // words that should NOT be included in the index
	private Dictionary dictionary; // dictionary used to create the index
	private Map<String, Set<Integer>> index = new HashMap<>(); // a list of words to list of pages they are on
	private int pages; // number of pages in the document
	private int wordCount; // number of words in the document

	public MyIndex(Dictionary dictionary, Set<String> ignoreWords, List<String> fileContents) {
		this.dictionary = dictionary;
		this.ignoreWords = ignoreWords;
		generateIndex(fileContents); // each String is a page
	}

	private void generateIndex(List<String> fileContents) {
		StringBuilder currentWord = new StringBuilder();
		for (int page = 0; page < fileContents.size(); page++) {
			// go through every page in the document
			for (int i = 0; i < fileContents.get(page).length(); i++) {
				// go through string contents of each page
				char ch = fileContents.get(page).charAt(i); // current character we're on
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
					// it's a letter we want to form a word with
					currentWord.append(ch);
				} else {
					String word = currentWord.toString().toUpperCase();
					if (!ignoreWords.contains(word) && dictionary.contains(word)) {
						addToIndex(word, page + 1); // add the word to the index
					}
					currentWord = new StringBuilder(); // onto next word
				}
			}
		} // for every page
	} // generate index

	private void addToIndex(String word, int page) {
		if (index.get(word) == null) { // the word isn't in the index
			Set<Integer> pageNumbers = new HashSet<>(); // create a new set of integers
			pageNumbers.add(page); // pages is the current page we're on
			index.put(word, pageNumbers);
		} else { // other wise we want to add the page number to the existing list
			index.get(word).add(page);
		}
	}

	@Override
	public Set<Integer> pageNums(String word) {
		if (index.get(word) == null) {
			return new HashSet<>(); // empty set, ie word doesn't appear on any page
		} else {
			return index.get(word); // return a list of integers indicating page numbers the word can be found
		}
	}

	@Override
	public boolean contains(String word) {
		return index.containsKey(word.toUpperCase()); // says if that word exists in the index
	}

	@Override
	public int getWordCount() {
		return wordCount; // number of words in document
	}

	@Override
	public int getPages() {
		return pages; // number of pages in document
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list;
		for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
			list = new ArrayList<Integer>(entry.getValue());
			Collections.sort(list); // display sorted order of pages instead of random order
			// iterate through the index giving information about each entry
			sb.append("================================================================================\n");
			sb.append("WORD: " + entry.getKey() + "\nDEFINITIONS: " + dictionary.getDetail(entry.getKey()) + "\nPAGES: "
					+ list + "\n\n");
		}
		return sb.toString();
	}

	@Override
	public List<String> getDefinitions(String word) {
		if (index.containsKey(word.toUpperCase())) { // word is in index
			return dictionary.getDetail(word.toUpperCase());
		} else { // word not in index
			return new ArrayList<String>();
		}
	}

}
