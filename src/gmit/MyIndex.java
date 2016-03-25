package gmit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyIndex implements Index {
	private Set<String> invalidWords = new HashSet<>(); // words that weren't in dictionary and/or were in ignoreWords
	private Set<String> ignoreWords = new HashSet<>(); // words that should NOT be included in the index
	private Dictionary dictionary; // dictionary used to create the index
	private Map<String, Set<Integer>> index = new HashMap<>(); // a list of words to list of pages they are on
	private int wordCount = 0; // number of words in the document

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
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '\'') {
					// it's a letter we want to form a word with
					currentWord.append(ch);
				} else {
					wordCount++;
					String word = currentWord.toString().toUpperCase();

					// cut off commas in the cases that there is one at both ends, one at the start, or one at the end
					// commas before the last character are okay
					if (word.length() > 2 && word.charAt(0) == '\'' && word.charAt(word.length() - 2) == '\'') {
						word = word.substring(1, word.length() - 1); // cut off first and last
					} else if (word.length() > 0 && word.charAt(0) == '\'') {
						word = word.substring(1); // cut off first
					} else if (word.length() > 2 && word.charAt(word.length() - 1) == '\'') {
						word = word.substring(0, word.length() - 1); // cut off last
					}

					if (!ignoreWords.contains(word) && dictionary.contains(word)) {
						addToIndex(word, page + 1); // add the word to the index
					} else {
						invalidWords.add(word); // add word to set of invalid words
					}
					currentWord = new StringBuilder(); // onto next word
				}
			}
		} // for every page
		/*
		 * Generating the index is an O(n) operation, each line of the document
		 * is processed and constant time operations such as adding to various
		 * maps are performed. Only a single pass is needed to generate the
		 * index which no words being revisted.
		 */
	} // generate index

	private void addToIndex(String word, int page) {
		if (index.get(word) == null) { // the word isn't in the index
			Set<Integer> pageNumbers = new HashSet<>(); // create a new set of integers
			pageNumbers.add(page); // pages is the current page we're on
			index.put(word, pageNumbers);
		} else { // other wise we want to add the page number to the existing list
			index.get(word).add(page);
		}
		/*
		 * A constant time operation used in the index
		 * creation.
		 */
	}

	@Override
	public Set<Integer> pageNums(String word) {
		if (index.get(word) == null) {
			return new HashSet<>(); // empty set, ie word doesn't appear on any page
		} else {
			return index.get(word); // return a list of integers indicating page numbers the word can be found
		}
		// constant time
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
		/*
		 * Here I convert the set into a list and then sort it.
		 * I chose to use a set in order to avoid duplicates,
		 * but I wanted to show the page appearances in sorted order
		 * sorting an arraylist is a nLog(n) operation.
		 */
	}

	@Override
	public List<String> getDefinitions(String word) {
		if (index.containsKey(word.toUpperCase())) { // word is in index
			return dictionary.getDetail(word.toUpperCase());
		} else { // word not in index
			return new ArrayList<String>();
		}
		// constant time operation
	}

	@Override
	public Set<String> getInvalidWords() {
		return invalidWords;
		// constant time
	}

	@Override
	public List<String> didYouMean(String word) {
		// given a word, give a list of words that are close to the one searched for
		List<String> wordsToReccommend = new ArrayList<>();
		for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
			if (closeMatch(word.toUpperCase(), entry.getKey())) {
				wordsToReccommend.add(entry.getKey());
			}
		}
		/*
		 * time complexiy of O(n * m) where n is the number
		 * of keys in the map and m is the time complexity of the 
		 * closeMatch method
		 */

		return wordsToReccommend;
	}

	private boolean closeMatch(String word, String closeToThis) {
		// returns true if the word is similar to closeToThis

		if (word.length() < 3) {
			return false; // don't do it for short words
		}

		int[] counts1 = new int[26]; // store counts for each letter in word
		int[] counts2 = new int[26]; // store counts for each letter in closeToThis

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				counts1[ch - 'A']++; // count them
			}

		}
		for (int i = 0; i < closeToThis.length(); i++) {
			char ch = closeToThis.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				counts2[ch - 'A']++; // count them
			}

		}
		int knocksAgainst = 0; // indicating differences in the words

		for (int i = 0; i < 26; i++) {
			if (counts1[i] != counts2[i]) {
				knocksAgainst++; // if a specific letter count is not the same, one knock against the word
			}
		}

		return knocksAgainst < 2;
		/*
		 * has a running time of O(3n) where n is the number of letters
		 * in the alphabet.
		 */

		/* mainly just to give plurals and vica versa if the other isn't in the index
		eg senses is not in, but sense is, continues not in, but continue is. Main intended purpose of this functionality
		also helps with small typos, stuff like that */
	}

	@Override
	public Set<String> getKeys() {
		return dictionary.getAllWords();
		// constant time
	}

}
