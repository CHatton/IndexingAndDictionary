package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private Map<String, WordDetail> dictionary = new HashMap<>();
	// maps a words to Word details, which contains a list of strings
	
	public Dictionary(String pathToFile) {

		try {
			// give the path to a dictionary file and it creates the map
			BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
			StringBuilder newDefinition = new StringBuilder();
			reader.readLine(); // don't want first line
			String next = reader.readLine(); // hold each line of text

			while (next != null) { // until we hit the end of the file
				if (next.charAt(0) == '"') { // then we've hit the start of a new word
					String word = next.substring(1, next.indexOf('"', 1)); // get the word of the current definition
					do {
						newDefinition.append(next + "\n"); // add the current line onto the multi-line definition
						next = reader.readLine(); // move onto next one
					} while (next != null && next.charAt(0) != '"');
					// null check for last line in the file

					// once we're here we've built up a full definition
					addToDictionary(word, newDefinition.toString());
					newDefinition = new StringBuilder(); // clear string
				}
			}
		} catch (IOException e) {
			System.out.println("There was an error creating the dictionary from " + pathToFile +", please make sure the file is there.");
		}
	} // constructor
	
	public boolean contains(String s){
		return dictionary.containsKey(s);
	}

	private void addToDictionary(String wordToAdd, String definitions) {
		if (dictionary.get(wordToAdd) == null) {
			ArrayList<String> newList = new ArrayList<>(); // make a new list because the word doesn't exist yet
			newList.add(definitions); // add the definition to the list
			WordDetail w = new WordDetail(newList); // create a word detail with the 1 item list
			dictionary.put(wordToAdd, w); // put it in the dictionary
		} else { // if we're here, then the item already has an entry
			// we want to get the entry, and update it
			dictionary.get(wordToAdd).addEntry(definitions); // add the new definition to the existing list
		}
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("The dictionary contains " + dictionary.size() +" Words.");
		return sb.toString();
	}
	
	public WordDetail getDetail(String word) {
		return dictionary.get(word);
	}
} // class
