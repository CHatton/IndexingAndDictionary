package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDocumentProvider {

	Document get(String pathToFile) {
		List<String> fileContents = new ArrayList<>();
		try {
			fileContents = fillContents(pathToFile); // fill document contents from file
		} catch (IOException e) {
			System.err.println("Warning: There was an error reading from the e-book - check file '" + pathToFile + "'");
		}
		ConcreteDocument d = new ConcreteDocument(fileContents);
		d.setSource(pathToFile);
		return d;
	}

	private List<String> fillContents(String pathToFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		StringBuilder page = new StringBuilder(); // represents a full page
		String next;
		List<String> fileContents = new ArrayList<>();
		while ((next = reader.readLine()) != null) {

			for (int i = 0; i < 40; i++) {
				if (next != null) { // don't want to add nulls to end of text
					page.append(next + "\n"); // add 40 non null lines of text
												// to the single string
				}
				next = reader.readLine();
			}
			fileContents.add(page.toString()); // add this page to the arraylist
			page = new StringBuilder(); // empty the string
		}
		reader.close();
		return fileContents;
	}

}
