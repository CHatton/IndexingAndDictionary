package gmit;

import java.util.Set;

public interface Index {
	Set<Integer> pageNums(String word);	// gives back a set of numbers indicating which pages the word appears on
	boolean contains(String word); // says whether or not the word is in the index
	int getPages(); // number of pages in the index
	int getWordCount(); // number of words in the document
}
