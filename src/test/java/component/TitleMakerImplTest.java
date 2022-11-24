package component;

import interfaces.TitleMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TitleMakerImplTest {

    @DisplayName("TitleMaker 생성 테스트")
    @Test
    public void testMakeTitleMaker() {
        final String titleFormat = "[seq일차] day.md";
        final String github = "https://github.com/bmincof/TIL-Maker";
        final TitleMaker sut = TitleMakerImpl.builder()
                .titleFormat(titleFormat)
                .github(github)
                .build();

        assertThat(sut).hasFieldOrPropertyWithValue("titleFormat", titleFormat)
                .hasFieldOrPropertyWithValue("github", github);
    }
    @DisplayName("title 생성")
    @Test
    public void testMakeTitleWithGithub() {
        final String titleFormat = "[seq일차] day.md";
        final String github = "https://github.com/bmincof/TIL";
        final TitleMaker sut = TitleMakerImpl.builder()
                .titleFormat(titleFormat)
                .github(github)
                .build();
        final String today = String.valueOf(LocalDate.now().getDayOfMonth());

        final String expected = "[41일차] " + today +".md";
        final String actual = sut.makeTitle();

        assertThat(actual).isEqualTo(expected);
    }
}