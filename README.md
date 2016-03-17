# Public Document Interface

# int pageCount()
returns the number of pages in the document, each page consists of 40 lines of text

# String pageRange(int from, int to)
returns the pages between from (inclusive) and to (not inclusive) as a string

# String fullDocument()
returns the full document as a String

# List\<Integer\> pageNums(String word)
returns a list of integers indicating the page numbers that the given word appears on,
an empty list will be returned if there ar no occurrences

# boolean contains(String word)
returns true if the given word exists in the index of the document

# int wordCount()
returns the number of words in the document, it includes all words NOT in the index

# String singlePage(int page)
returns the 40 line page as a string

# String getIndex()
returns the entire contents of the index as a string. 
This includes each word, its definitions and the page numbers that word appears on (in sorted order).

# List\<String\> getDefinitions(String word)
this method returns a list of defintions assosiated with the given word, if that word doesn't exist, an empty list will be returned.

# String allPagesWith(String word)
this method returns a string which is the concatenation of all pages that contain the given word.

# List\<String\> getInvalidWords()
this method returns a list of strings with the words that were found in the text, but were not in the dictionary, or which did appear in the stopwords list.

#List \<String\> didYouMean(String word)
this method gives back a list of words that are similar to the given word.
