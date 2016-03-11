package gmit;

import java.util.List;

public interface Document {
	int pageCount();// gives the number of pages in the document
	void printFullDocument();// prints the full document
	List<Integer> pageNums(String word);// gives back the list of page the word is on
	boolean contains(String word);// says whether or not the word exists in the document
	int wordCount();// gives back the total unique word count of the document
	void printSinglePage(int page);// prints a single page from the document
	void printIndex();// print out the index
	List<String> getDefinitions(String word); 
	// gives back the definitions for a given word if it's in the document's index
}
