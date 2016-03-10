package gmit;

import java.util.Set;

public interface Index {
	Set<Integer> pageNums(String word);
	// gives back a list of numbers indicating which pages the word appears on
	//List<String> definitions(String word);
	// gives back the definition of the word
	boolean contains(String word);
	// says whether or not the word is in the index
	
}
