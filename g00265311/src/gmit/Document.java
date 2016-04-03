package gmit;

import java.util.List;

public interface Document {
	int pageCount();// gives the number of pages in the document

	String pageRange(int from, int to);
	// give back a string that is the concatenation of all the pages within the range

	String fullDocument();// prints the full document

	List<String> allPages(); // give back a list of strings, each one representing a "page"

	String singlePage(int page);// prints a single page from the document

	List<Integer> exactPhraseSearch(String phrase, Index index);
	// returns the pages at which that phrase is found, empty list if it isn't there

	List<Integer> loosePhraseSearch(String phrase, Index index);
	// returns the pages at which the given words appear

	String allGivenPages(List<Integer> pages);
	// give back the text of all the pages when given a list of integers
}
