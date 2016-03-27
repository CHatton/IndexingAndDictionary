package gmit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcreteDocument implements Document {
	private List<String> fileContents = new ArrayList<>();
	// entire file in nested list format 1 String per page, a page consists of 40 lines
	private String source;

	public ConcreteDocument(List<String> fileContents) {
		this.fileContents = fileContents;
	} // constructor

	public void setSource(String source){
		this.source = source;
	}
	
	public String getSource(){
		return source;
	}

	@Override
	public int pageCount() {
		return fileContents.size(); // total number of pages in the document
	}
	// constant time operation O(1)

	@Override
	public String fullDocument() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fileContents.size(); i++) {
			sb.append(singlePage(i + 1)); // print every individual page
		}
		return sb.toString();
	}
	/*
	 * an O(n) operation, every element of the arraylist must be added to the
	 * stringbuilder and returned as a string
	 */

	@Override
	public String singlePage(int page) {
		StringBuilder sb = new StringBuilder();
		if (page < 1 || page > fileContents.size()) { // prevent invalid option
			sb.append("Invalid page number. Please enter between 1 and " + fileContents.size());
		} else { // valid page number
			sb.append("PAGE: " + page + "\n");
			sb.append("========================================================================\n");
			sb.append(fileContents.get(page - 1) + "\n"); // page -1 so user
															// enters 1 for
															// index 0 etc
			sb.append("========================================================================\n");
		}
		return sb.toString();
		/*
		 * Selecting any element from an arraylist at a known index is a
		 * constant time operation, so printing out any given page is O(1) time
		 * complexity
		 */
	}

	@Override
	public String pageRange(int from, int to) {
		StringBuilder sb = new StringBuilder();
		for (int i = from; i < to; i++) {
			sb.append(singlePage(i));
		}
		return sb.toString();
	}

	/*
	 * O(n) time complexity where n is the number of pages between from and to
	 */


	@Override
	public List<Integer> loosePhraseSearch(String phrase, Index index) {
		List<Integer> pagesItAppearsOn = new ArrayList<>();
		List<String> validWords = new ArrayList<>();
		phrase = phrase.trim();
		String[] words = phrase.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (index.contains(words[i])) {
				validWords.add(words[i]); // add valid words to the ones to
											// check
			}
		}
		List<Integer> pagesToSearch;
		if (validWords.size() == 0) {
			// need to search every page, worst case
			List<Integer> allPages = new ArrayList<>();
			for (int i = 0; i < fileContents.size(); i++) {
				allPages.add(i + 1); // add all pages
			}
			pagesToSearch = new ArrayList<>(allPages);
		} else {
			// there are valid words, pick the rarest one and search those pages
			String rarestWord = ""; // just a default value
			int rarestCount = Integer.MAX_VALUE; // guarantee we'll have a
													// smaller size
			for (String word : validWords) {
				if (index.pageNums(word.toUpperCase()).size() < rarestCount) {
					rarestWord = word; // find the word with the least number of
										// occurrences
										// means least number of pages to search
				}
			}
			pagesToSearch = index.pageNums(rarestWord.toUpperCase());
			// search only pages containing a valid word
		}

		for (Integer page : pagesToSearch) {
			String[] currentPage = fileContents.get(page - 1).toUpperCase().split(" ");
			Set<String> pageAsSet = new HashSet<String>(Arrays.asList(currentPage));
			// convert page set of words
			Set<String> phraseAsSet = new HashSet<String>(Arrays.asList(phrase.toUpperCase().split(" ")));
			// convert phrase to set of words

			if (pageAsSet.containsAll(phraseAsSet)) {
				pagesItAppearsOn.add(page);
			}
		}
		Collections.sort(pagesItAppearsOn);
		return pagesItAppearsOn;
	}

	@Override
	public List<Integer> exactPhraseSearch(String phrase,Index index) {

		// if it's on list of restricted words, ignore it
		List<Integer> pagesItAppearsOn = new ArrayList<>();
		List<String> validWords = new ArrayList<>();
		phrase = phrase.trim();
		String[] words = phrase.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (index.contains(words[i])) {
				validWords.add(words[i]); // add valid words to the ones to
											// check
			}
		}

		List<Integer> pagesToSearch; // this will be all the pages we need to
									// search,
									// either all of them, or the lowest possible number of pages

		if (validWords.size() == 0) { // no valid words
			List<Integer> allPages = new ArrayList<>();
			for (int i = 0; i < fileContents.size(); i++) {
				allPages.add(i + 1); // all pages to be searched
			}
			pagesToSearch = new ArrayList<>(allPages); // search all pages
		} else {
			// there are valid words, pick the rarest one and search those pages
			String rarestWord = ""; // just a default value
			int rarestCount = Integer.MAX_VALUE; // guarantee we'll have a
													// smaller size
			for (String word : validWords) {
				if (index.pageNums(word.toUpperCase()).size() < rarestCount) {
					rarestWord = word; // find the word with the least number of
										// occurrences
										// means least number of pages to search
				}
			}

			pagesToSearch = index.pageNums(rarestWord.toUpperCase());
			// search only pages containing a valid word
		}

		// just pick first valid word, cuts back on the vast majority of pages
		// to search

		// search for first word
		for (Integer page : pagesToSearch) {
			String currentPage = fileContents.get(page - 1);
			if (currentPage.toLowerCase().contains(phrase.toLowerCase())) {
				pagesItAppearsOn.add(page);
			}
		}
		Collections.sort(pagesItAppearsOn); // sort them to display in order
		return pagesItAppearsOn; // return list of pages
		/*
		 * The phrase search method has a running time of O(n * m) where n is
		 * the number of pages to be examined and m is the size of a page (40
		 * lines in this case). This is much lower than if the entire document
		 * were to be searched which would give a running time of O(k * m) where
		 * k is every page in the document
		 */
	}


	@Override
	public List<String> allPages() {
		return fileContents;
	}

	@Override
	public String allPagesWith(String word, Index index) {
		StringBuilder sb = new StringBuilder();	
		for(Integer n : index.pageNums(word)){
			sb.append(singlePage(n));
		}
		return sb.toString();
	}

}
