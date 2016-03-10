package gmit;

import java.io.IOException;
import java.util.List;

public class FileDocument implements Document {
	//private File file;
	List<List<String>> fileContents; // entire file in nested list format
	int pageCount; // total number of pages in the file
	//Dictionary dictionary; // dictionary used to create the index
	Index index; // the document's index
	

	public FileDocument(String pathToFile, String pathToDictionary, String pathToIgnoreWords) throws IOException {
		// give the path to the file, and it creates a document from the file given
		this.index = new MyIndex(pathToFile, pathToDictionary, pathToIgnoreWords);
		fillContents(pathToFile); // fill up nested lists with contents from file
	}
	
	private void fillContents(String pathToFile){
		
	}

	@Override
	public int pageCount() {
		return pageCount; // total number of pages in the document
	}

	@Override
	public String fullDocument() {
		return null; // gives back the file text
	}

	@Override
	public List<Integer> pageNums(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int wordCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	@Override
	public void printSinglePage(int page) {

	}

}
