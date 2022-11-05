package component;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


class BodyMakerImplTest {

    @DisplayName("문서 본문 만들기")
    @Test
    public void makeContent() {
        final ArrayList<String> testContent =
                new ArrayList<>(Arrays.asList(new String[]{"test1", "test2"}));
        final String expected = "# 목차\n" +
                ">## [1. test1](#test1)\n" +
                ">## [2. test2](#test2)\n" +
                "<br>\n\n" +
                "# test1\n\n\n" +
                "# test2\n\n\n";
        final BodyMakerImpl contentMaker = new BodyMakerImpl(testContent);

        final String actual = contentMaker.makeBody();

        assertThat(actual).isEqualTo(expected);
    }
}