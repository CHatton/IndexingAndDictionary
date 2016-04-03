package gmit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dictionary {
	private Map<String, List<String>> dictionary = new HashMap<>();
	// maps a words to list of definitions

	public Dictionary() {
		this.dictionary = new HashMap<String, List<String>>(); // give back empty dictionary
	} // to be used if the there is an I/O error

	public Dictionary(String pathToFile) {
		// give the path to a dictionary file and it creates the map
		try {
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
					addToDictionary(word.toUpperCase(), newDefinition.toString());
					// upper case to make everything that's added case insensitive
					newDefinition = new StringBuilder(); // clear string
				}
			}
			reader.close();
		} catch (IOException e) {
			this.dictionary = new HashMap<String, List<String>>(); // give back empty dictionary
		}

		/*
		 * Construction of the dictionary map has an O(n) time complexity
		 * where n is the size of the document, each line has to be processed
		 * once. Each word that is added is a constant time operation O(1)
		 */

	} // main constructor

	public boolean contains(String s) {
		return dictionary.containsKey(s.toUpperCase());
	}

	private void addToDictionary(String wordToAdd, String definitions) {
		if (!dictionary.containsKey(wordToAdd)) {
			List<String> newList = new ArrayList<>(); // make a new list because the word doesn't exist yet
			newList.add(definitions); // add the definition to the list
			dictionary.put(wordToAdd, newList); // put it in the dictionary
			/*
			 * Adding the definition to the new list is a constant
			 * time operation. O(1)
			 */
		} else { // if we're here, then the item already has an entry
			// we want to get the entry, and update it
			dictionary.get(wordToAdd).add(definitions); // add the new definition to the existing list

			/*
			 * both accessing the correct list from the map
			 * and adding a new definition to that list
			 * are constant time operations O(1)
			 */
		}
	}

	@Override
	public String toString() {
		return "The dictionary contains " + dictionary.size() + " words.\n";
	}

	// accessing the size is a constant time operation

	public List<String> getDefinitions(String word) {
		return dictionary.get(word); // constant time
	}

	public Set<String> getAllWords() {
		return dictionary.keySet(); // all words in the dictionary
	}
} // class
