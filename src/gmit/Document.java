package gmit;

import java.util.List;

public interface Document {
	int pageCount();// gives the number of pages in the document

	String pageRange(int from, int to);

	String fullDocument();// prints the full document

	List<Integer> pageNums(String word);// gives back the list of page the word is on

	boolean contains(String word);// says whether or not the word exists in the document

	int wordCount();// gives back the total unique word count of the document

	String singlePage(int page);// prints a single page from the document

	String getIndex();// print out the index

	List<String> getDefinitions(String word); // gives back the definitions for a given word if it's in the document's index

	String allPagesWith(String word); // prints all pages with that word

	List<Integer> pageNumbersFor(String word); // give back all the page numbers that word appears on

	List<String> getInvalidWords(); // gives back list of words that were either not on the dictionary, or on the stop words list

	List<String> didYouMean(String word); // list of words using simple recommendation system
}
