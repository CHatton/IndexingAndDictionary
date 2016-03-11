package gmit;

public class DocumentRunner {
	public static void main(String[] args) {

		long before = System.currentTimeMillis();
		Document d = new FileDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");

		//d.printAllPagesWith("continually");
		//d.printSinglePage(5);
		//d.printFullDocument();
		System.out.println(d);
		//d.printIndex();
	}
}
