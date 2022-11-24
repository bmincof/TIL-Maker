package component;

import interfaces.TitleMaker;
import lombok.Builder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Builder
public class TitleMakerImpl implements TitleMaker {

    private final String titleFormat;
    private final String github;

    @Override
    public String makeTitle() {
        try{
            return replaceFormat(loadSeqInfo());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String replaceFormat(String format) {
        return format.replace("day", String.valueOf(LocalDate.now().getDayOfMonth()));
    }

    private String loadSeqInfo() throws IOException {
        URL url = new URL("https://api.github.com/repos/bmincof/TIL/commits");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json"); // Content-Type 지정
        conn.setDoOutput(true); // 출력 가능 상태로 변경
        conn.connect();

// 데이터  읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
            sb.append(line); // StringBuilder 사용시 객체를 계속 생성하지 않고 하나의 객체릂 수정하므로 더 빠름.
        }
        conn.disconnect();

// JSON Parsing
        String json = sb.toString();

        JSONArray commits = new JSONArray(json);
        String message = commits.getJSONObject(0).getJSONObject("commit").getString("message");

        int startIdx = message.indexOf("[");
        int endIdx = message.indexOf("일");
        String dayInfo = message.substring(startIdx + 1, endIdx);

        return titleFormat.replace("seq",Integer.parseInt(dayInfo) + 1 + "");
    }

}
