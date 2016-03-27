package gmit;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class DocumentRunner {

	public static Scanner console = new Scanner(System.in);
	public static Document currentDoc;
	public static Dictionary currentDictionary;
	public static Index currentIndex;
	public static RestrictedWords currentStopwords;

	public static void main(String[] args) throws IOException {
		defaultFile(); // creates default file document

		int userChoice;

		help();

		do {
			System.out.println("Enter choice: (19 for help)");
			userChoice = console.nextInt();

			switch (userChoice) {
			case 1:
				defaultFile();
				break;
			case 2:
				defaultURL();
				break;
			case 3:
				updateDictionary();
				break;
			case 4:
				updateStopwords();
				break;
			case 5:
				updateDocument();
				break;
			case 6:
				updateEverything();
				break;
			case 7:
				printDocumentInfo();
				break;
			case 8:
				printFullDoc();
				break;
			case 9:
				printSinglePage();
				break;
			case 10:
				printPageRange();
				break;
			case 11:
				printIndex();
				break;
			case 12:
				printPagesOfGivenWord();
				break;
			case 13:
				printInvalidWords();
				break;
			case 14:
				printAllPagesWithWord();
				break;
			case 15:
				searchForWord();
				break;
			case 16:
				searchForExactMatch();
				break;
			case 17:
				searchForLooseMatch();
				break;
			case 18:
				calculateAverageCreationTime();
				break;
			case 19:
				help();
				break;
			case 20:
				detailedHelp();
				break;
			case -1:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid option (14 for help)");
				break;
			}

		} while (userChoice != -1);
	} // main

	public static void help() {
		// prints out the options available to the user
		System.out.println("===== Document Creation =====");
		System.out.println(" 1) Use premade file document");
		System.out.println(" 2) Use premade url document");
		System.out.println(" 3) Update Dictionary");
		System.out.println(" 4) Update Stopwords");
		System.out.println(" 5) Update Document");
		System.out.println(" 6) Create custom document (update everything)");
		System.out.println("===== Printing From Document =====");
		System.out.println(" 7) Print document information");
		System.out.println(" 8) Print full document");
		System.out.println(" 9) Print single page");
		System.out.println(" 10) Print range of pages");
		System.out.println("11) Print index of document");
		System.out.println("12) Print page numbers of given word");
		System.out.println("13) Print invalid words in document");
		System.out.println("14) Print all pages with given word");
		System.out.println("===== Search the Document =====");
		System.out.println("15) Search for a word");
		System.out.println("16) Search for a phrase (exact match)");
		System.out.println("17) Search for a phrase (loose match)");
		System.out.println("===== Demonstration =====\n");
		System.out.println("18) Calculate average document creation time (War and Peace)");
		System.out.println("===== Help =====");
		System.out.println("===== Document Creation =====\n");
		System.out.println("19) Help");
		System.out.println("20) Detailed Help");
		System.out.println("===== Exit =====\n");
		System.out.println("-1) Exit\n");
	}

	public static void detailedHelp() {

		System.out.println("===== Document Creation =====");
		System.out.println(" 1) Use premade document");
		System.out.println("This option creates a document using the following settings");
		System.out.println("Book text: WarAndPeace-LeoTolstoy.txt");
		System.out.println("Dictionary: dictionary.csv");
		System.out.println("Stopwords: stopwords.txt");
		System.out.println("Recommended for testing purposes.\n");

		
		
		System.out.println(" 2) Create custom document");
		System.out.println("This option allows you to create a custom document");
		System.out.println("Using your own settings. Make sure that all the files");
		System.out.println("That you choose are in the correct folder.");
		System.out.println("You will recieve a warning message if the document");
		System.out.println("Was not created successfully.\n");

		System.out.println("===== Printing From Document =====");
		System.out.println(" 3) Print document information");
		System.out.println("This option prints all of the documents information");
		System.out.println("including text file name, dictionary name, stopwords name,");
		System.out.println("page count and word count of the current active document.\n");

		System.out.println(" 4) Print full document");
		System.out.println("This option simply prints out the entire contents");
		System.out.println("of the document to the screen.\n");

		System.out.println(" 5) Print single page");
		System.out.println("Prints out a single page, if the page entered is below 1");
		System.out.println("or above the page count of the document, it will show you");
		System.out.println("and error message.\n");

		System.out.println(" 6) Print range of pages");
		System.out.println("This option will print out the pages between from (inclusive)");
		System.out.println("and to (not inclusive) of the current document.\n");

		System.out.println(" 7) Print index of document");
		System.out.println("This option simply prints the entire contents of the index");
		System.out.println("of the document to the screen. The index consists of words");
		System.out.println("in the dictionary AND not in the stopwords list, and the page");
		System.out.println("numbers each of the words appears on.\n");

		System.out.println(" 8) Print page numbers of given word");
		System.out.println("This option prints all of the page numbers the given word");
		System.out.println("appears on.\n");

		System.out.println(" 9) Print invalid words in document");
		System.out.println("This option simple lists all of the words that were in the stopwords");
		System.out.println("file.\n");

		System.out.println("10) Print all pages with given word");
		System.out.println("This option will print every page that contains the word");
		System.out.println("Entered by the user, if there are no words, it will recommend some.\n");

		System.out.println("===== Search the Document =====");
		System.out.println("11) Search for a word");
		System.out.println("Allows the user to search for a word in the document, it that word");
		System.out.println("appears in the document, it will print out all the information about that");
		System.out.println("word. If it is not in the document, it will suggest a list of words that");
		System.out.println("are in the document based on your search.\n");

		System.out.println("12) Search for phrase (exact match)");
		System.out.println("This option takes a phrase and looks through the document to see");
		System.out.println("if that phrase exists in the exact word order given.\n");

		System.out.println("13) Search for a phrase (loose match)");
		System.out.println("This option takes a phrase, and looks through the document to see");
		System.out.println("if every word in the phrase appears on a page, and gives back a sorted");
		System.out.println("list of pages it occurrs on.\n");

		System.out.println("===== Help =====");
		System.out.println("14) Help");
		System.out.println("Prints out a list of options available.\n");

		System.out.println("15) Detailed Help");
		System.out.println("Prints out more detailed information about each available option.\n");

		System.out.println("===== Demonstration =====");
		System.out.println("16) Overlapping words between 2 documents");
		System.out.println("Creates 2 documents, and gives back a set of the words that both");
		System.out.println("documents contain.\n");

		System.out.println("17) Calculate average document creation time (War and Peace)");
		System.out.println("Creates the default document 50 times and gets the average time.\n");

		System.out.println("===== Exit =====");
		System.out.println("-1) Exit");
		System.out.println("Quits the program.\n");

	}

	public static void calculateAverageCreationTime() {
		long time = averageDocumentCreationTime(20);
		System.out.println("The average creation time was " + time + "ms");
	}

	public static void searchForLooseMatch() {
		System.out.println("Enter search phrase: ");
		console.nextLine(); // flush buffer
		String phrase = console.nextLine();
		List<Integer> pages = currentDoc.loosePhraseSearch(phrase, currentIndex);
		if (pages.size() != 0) {
			System.out.println("The words in that phrase appear on page(s) " + pages);
		} else {
			System.out.println("Those words don't appear together anywhere in the document!");
		}
	}

	public static void searchForExactMatch() {
		System.out.println("Enter search phrase: ");
		console.nextLine(); // flush buffer
		String phrase = console.nextLine();
		List<Integer> pages = currentDoc.exactPhraseSearch(phrase, currentIndex);

		if (pages.size() != 0) {
			System.out.println("That phrase appears on page(s) " + pages);
		} else {
			System.out.println("That phrase isn't in the document!");
		}

	}

	public static void searchForWord() {
		System.out.print("Enter word to search for: ");
		String searchWord = console.next();

		if (currentIndex.contains(searchWord)) {
			System.out.println(searchWord + " appears on pages: " + currentIndex.pageNums(searchWord));
			System.out.println(currentIndex.getDefinitions(searchWord));
		} else {
			System.out.println("Sorry " + searchWord + " isn't in the index, did you mean one of these?: ");
			System.out.println(currentIndex.didYouMean(searchWord));
		}
	}

	public static void printAllPagesWithWord() {
		System.out.println("Print all pages with what word: ");
		String word = console.next();
		
		if( currentIndex.pageNums(word).size() != 0){
			System.out.println(word + " appears on page(s) " + currentIndex.pageNums(word));
			System.out.println(currentDoc.allPagesWith(word, currentIndex));
		}else{
			System.out.println(word +  " is not it the document, did you mean one of these?: ");
			System.out.println(currentIndex.didYouMean(word));
		}
		
		
	}

	public static void printInvalidWords() {
		System.out.println(
				"A word is invalid if it appears in the document, but is not in the dictionary or IS in the list of stopwords");
		iterateThroughCollection(currentIndex.getInvalidWords());
	}

	public static void printPagesOfGivenWord() {
		System.out.println("Enter word: ");
		String word = console.next();
		if (currentIndex.pageNums(word).size() != 0) {
			System.out.println(word + " appears on page(s): " + currentIndex.pageNums(word));
		} else { // it is 0, so recommend other words
			System.out.println(word + " does not appear in the index, did you mean one of these?");
			System.out.println(currentIndex.didYouMean(word));
		}
	}

	public static void printIndex() {
		System.out.println(currentIndex);
	}

	public static void printPageRange() {
		System.out.print("From Page: ");
		int from = console.nextInt();
		System.out.print("To Page: ");
		int to = console.nextInt();
		System.out.println(currentDoc.pageRange(from, to));
	}

	public static void printSinglePage() {
		System.out.print("Print Which Page: ");
		int page = console.nextInt();
		System.out.println(currentDoc.singlePage(page));
	}

	public static void printFullDoc() {
		System.out.println(currentDoc.fullDocument());
	}

	public static void printDocumentInfo() {
		System.out.println("Document Source: " + currentDoc.getSource());
		System.out.println("Word Count: " + currentIndex.getWordCount());
		System.out.println("Page Count: " + currentDoc.pageCount());
	}

	public static void defaultFile() {
		createFileDocument("WarAndPeace-LeoTolstoy.txt", "dictionary.csv", "stopwords.txt");
	}

	public static void defaultURL() {
		createUrlDocument("http://www.huffingtonpost.co.uk/", "dictionary.csv", "stopwords.txt");
	}

	// creates the document and prints out the time it took to create it
	public static void createFileDocument(String bookPath, String dictionaryPath, String stopWordsPath) {
		System.out.println("Constructing File Document...");
		long before = System.currentTimeMillis();
		currentDictionary = new Dictionary(dictionaryPath);
		long after = System.currentTimeMillis();
		System.out.println("Constructed dictionary in " + (after - before) + "ms");
		before = System.currentTimeMillis();
		currentStopwords = new RestrictedWords(stopWordsPath);
		after = System.currentTimeMillis();
		System.out.println("Constructed stopwords in " + (after - before) + "ms");
		FileDocumentProvider fileProv = new FileDocumentProvider();
		before = System.currentTimeMillis();
		currentDoc = fileProv.get(bookPath);
		after = System.currentTimeMillis();
		System.out.println("Constructed document in " + (after - before) + "ms");
		before = System.currentTimeMillis();
		currentIndex = new MyIndex(currentDictionary, currentStopwords, currentDoc);
		after = System.currentTimeMillis();
		System.out.println("Constructed index in " + (after - before) + "ms");
	}

	public static void createUrlDocument(String url, String dictionary, String stopwords) {
		System.out.println("Constructing URL Document...");
		long before = System.currentTimeMillis();
		currentDictionary = new Dictionary(dictionary);
		long after = System.currentTimeMillis();
		System.out.println("Constructed dictionary in " + (after - before) + "ms");
		before = System.currentTimeMillis();
		currentStopwords = new RestrictedWords(stopwords);
		after = System.currentTimeMillis();
		System.out.println("Constructed stopwords in " + (after - before) + "ms");
		URLDocumentProvider urlProv = new URLDocumentProvider();
		before = System.currentTimeMillis();
		currentDoc = urlProv.get(url);
		after = System.currentTimeMillis();
		System.out.println("Constructed document in " + (after - before) + "ms");
		before = System.currentTimeMillis();
		currentIndex = new MyIndex(currentDictionary, currentStopwords, currentDoc);
		after = System.currentTimeMillis();
		System.out.println("Constructed index in " + (after - before) + "ms");

	}

	public static void updateDictionary() {
		System.out.println("Enter new dictionary path. ");
		String newPath = console.next();
		currentDictionary = new Dictionary(newPath);
	}

	public static void updateStopwords() {
		System.out.println("Enter new stopwords path. ");
		String newPath = console.next();
		currentStopwords = new RestrictedWords(newPath);
	}

	public static void updateDocument() {
		System.out.println("Enter new file path or URL. (make sure URL starts with 'http')");
		String newPath = console.next();
		boolean isFile;
		if (newPath.length() > 4 && newPath.substring(0, 4).equalsIgnoreCase("http")) {
			isFile = false; // is url
		} else {
			isFile = true;
		}
		currentDoc = (isFile ? new FileDocumentProvider().get(newPath) : new URLDocumentProvider().get(newPath));

	}

	public static void updateEverything() {
		updateDictionary();
		updateStopwords();
		updateDocument();
	}

	public static long averageDocumentCreationTime(int numTimes) {
		long totalTime = 0;
		System.out.println("Calculating using War and Peace...");
		for (int i = 0; i < numTimes; i++) {
			long before = System.currentTimeMillis();
			currentDictionary = new Dictionary("dictionary.csv");
			currentDoc = new FileDocumentProvider().get("WarAndPeace-LeoTolstoy.txt");
			currentStopwords = new RestrictedWords("stopwords.txt");
			currentIndex = new MyIndex(currentDictionary, currentStopwords, currentDoc);
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
