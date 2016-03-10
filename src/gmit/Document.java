package gmit;

import java.util.List;

public interface Document {
	int pageCount();
	// gives the number of pages in the document
	String fullDocument();
	// gives the full document as a string
	List<Integer> pageNums(String word);
	// gives back the list of page the word is on
	boolean contains(String word);
	// says whether or not the word exists in the document
	int wordCount();
	// gives back the total word count of the document
	void printSinglePage(int page);
	// prints a single page from the document
}
