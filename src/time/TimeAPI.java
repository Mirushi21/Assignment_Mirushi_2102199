package time;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class TimeAPI {

	public static void main(String[] args) {
        try {
            // Create and start the HTTP server
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/getTimeStories", new TimeStoriesHandler());
            server.start();
            System.out.println("Server started at http://localhost:8080/getTimeStories");
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }

    static class TimeStoriesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
               
                String htmlContent = HTMLFetcher.fetchHTML("https://time.com");

                
                List<String[]> stories = StoryExtractor.extractStories(htmlContent);

                //  latest 6 stories
                stories = stories.subList(0, Math.min(6, stories.size()));

                //  JSON format
                String jsonResponse = createJsonResponse(stories);

                // response
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponse.getBytes());
                }
            } catch (Exception e) {
                // Handle any exceptions that occur while processing the request
                String errorResponse = "{\"error\": \"An error occurred while processing the request.\"}";
                exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorResponse.getBytes());
                }
            }
        }

        // convert the stories list to JSON string
        private String createJsonResponse(List<String[]> stories) {
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("[");

            for (int i = 0; i < stories.size(); i++) {
                String[] story = stories.get(i);
                jsonResponse.append("{");
                jsonResponse.append("\"title\": \"").append(story[0]).append("\", ");
                jsonResponse.append("\"link\": \"").append(story[1]).append("\"");
                jsonResponse.append("}");

                if (i < stories.size() - 1) {
                    jsonResponse.append(", ");
                }
            }

            jsonResponse.append("]");
            return jsonResponse.toString();
        }
    }


}
