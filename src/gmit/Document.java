package gmit;

import java.util.List;
import java.util.Set;

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

	List<Integer> exactPhraseSearch(String phrase); 
	// returns the pages at which that phrase is found, empty list if it isn't there

	List<Integer> loosePhraseSearch(String phrase); 
	// returns the pages at which the given words appear

	Set<String> overlappingWords(Document other);
	// takes another document, and gives a list of words that are in both the indexes
}

/*
 * Note about encapsulation with the Document interface
 * 
 * The person using the Document interface has no idea how the contents of
 * the file are being stored, or that it has an index at all.
 * 
 * All the user needs to know is how to use the public methods and what
 * the return values will be.
 * 
 * For example, the fileContents could have been saved as a single string,
 * this would make no difference to the user as long as the methods
 * still had the same. correct return value
 * 
 * the document could have had all of the properties of the index combined
 * into one thing, the reason I created a separate interface was that
 * there should be able to be a different type of document, that isn't created
 * by reading in files, but should still be able to use the same type of index.
 * 
 * The user also doesn't need to worry about whether or not creating a FileDocument
 * will throw an exception. All the exceptions are handled inside the FileDocument
 * class. There will be warnings about the document not having been created correctly,
 * but no exceptions will be thrown from the main method where someone is creating
 * and instance of the FileDocument
 */
