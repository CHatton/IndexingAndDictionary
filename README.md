# Public Document Interface

# int pageCount()
returns the number of pages in the document, each page consists of 40 lines of text

# String pageRange(int from, int to)
returns the pages between from (inclusive) and to (not inclusive) as a string

# String fullDocument()
returns the full document as a String

# List<Integer> pageNums(String word)
returns a list of integers indicating the page numbers that the given word appears on,
an empty list will be returned if there ar no occurrences

# boolean contains(String word)
returns true if the given word exists in the index of the document

# int wordCount()
returns the number of words in the document, it includes all words NOT in the index

# String singlePage(int page)
returns the 40 line page as a string

