package gmit;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLDocumentProvider {

	Document get(String inputUrl) {
		List<String> fileContents = new ArrayList<>();
		try {
			// JSOUP stuff
			org.jsoup.nodes.Document doc = Jsoup.connect(inputUrl).get();
			// gets document out of html
			// didn't know Document was a thing already until I found this!

			Elements p = doc.getElementsByTag("p"); // select all p tags
			StringBuilder current = new StringBuilder();
			for (Element individalParagraph : p) { // do this for every paragraph

				int length = individalParagraph.text().length();

				if (length > 160) {
					current.append(individalParagraph.text().substring(0, length / 4) + "\n");
					current.append(individalParagraph.text().substring(length / 4, 2 * (length / 4)) + "\n");
					current.append(individalParagraph.text().substring(2 * (length / 4), 3 * (length / 4)) + "\n");
					current.append(individalParagraph.text().substring(3 * (length / 4)));
				} else if (length > 120) {
					current.append(individalParagraph.text().substring(0, (length / 3)) + "\n");
					current.append(individalParagraph.text().substring(length / 3, 2 * (length / 3)) + "\n");
					current.append(individalParagraph.text().substring(2 * (length / 3)));
				} else if (length > 80) {
					current.append(individalParagraph.text().substring(0, length / 3) + "\n");
					current.append(individalParagraph.text().substring(length / 3, 2 * (length / 3)) + "\n");
				} else {
					current.append(individalParagraph.text());
				}
				// just here to split up the longer paragraphs, not a perfect solution, but I just
				// wanted the user to be able to see the full paragraph

				fileContents.add(current.toString());
				current = new StringBuilder(); // reset contents
				// add up all paragraphs
			}
			// END JSOUP stuff
		} catch (Exception e) { // chose to catch all exceptions as there are many different
			// exceptions that can happen when dealing with urls
			System.err.println("There was an error creating the document from " + inputUrl);
			inputUrl = "Error creating.";
			fileContents = new ArrayList<>(); // give back empty array if there was an error
		}

		return new ConcreteDocument(fileContents, inputUrl);

		/*
		 * time complexity of O(n + m) where n is the size
		 * of all the paragraphs being dealt with and m
		 * is the time take from creating the document
		 * from the url
		 */
	}

}
