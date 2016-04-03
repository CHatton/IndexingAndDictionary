package gmit;

import java.util.List;
import java.util.Set;

public interface Index {
	List<Integer> pageNums(String word); // gives back a set of numbers indicating which pages the word appears on
	/*
	 * The reason for keeping it as a set privately is to avoid duplicates,
	 * if a word appears on the same page twice, the page number
	 * should just appear once, it's sorted and returned as a List
	 */
	
	boolean contains(String word); // says whether or not the word is in the index

	int getWordCount(); // number of words in the document

	List<String> getDefinitions(String word); // give all the definitions for a given word
	
	Set<String> getInvalidWords(); 
	// give back the words that were IN stop words or were NOT in dictionary
	
	List<String> didYouMean(String word);
	// give back a list of recommended words based on a given word

}
