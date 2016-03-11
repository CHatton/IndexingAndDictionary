package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDocument implements Document {
	List<String> fileContents = new ArrayList<>(); // entire file in nested list format
	Index index; // the document's index

	public FileDocument(String pathToFile, String pathToDictionary, String pathToIgnoreWords) {
		try {
			// give the path to the file, and it creates a document from the file given
			Dictionary d = new Dictionary(pathToDictionary);
			Set<String> ignoreWords = populateIgnoreWords(pathToIgnoreWords);
			fillContents(pathToFile); // fill document contents from file
			index = new MyIndex(d, ignoreWords, fileContents); // create index with dictionary, ignore words and fileContents
		} catch (IOException e) {
			System.out.println("There was an error creating the document.");
			System.out.println("Please check for the following files: ");
			System.out.println(pathToFile + ", " + pathToDictionary + ", " + pathToIgnoreWords);
		} // if there's an error with one of the files

	}

	private Set<String> populateIgnoreWords(String pathToIgnoreWords) throws IOException {
		Set<String> ignoreWords = new HashSet<>();
		BufferedReader reader = new BufferedReader(new FileReader(pathToIgnoreWords));
		String next;
		while ((next = reader.readLine()) != null) {
			ignoreWords.add(next.toUpperCase());
			// add each line of the file as a word to ignore and not add to the index
		}
		reader.close();
		return ignoreWords;
	}

	private void fillContents(String pathToFile) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		StringBuilder page = new StringBuilder(); // represents a full page
		String next;
		int pos = 0;

		while ((next = reader.readLine()) != null) {

			for (int i = 0; i < 40; i++) {
				if (next != null) { // don't want to add nulls to end of text
					page.append(next + "\n");
				}
				next = reader.readLine();
			}
			fileContents.add(page.toString());
			page = new StringBuilder();
		}
		reader.close();
	}

	@Override
	public int pageCount() {
		return index.getPages(); // total number of pages in the document
	}

	@Override
	public void printFullDocument() {
		for (int i = 0; i < fileContents.size(); i++) {
			System.out.println(fileContents.get(i)); // each string is a page
		}
	}
	
	@Override
	public void printSinglePage(int page) {
		System.out.println(fileContents.get(page - 1));
	}

	@Override
	public List<Integer> pageNums(String word) {
		List<Integer> pageNumbers = new ArrayList<>(index.pageNums(word.toUpperCase()));
		Collections.sort(pageNumbers);
		return pageNumbers;
	}

	@Override
	public boolean contains(String word) {
		return index.contains(word); // delegate to index
	}

	@Override
	public int wordCount() {
		return index.getWordCount();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Word Count: " + wordCount() + "\n");
		sb.append("Num Pages: " + pageCount() + "\n");
		return sb.toString();
	}



	@Override
	public void printIndex() {
		System.out.println(index);
	}

	@Override
	public List<String> getDefinitions(String word) {
		return index.getDefinitions(word);
	}

}
