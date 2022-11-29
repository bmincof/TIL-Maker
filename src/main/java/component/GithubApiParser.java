package component;

import interfaces.Parser;
import interfaces.UrlScraper;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class GithubApiParser implements Parser {

    private final UrlScraper scraper;

    public GithubApiParser(UrlScraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public Map<String, String> parse() {

        String json = scraper.getInfoFromWeb();
        // JSON Parsing
        JSONArray commits = new JSONArray(json);
        String message = commits.getJSONObject(0).getJSONObject("commit").getString("message");

        int startIdx = message.indexOf("[");
        int endIdx = message.indexOf("일");
        String dayInfo = message.substring(startIdx + 1, endIdx);

        Map<String, String> scraped = new HashMap<>();
        scraped.put("day", dayInfo);
        return scraped;

    }

}
