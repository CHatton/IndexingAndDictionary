package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileDocument implements Document {
	//private File file;
	List<String> fileContents = new ArrayList<>(); // entire file in nested list format
	Index index; // the document's index

	public FileDocument(String pathToFile, String pathToDictionary, String pathToIgnoreWords) {
		// give the path to the file, and it creates a document from the file given
		this.index = new MyIndex(pathToFile, pathToDictionary, pathToIgnoreWords);
		fillContents(pathToFile); // fill up nested lists with contents from file
	}

	private void fillContents(String pathToFile) {
		try {
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
		} catch (IOException e) {
			System.out.println("There was an error reading in the file from " + pathToFile);
		}
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
	public List<Integer> pageNums(String word) {
		List<Integer> pageNumbers = new ArrayList<>(index.pageNums(word));
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
	public void printSinglePage(int page) {
		System.out.println(fileContents.get(page - 1));
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
