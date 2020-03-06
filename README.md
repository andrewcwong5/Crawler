# Crawler

The web crawler I built relies on jsoup which is a Java library with a HTML parser.
The program can identify URLs in the HTML source code of any given page, so I would have to include a list of all the pages or topics from the Library like "Allergy & Intolerance", "Blood disorders", etc. and have the crawler iterate through each link.
However I couldn't see where the metadata information like "Title", "Author" was coming from in the HTML for the results of a certain Topic.
I presume its a json string from an API for the Library. So ideally I'd have to build a database with matching fields to extract that data. Then I would output it using FileWriter and PrintWriter into a txt file using the prescribed format.
