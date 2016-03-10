package gmit;

public class DocumentRunner {
	public static void main(String[] args) {
/*
			
			Dictionary d = new Dictionary("dictionary.csv");
			
			


*/
	//	Document test = new FileDocument(pathToFile, "dictionary.csv", ignoreWords)
		long before = System.currentTimeMillis();
		Index myIndex = new MyIndex("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println(after - before);
	}
}
