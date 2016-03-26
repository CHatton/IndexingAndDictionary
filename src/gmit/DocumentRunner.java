package gmit;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class DocumentRunner {

	public static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		
		
		Dictionary dictionary = new Dictionary("dictionary.csv"); // create the dictionary
		
		
		/*
		Document doc = createUrlDocument("http://www.nytimes.com/", "dictionary.csv", "stopwords.txt");
		//Document doc = new FileDocument("WarAndPeace-LeoTolstoy.txt", "dictionary.csv", "stopwords.txt");
		System.out.println("Using premade document - War and Peace");
		// Document doc = createDocument(); // start off with default war and
		// peace document
		int userChoice;
		System.out.println(help());
		do {
			System.out.print("Enter Choice (14 for help): ");
			userChoice = console.nextInt();
			// read in and force to upper case the next character entered
			switch (userChoice) {
			case 1:
				doc = createDocument();
				// uses default document
				break;
			case 2:
				doc = createCustomDocument();
				// lets user create custom document
				break;
			case 3:
				System.out.println(doc);// .toString()
				break;
			case 4:
				System.out.println(doc.fullDocument());
				break;
			case 5:
				if (doc.pageCount() != 0) {// check for invalid document
					System.out.println("Print which page?");
					System.out.println("1 - " + doc.pageCount());
					int pageNum = console.nextInt();
					System.out.println(doc.singlePage(pageNum));
				} else {
					System.out.println("Invalid document, please create a valid one, or use premade!");
				}
				break;
			case 6:
				if (doc.pageCount() != 0) { // check for invalid document
					System.out.println("From Page: ");
					int from = console.nextInt();
					System.out.println("To Page: ");
					int to = console.nextInt();
					System.out.println(doc.pageRange(from, to));
					// print pages between from (inclusive) and to (not
					// inclusive)
				} else {
					System.out.println("Invalid document, please create a valid one, or use premade!");
				}
				break;
			case 7:
				System.out.println(doc.getIndex()); // prints out the index for
													// the document
				break;
			case 8:
				System.out.print("Enter word: ");
				String word = console.next();
				if (doc.pageNumbersFor(word).size() != 0) {
					System.out.println(word + " appears on " + doc.pageNumbersFor(word).size() + " page(s) "
							+ doc.pageNumbersFor(word));
				} else {
					System.out.println(word + " isn't in the document!");
					System.out.println("Based on your search, these are in the document: " + doc.didYouMean(word));
				}
				break;
			case 9:
				System.out.println("Words not in the dictionary OR in the stop list: ");
				iterateThroughCollection(doc.getInvalidWords());
				break;
			case 10:
				System.out.print("Enter word: ");
				String givenWord = console.next();
				if (doc.allPagesWith(givenWord).length() != 0) {
					System.out.println(doc.allPagesWith(givenWord));
				} else {
					System.out.println(givenWord + " isn't in the document!");
					System.out.println("Based on your search, these are in the document: " + doc.didYouMean(givenWord));
				}
				break;
			case 11:
				System.out.print("Enter word to search for: ");
				String searchWord = console.next();
				if (doc.contains(searchWord)) {
					System.out.println(searchWord + " appears on pages " + doc.pageNumbersFor(searchWord));
					System.out.println("Definitions: ");
					System.out.println(doc.getDefinitions(searchWord));
				} else {
					System.out.println("Sorry that word isn't in the book!");
					System.out.println("It's either not in the dictionary or the text itself, or is on the stoplist.");
					System.out.println("Based on your search: " + doc.didYouMean(searchWord));
					// word recommendation feature
				}
				break;
			case 12: // exact search
				System.out.println("Enter search phrase: ");
				console.nextLine();
				String exactSearchPhrase = console.nextLine();
				System.out.println("\"" + exactSearchPhrase + "\"" + " appears on page(s): "
						+ doc.exactPhraseSearch(exactSearchPhrase));
				break;
			case 13: // loose search
				System.out.println("Enter search phrase: ");
				console.nextLine();
				String looseSearchPhrase = console.nextLine();
				System.out.println("Words in \"" + looseSearchPhrase + "\"" + " appear on page(s): "
						+ doc.loosePhraseSearch(looseSearchPhrase));
				break;
			case 14:
				System.out.println(help()); // print out available options for
											// user
				break;
			case 15:
				System.out.println(detailedHelp());
				break;

			case 16:
				Document doc1 = createDocument(); // war and peace
				Document doc2 = createDocument("DeBelloGallico.txt", "dictionary.csv", "stopwords.txt");
				System.out.println("Created War and Peace & DeBelloGallico");
				Set<String> overLapping = doc1.overlappingWords(doc2);
				System.out.println("Both documents share the following " + overLapping.size()
						+ " words - Press any key to continue ");
				console.nextLine();
				console.nextLine();
				iterateThroughCollection(overLapping);
				// print one per line instead of using default toString method
				break;
			case 17:
				System.out.print("The average document creation time is: (calculating...) ");
				System.out.println(averageDocumentCreationTime(50) + "ms");
				break;
			case 18:
				doc = urlCreation();

			case -1:
				System.out.println("Goodbye!"); // program will end
				break;
			default:
				System.out.println("\nInvalid Option.");
				System.out.println(help()); // show options
				break;
			}
		} while (userChoice != -1);
*/
	} // main

	public static String help() {
		// prints out the options available to the user
		StringBuilder sb = new StringBuilder();
		sb.append("===== Document Creation =====\n");
		sb.append(" 1) Use premade document\n");
		sb.append(" 2) Create custom document\n");
		sb.append("===== Printing From Document =====\n");
		sb.append(" 3) Print document information\n");
		sb.append(" 4) Print full document\n");
		sb.append(" 5) Print single page\n");
		sb.append(" 6) Print range of pages\n");
		sb.append(" 7) Print index of document\n");
		sb.append(" 8) Print page numbers of given word\n");
		sb.append(" 9) Print invalid words in document\n");
		sb.append("10) Print all pages with given word\n");
		sb.append("===== Search the Document =====\n");
		sb.append("11) Search for a word\n");
		sb.append("12) Search for a phrase (exact match)\n");
		sb.append("13) Search for a phrase (loose match)\n");
		sb.append("===== Help =====\n");
		sb.append("14) Help\n");
		sb.append("15) Detailed Help\n");
		sb.append("===== Demonstration =====\n");
		sb.append("16) Overlapping words between 2 documents\n");
		sb.append("17) Calculate average document creation time (War and Peace)\n");
		sb.append("===== URL Document =====\n");
		sb.append("18) Create a URL Document\n");
		sb.append("===== Exit =====\n");
		sb.append("-1) Exit\n");
		return sb.toString();
	}

	public static String detailedHelp() {
		StringBuilder sb = new StringBuilder();
		sb.append("===== Document Creation =====\n");
		sb.append(" 1) Use premade document\n");
		sb.append("This option creates a document using the following settings\n");
		sb.append("Book text: WarAndPeace-LeoTolstoy.txt\n");
		sb.append("Dictionary: dictionary.csv\n");
		sb.append("Stopwords: stopwords.txt\n");
		sb.append("Recommended for testing purposes.\n\n");

		sb.append(" 2) Create custom document\n");
		sb.append("This option allows you to create a custom document\n");
		sb.append("Using your own settings. Make sure that all the files\n");
		sb.append("That you choose are in the correct folder.\n");
		sb.append("You will recieve a warning message if the document\n");
		sb.append("Was not created successfully.\n\n");

		sb.append("===== Printing From Document =====\n");
		sb.append(" 3) Print document information\n");
		sb.append("This option prints all of the documents information\n");
		sb.append("including text file name, dictionary name, stopwords name,\n");
		sb.append("page count and word count of the current active document.\n\n");

		sb.append(" 4) Print full document\n");
		sb.append("This option simply prints out the entire contents\n");
		sb.append("of the document to the screen.\n\n");

		sb.append(" 5) Print single page\n");
		sb.append("Prints out a single page, if the page entered is below 1\n");
		sb.append("or above the page count of the document, it will show you\n");
		sb.append("and error message.\n\n");

		sb.append(" 6) Print range of pages\n");
		sb.append("This option will print out the pages between from (inclusive)\n");
		sb.append("and to (not inclusive) of the current document.\n\n");

		sb.append(" 7) Print index of document\n");
		sb.append("This option simply prints the entire contents of the index\n");
		sb.append("of the document to the screen. The index consists of words\n");
		sb.append("in the dictionary AND not in the stopwords list, and the page\n");
		sb.append("numbers each of the words appears on.\n\n");

		sb.append(" 8) Print page numbers of given word\n");
		sb.append("This option prints all of the page numbers the given word\n");
		sb.append("appears on.\n\n");

		sb.append(" 9) Print invalid words in document\n");
		sb.append("This option simple lists all of the words that were in the stopwords\n");
		sb.append("file.\n\n");

		sb.append("10) Print all pages with given word\n");
		sb.append("This option will print every page that contains the word\n");
		sb.append("Entered by the user, if there are no words, it will recommend some.\n\n");

		sb.append("===== Search the Document =====\n");
		sb.append("11) Search for a word\n");
		sb.append("Allows the user to search for a word in the document, it that word\n");
		sb.append("appears in the document, it will print out all the information about that\n");
		sb.append("word. If it is not in the document, it will suggest a list of words that\n");
		sb.append("are in the document based on your search.\n\n");

		sb.append("12) Search for phrase (exact match)\n");
		sb.append("This option takes a phrase and looks through the document to see\n");
		sb.append("if that phrase exists in the exact word order given.\n\n");

		sb.append("13) Search for a phrase (loose match)\n");
		sb.append("This option takes a phrase, and looks through the document to see\n");
		sb.append("if every word in the phrase appears on a page, and gives back a sorted\n");
		sb.append("list of pages it occurrs on.\n\n");

		sb.append("===== Help =====\n");
		sb.append("14) Help\n");
		sb.append("Prints out a list of options available.\n\n");

		sb.append("15) Detailed Help\n");
		sb.append("Prints out more detailed information about each available option.\n\n");

		sb.append("===== Demonstration =====\n");
		sb.append("16) Overlapping words between 2 documents\n");
		sb.append("Creates 2 documents, and gives back a set of the words that both\n");
		sb.append("documents contain.\n\n");

		sb.append("17) Calculate average document creation time (War and Peace)\n");
		sb.append("Creates the default document 50 times and gets the average time.\n\n");

		sb.append("===== Exit =====\n");
		sb.append("-1) Exit\n");
		sb.append("Quits the program.\n\n");

		return sb.toString();

	}

	public static Document urlCreation() {
		System.out.println("Pick a defauly url or pick your own.");
		System.out.println("Don't forget to include the protocol eg 'http://'");
		int choice;
		String customUrl;
		String customDictionary;
		String customStopwords;

		System.out.println("Enter URL (-1 to quit)");
		System.out.println("1) Enter custom URL: ");
		System.out.println("2) use premade: http://www.huffingtonpost.co.uk/");
		System.out.println("3) use premade: http://news.yahoo.com/ ");
		System.out.println("4) use premade: http://www.nytimes.com/");

		choice = console.nextInt();
		switch (choice) {
		case 1:
			customUrl = console.next();
			System.out.print("Enter dictionary (recommended 'dictionary.csv'): ");
			customDictionary = console.next();
			System.out.print("Enter stop words (recommended 'stopwords.txt'): ");
			customStopwords = console.next();
			createUrlDocument(customUrl, customDictionary, customStopwords);
			break;
		case 2:
			System.out.println("Creating document using http://www.huffingtonpost.co.uk/)");
			return createUrlDocument("http://www.huffingtonpost.co.uk/", "dictionary.csv", "stopwords.txt");
		case 3:
			System.out.println("Creating document using http://news.yahoo.com/");
			return createUrlDocument("http://news.yahoo.com/", "dictionary.csv", "stopwords.txt");
		case 4:
			System.out.println("Creating document using http://www.nytimes.com/");
			return createUrlDocument("http://www.nytimes.com/", "dictionary.csv", "stopwords.txt");
		}
		return null;
	}

	public static Document createDocument() {
		long before = System.currentTimeMillis();
		Document doc = new FileDocument("WarAndPeace-LeoTolstoy.txt", "dictionary.csv", "stopwords.txt");
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");
		System.out.println();
		return doc;
	}

	public static Document createUrlDocument(String url, String dictionary, String stopwords) {
		long before = System.currentTimeMillis();
		Document doc = new URLdocument(url, dictionary, stopwords);

		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");
		System.out.println();
		return doc;
	}

	// creates the document and prints out the time it took to create it
	public static Document createDocument(String bookPath, String dictionaryPath, String stopWordsPath) {
		long before = System.currentTimeMillis();
		Document doc = new FileDocument(bookPath, dictionaryPath, stopWordsPath);
		long after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms to create the document");
		System.out.println();
		return doc;
	}

	public static Document createCustomDocument() {
		System.out.println("Enter full path to book.");
		String bookPath = console.next();
		System.out.println("Enter full path to dictionary.");
		String dictionaryPath = console.next();
		System.out.println("Enter full path to stopwords");
		String stopWordsPath = console.next();
		Document doc = createDocument(bookPath, dictionaryPath, stopWordsPath);
		return doc;
	}

	public static long averageDocumentCreationTime(int numTimes) {
		long totalTime = 0;
		for (int i = 0; i < numTimes; i++) {
			long before = System.currentTimeMillis();
			new FileDocument("WarAndPeace-LeoTolstoy.txt", "dictionary.csv", "stopwords.txt");
			long after = System.currentTimeMillis();
			totalTime += (after - before);
		}
		return totalTime / numTimes;
		// creates a document numTimes times and gets the average creation time
	}

	public static void iterateThroughCollection(Collection<String> col) {
		for (String s : col) {
			System.out.println(s);
		}
	}

}
