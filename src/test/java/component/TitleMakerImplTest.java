package component;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TitleMakerImplTest {

    @DisplayName("제목 생성 테스트")
    @Test
    public void titleMake() {
        final String expected = "[10일차] 3.md";
        final TitleMakerImpl titleMaker = new TitleMakerImpl();

        final String actual = titleMaker.makeTitle();

        assertThat(actual).isEqualTo(expected);

    }

}