package gmit;

public class DocumentRunner {
	public static void main(String[] args) {

		long before = System.currentTimeMillis();
		Document deBelloGallico = new FileDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");

	
	}
}
