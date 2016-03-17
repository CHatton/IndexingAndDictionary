package gmit;

import java.util.Scanner;

public class DocumentRunner {
	public static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {

		Document d = createDocument(); // start off with default document
		char userChoice;

		help();
		do {
			System.out.print("Enter Choice: ");
			userChoice = Character.toUpperCase(console.next().charAt(0));
			// read in and force to upper case the next character entered
			switch (userChoice) {
			case 'P':
				d = createDocument();
				// uses default document
				break;
			case 'C':
				d = createCustomDocument();
				// lets user create custom document
				break;
			case 'V':
				System.out.println("Words not in the dictionary OR in the stop list: ");
				System.out.println(d.getInvalidWords());
				break;
			case 'X':
				System.out.println("Goodbye!");
				break;
			case 'H':
				help(); // print out available options for user
				break;
			case 'I':
				d.printIndex(); // prints out the index for the document
				break;
			case 'D':
				System.out.println(d);//.toString()
				break;
			case 'N':
				System.out.print("Enter word: ");
				String word = console.next();
				System.out.println(word + " appears on page(s) " + d.pageNumbersFor(word));
				break;
			case 'O':
				System.out.println("Print which page?");
				System.out.println("1 - " + d.pageCount());
				int pageNum = console.nextInt();
				d.printSinglePage(pageNum);
				break;
			case 'R':
				System.out.println("From Page: ");
				int from = console.nextInt();
				System.out.println("To Page: ");
				int to = console.nextInt();
				d.printPageRange(from, to);
				// print pages between from (inclusive) and to (not inclusive)
				break;
			case 'F':
				d.printFullDocument();
				break;
			case 'A':
				System.out.print("The average document creation time is: (calculating...) ");
				System.out.println(averageDocumentCreationTime() + "ms");
				break;
			default:
				System.out.println();
				System.out.println("Invalid Option.");
				help(); // show options
				break;
			}

		} while (userChoice != 'X');

	}

	public static Document createDocument() {
		long before = System.currentTimeMillis();
		Document d = new FileDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");
		System.out.println();
		return d;
	}

	// creates the document and prints out the time it took to create it
	public static Document createDocument(String bookPath, String dictionaryPath, String stopWordsPath) {
		long before = System.currentTimeMillis();
		Document d = new FileDocument(bookPath, dictionaryPath, stopWordsPath);
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");
		System.out.println();
		return d;
	}

	public static Document createCustomDocument() {
		System.out.println("Enter full path to book.");
		String bookPath = console.next();
		System.out.println("Enter full path to dictionary.");
		String dictionaryPath = console.next();
		System.out.println("Enter full path to stopwords");
		String stopWordsPath = console.next();
		Document d = createDocument(bookPath, dictionaryPath, stopWordsPath);
		return d;
	}

	public static void help() {
		// prints out the options available to the user

		System.out.println("(C)reate new document");
		System.out.println("Use (P)remade document");
		System.out.println("Print (I)ndex of document");
		System.out.println("Print (D)ocument information");
		System.out.println("Print page (N)umbers of given word");
		System.out.println("Print (O)ne page");
		System.out.println("Print (R)ange of pages");
		System.out.println("Print (F)ull document");
		System.out.println("List in(V)alid words");
		System.out.println("Get (A)verage document creation time");

		System.out.println("(H)elp");
		System.out.println("E(X)it");

	}

	public static long averageDocumentCreationTime() {

		long totalTime = 0;

		for (int i = 0; i < 100; i++) {
			long before = System.currentTimeMillis();
			Document d = new FileDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
			long after = System.currentTimeMillis();
			totalTime += (after - before);
		}

		return totalTime / 100;
	}

}
