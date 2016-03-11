package gmit;

import static org.junit.Assert.*;

import org.junit.Test;

public class DocumentTest {

	@Test
	public void docTest() {
		Document testDocument = new FileDocument("indextest.dat", "dictionary.csv", "stopwords.txt");
		assertFalse(testDocument.contains("another")); // in the document, but in the stopwords
		assertFalse(testDocument.contains("civilisation")); // in the document but not in the dictionary
		assertTrue(testDocument.contains("PROVINCE")); // word is in the document, the dictionary and NOT the stop list
		assertTrue(testDocument.contains("Seine")); // the word is in the test document, and the dictionary, not in the stop list
		assertFalse(testDocument.contains("hello")); // word isn't in the document, IS in the dictionary and isn't in the stop list
	}

}
