package gmit;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndexTest {

	@Test
	public void generateIndexTest() {
		Index index = new MyIndex("indextest.dat", "dictionary.csv", "stopwords.txt");
		assertFalse(index.contains("all")); // on the stop list
		assertTrue(index.contains("refinement")); // should be in index
		assertFalse(index.contains("Belgae")); // in text but not in dictionary
		assertTrue(index.contains("inHabIt")); // case insensitive
		assertEquals(8,index.getWordCount()); // number of unique words in index
	}

}
