package time;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoryExtractor {

	public static ArrayList<String[]> extractStories(String htmlContent) {
        ArrayList<String[]> stories = new ArrayList<>();
        
        //  matching the <a> tags inside <h2> tags for story titles and links
        String regex = "<h2.*?><a href=\"(.*?)\".*?>(.*?)</a>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);
        
        // Finding matches and storing them in the list
        while (matcher.find()) {
            String link = "https://time.com" + matcher.group(1);  
            String title = matcher.group(2);
            stories.add(new String[]{title, link});
        }
        
        return stories;
    }

}
