package gmit;

import org.junit.Test;

public class IndexTest {

	@Test
	public void generateIndexTest() {
		long before = System.currentTimeMillis();
		Document deBelloGallico = new FileDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");

		//deBelloGallico.printIndex();
		//System.out.println(deBelloGallico.getDefinitions("Adopt"));
		System.out.println(deBelloGallico.pageNums("adopt"));
		
	}

}
