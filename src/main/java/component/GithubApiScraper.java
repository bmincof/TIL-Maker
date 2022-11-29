package component;

import interfaces.UrlScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubApiScraper implements UrlScraper {

    private final URL url;

    public GithubApiScraper(URL url) {
        this.url = url;
    }

    public String getInfoFromWeb() {
        String info = "";
        try{
            HttpURLConnection conn = accessToAPI(url);
            info = loadInfoFromWeb(conn.getInputStream());
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    private HttpURLConnection accessToAPI(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json"); // Content-Type 지정
        conn.setDoOutput(true); // 출력 가능 상태로 변경
        return conn;
    }

    private String loadInfoFromWeb(InputStream inputStream) throws IOException {
// 데이터  읽어오기
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null) {
            sb.append(line); // StringBuilder 사용시 객체를 계속 생성하지 않고 하나의 객체릂 수정하므로 더 빠름.
        }

        return sb.toString();
    }
}
