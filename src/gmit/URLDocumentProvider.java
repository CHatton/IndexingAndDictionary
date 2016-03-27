package gmit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLDocumentProvider {

	Document get(String inputUrl) {
		List<String> fileContents = new ArrayList<>();
		try {
			// Make a URL to the web page
			URL url = new URL(inputUrl);

			// Get the input stream through URL Connection
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			// JSOUP stuff
			org.jsoup.nodes.Document doc = Jsoup.parse(sb.toString());
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

				fileContents.add(current.toString());
				current = new StringBuilder(); // reset contents
				// add up all paragraphs
			}
			// END JSOUP stuff
		} catch (IOException e) {
			System.out.println("There was an error creating the document from " + inputUrl);
		}
		
		ConcreteDocument d = new ConcreteDocument(fileContents);
		d.setSource(inputUrl);
		return d;
	}

}
