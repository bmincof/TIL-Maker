package component;

import interfaces.BodyMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


class BodyMakerImplTest {

    @Test
    public void makeBodyMaker(){
        String tableFormat = ">## [index. content]";
        String contentFormat = "# content";
        ArrayList<String> contentList = new ArrayList<>(Arrays.asList(new String[] {"test1","test2"}));
        BodyMaker sut = new BodyMakerImpl(tableFormat, contentFormat, contentList);

        assertThat(sut).hasFieldOrPropertyWithValue("tableFormat",tableFormat)
                .hasFieldOrPropertyWithValue("contentFormat",contentFormat)
                .hasFieldOrProperty("contentList");
    }

    @DisplayName("문서 본문 만들기")
    @Test
    public void makeContent() {
        String tableFormat = ">## [index. content]";
        String contentFormat = "# content";
        ArrayList<String> contentList = new ArrayList<>(Arrays.asList(new String[] {"test1","test2"}));
        BodyMaker sut = new BodyMakerImpl(tableFormat, contentFormat, contentList);
        final String expected = "# 목차\n" +
                ">## [1. test1](#test1)\n" +
                ">## [2. test2](#test2)\n" +
                "<br>\n\n" +
                "# test1\n\n\n" +
                "# test2\n\n\n";

        final String actual = sut.makeBody();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주제에 공백이 있을 경우")
    @Test
    public void makeContentWithBlank() {
        String tableFormat = ">## [index. content]";
        String contentFormat = "# content";
        ArrayList<String> contentList = new ArrayList<>(Arrays.asList(new String[] {"test 1","test 2"}));
        BodyMaker sut = new BodyMakerImpl(tableFormat, contentFormat, contentList);

        final String expected = "# 목차\n" +
                ">## [1. test 1](#test-1)\n" +
                ">## [2. test 2](#test-2)\n" +
                "<br>\n\n" +
                "# test 1\n\n\n" +
                "# test 2\n\n\n";

        final String actual = sut.makeBody();

        assertThat(actual).isEqualTo(expected);
    }

}
