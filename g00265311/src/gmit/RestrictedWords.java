package gmit;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RestrictedWords {

	Set<String> stopWords = new HashSet<>();

	public RestrictedWords(String pathToStopWords) {
		try {
			Scanner sc = new Scanner(new FileReader(pathToStopWords));
			while (sc.hasNext()) {
				stopWords.add(sc.next().toUpperCase()); // add every word in the file to the set
			}
		} catch (IOException e) {
			System.err.println("There was an error reading from file " + pathToStopWords);
		}
	}
	
	public boolean contains(String word) {
		return stopWords.contains(word.toUpperCase());
		// constant time operation
		// all that's needed is knowing if someting is it it
	}

}
