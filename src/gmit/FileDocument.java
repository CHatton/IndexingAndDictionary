package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDocument implements Document {
	private List<String> fileContents = new ArrayList<>();
	// entire file in nested list format 1 String per page, a page consists of 40 lines
	private Index index; // the document's index

	// these 3 variables mainly for the toString method
	private String filePath;
	private String dictionaryPath;
	private String ignoreWordsPath;

	public FileDocument(String pathToFile, String pathToDictionary, String pathToIgnoreWords) {

		filePath = pathToFile;
		dictionaryPath = pathToDictionary;
		ignoreWordsPath = pathToIgnoreWords;

		Dictionary d; // dictionary that will be used to create index
		try {
			d = new Dictionary(pathToDictionary); // creates dictionary from
													// given path
		} catch (IOException e) {
			System.err.println("Warning: The document has a blank dictionary - check file '" + pathToDictionary + "'");
			d = new Dictionary(); // use an empty one if there is a problem
									// loading it from the file
		}
		Set<String> ignoreWords;
		try {
			ignoreWords = populateIgnoreWords(pathToIgnoreWords);
			// creates list of words to ignore
		} catch (IOException e) {
			System.err.println("Warning: The stoplist for the index is blank - check file '" + pathToIgnoreWords + "'");
			ignoreWords = new HashSet<>(); // default to empty if there's a
											// problem reading from the file
		}
		try {
			fillContents(pathToFile); // fill document contents from file
		} catch (IOException e) {
			System.err.println("Warning: There was an error reading from the e-book - check file '" + pathToFile + "'");
			fileContents = new ArrayList<>();
		} // if there's an error with one or more of the files
		index = new MyIndex(d, ignoreWords, fileContents); // create index with
															// dictionary,
															// ignore words and
															// fileContents
	} // constructor

	/*
	 * This constructor deals with three files, with lengths n,m and k say i is
	 * the time taken to create the index this constructor has a time complexity
	 * of O(n + m + k + i)
	 */

	private Set<String> populateIgnoreWords(String pathToIgnoreWords) throws IOException {
		Set<String> ignoreWords = new HashSet<>(); // start off empty
		BufferedReader reader = new BufferedReader(new FileReader(pathToIgnoreWords));
		String next;
		while ((next = reader.readLine()) != null) {
			ignoreWords.add(next.toUpperCase()); // add upper case of word
			// add each line of the file as a word to ignore and not add to the
			// index
		}
		reader.close();
		return ignoreWords; // give back set
	}
	/*
	 * this method has a time complexity of O(n) it needs to linearly look at
	 * every line in the file
	 */

	private void fillContents(String pathToFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		StringBuilder page = new StringBuilder(); // represents a full page
		String next;

		while ((next = reader.readLine()) != null) {

			for (int i = 0; i < 40; i++) {
				if (next != null) { // don't want to add nulls to end of text
					page.append(next + "\n"); // add 40 non null lines of text
												// to the single string
				}
				next = reader.readLine();
			}
			fileContents.add(page.toString()); // add this page to the arraylist
			page = new StringBuilder(); // empty the string
		}
		reader.close();
	}
	/*
	 * again another linear operation. It needs to go through and look at every
	 * line in the file and add it to arraylist O(n) time complexity
	 */

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
	public List<Integer> pageNums(String word) {
		List<Integer> pageNumbers = new ArrayList<>(index.pageNums(word.toUpperCase()));
		Collections.sort(pageNumbers);
		return pageNumbers;

		/*
		 * sorting the list of pageNumbers is an nLog(n) operation accessing the
		 * list of numbers through the pageNums method is a constant time
		 * operation as it is access an element from a map overall the method
		 * has a O(1) + nLog(n) time complexity which can be reduced to just
		 * nLog(n)
		 */
	}

	@Override
	public boolean contains(String word) {
		return index.contains(word); // delegate to index
		// constant time operation
	}

	@Override
	public int wordCount() {
		return index.getWordCount();
		// constant time
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("File Name: " + filePath + "\n");
		sb.append("Dictionary File: " + dictionaryPath + "\n");
		sb.append("Stopwords File: " + ignoreWordsPath + "\n");
		sb.append("Word Count: " + wordCount() + "\n");
		sb.append("Num Pages: " + pageCount() + "\n");
		return sb.toString();
		// O(n) where n is the number of characters in the string
		// it would be O(n^2) if string concatenation was used instead of a
		// string builder
	}

	@Override
	public String getIndex() {
		return index.toString(); // prints the toString from index
	}

	@Override
	public List<String> getDefinitions(String word) {
		return index.getDefinitions(word);
		// constant time O(1)
	}

	@Override
	public String allPagesWith(String word) {
		List<Integer> pageNumbers = pageNums(word);
		StringBuilder sb = new StringBuilder();
		// get list of pages word is on
		for (Integer page : pageNumbers) {
			sb.append(singlePage(page)); // print out all of them
		}
		return sb.toString();
		/*
		 * accessing a single page and getting the List of page numbers are both
		 * constant time operations so this method has a time complexity of O(n)
		 * where n is the number of pages being printed
		 */
	}

	@Override
	public List<Integer> pageNumbersFor(String word) {
		Set<Integer> pageNumsSet = index.pageNums(word.toUpperCase()); // get
																		// the
																		// set
																		// of
																		// pages
		List<Integer> pageNumsList = new ArrayList<>(pageNumsSet); // turn it
																	// into a
																	// list
		Collections.sort(pageNumsList); // sort it
		return pageNumsList; // return it
		/*
		 * getting the set of page numbers is a constant time operation sorting
		 * it in the form of a list is an nlog(n) operation. making the overall
		 * complexity nlog(n)
		 */

	}

	@Override
	public List<String> getInvalidWords() {
		List<String> invalidWords = new ArrayList<>(index.getInvalidWords());
		Collections.sort(invalidWords);
		return invalidWords;
		/*
		 * same as previous, O(1) + nlog(n) = nlog(n) time complexity
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
	public List<String> didYouMean(String word) {
		List<String> wordsToReccommend = index.didYouMean(word);
		return wordsToReccommend;
	}

	@Override
	public List<Integer> loosePhraseSearch(String phrase) {
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
		Set<Integer> pagesToSearch;
		if (validWords.size() == 0) {
			// need to search every page, worst case
			List<Integer> allPages = new ArrayList<>();
			for (int i = 0; i < fileContents.size(); i++) {
				allPages.add(i + 1); // add all pages
			}
			pagesToSearch = new HashSet<>(allPages);
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
	public List<Integer> exactPhraseSearch(String phrase) {

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

		Set<Integer> pagesToSearch; // this will be all the pages we need to
									// search,
									// either all of them, or the lowest possible number of pages

		if (validWords.size() == 0) { // no valid words
			List<Integer> allPages = new ArrayList<>();
			for (int i = 0; i < fileContents.size(); i++) {
				allPages.add(i + 1); // all pages to be searched
			}
			pagesToSearch = new HashSet<>(allPages); // search all pages
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
	public Set<String> overlappingWords(Document other) {
		Set<String> sharedWords = new HashSet<>();
		Set<String> inThisDoc = index.getKeys(); // all the words in this
													// document
		for (String s : inThisDoc) {
			if (other.contains(s)) { // if the other document contains the word
				sharedWords.add(s); // add it to shared
			}
			// the words we lose from the other document can't be in the shared
			// set if the original doesn't have them
		}
		return sharedWords; // set of all common words between documents
		/*
		 * This method has a running time complexity of O(n) where n is the
		 * number elements in the index of the other document. It is a constant
		 * time operation to get the index of this document, and to get the
		 * index of the other document.
		 */
	}

}
