Notes for running the program

This program has dependencies
	-jsoup 1.8.3

I've included jsoup and a runnable jar of the program to make it easier to test.

it can be run with "java -jar g00265311.jar"

the default files it uses to make a document are

WarAndPeace-LeoTolstoy.txt
stopwords.txt
dictionary.csv

the default URL document is taken from "http://www.huffingtonpost.co.uk/"

However you can use your own custom files or URL at run time by following the on-screen instructions.

=======================================================

Notes on design decisions.

Along with the index interface, I also chose to create a document interface.

Initially, I had two different implementations of the document interface,
I had a FileDocument, and a URLDocument. However, both of these shared around
90% of the same code, and they only differred in their constructors.

I had thought about combining them into the same class, and having multiple constructors,
but I decided against it, because this would make the documents created from a file
dependant on Jsoup, which it has no reason to be.

I then decided to create providers for both a File and URL document. The only thing
that needs to be done is create a list of strings, and call the document constructor.

Also, if I wanted to make a new type of a document from a different source,
all I'd need to do would be to create a new provided that can create a list
of strings, and it can then provide a document.

At first I had combined the dictionary and the index all into the document class,
but I then thought that it would be better to break everything out separately.
For example, a document can exist without an index, so there's no reason that
the document should be forced to have an index. I did my best to ensure that everything
has a single responsibility.

In the main DocumentRunner class, the user is able to access all the public document
and index interface methods.