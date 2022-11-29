package component;

import interfaces.UrlScraper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GithubApiParserTest {

    @Mock
    UrlScraper scraper;

    @InjectMocks
    GithubApiParser sut;
    String scraped = "[{\"commit\": {\n" +
            "      \"message\": \"[45일차] Mockito를 이용한 mocking 시도\",\n" +
            "      }\n" +
            "  }]";

    @Test
    void testParse() {

        doReturn(scraped).when(scraper).getInfoFromWeb();

        HashMap<String, String> parsed = (HashMap<String, String>) sut.parse();
        assertThat(parsed).hasSize(1)
                .hasFieldOrPropertyWithValue("day","45");
    }

}