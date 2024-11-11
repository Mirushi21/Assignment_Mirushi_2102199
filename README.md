**Time.com Latest Stories API**
This project implements a basic web service that fetches the latest 6 stories from Time.com and serves them as JSON via a custom API endpoint. The code is implemented in Java, without using any external libraries for HTML parsing, in compliance with assignment requirements.

**Features**
Fetches the Latest Stories: The application retrieves the latest 6 stories from Time.com by reading the HTML content and manually extracting titles and URLs.
Serves as an API: The API is accessible at http://localhost:8080/getTimeStories, and it responds with a JSON array containing the title and link for each story.
**Project Structure**
**Files**
HTMLFetcher.java: Contains the code to fetch HTML content from Time.com.
StoryExtractor.java: Parses the HTML content to extract titles and links for the latest stories.
SimpleAPI.java: Sets up a simple HTTP server to serve the extracted stories as JSON at the /getTimeStories endpoint.
How It Works
HTML Fetching: HTMLFetcher uses an HTTP GET request to retrieve the HTML content of Time.com's homepage.
HTML Parsing: StoryExtractor analyzes the HTML content, looking for the latest news story titles and URLs. Since we can't use external libraries, parsing is done with basic string manipulation.
Serving JSON: SimpleAPI starts a basic HTTP server on localhost:8080 and listens for requests. When a client sends a GET request to /getTimeStories, it responds with a JSON array of stories.
**Prerequisites**
Java Development Kit (JDK): Make sure you have JDK installed to compile and run the Java code.
ChromeDriver (if using Selenium): If dynamic content rendering is necessary, use Selenium to fully load the page. Download ChromeDriver from here and configure it accordingly.

I was restricted to using only Java and not allowed to use third-party libraries or headless browsers, I was not be able to fetch dynamically loaded content effectively. However, if you are allowed to use Selenium, it should be a reliable way to retrieve the full content.
Mirushi
2102199
