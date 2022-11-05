package component;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SettingsParserImplTest {

    @Test
    @DisplayName("설정파일 생성 후 설정파일 내용을 가지고 있어야 함")
    public void testMake(){
        String settings = "title=Day";
        SettingsParserImpl sut = new SettingsParserImpl(settings);

        assertThat(sut).isNotNull()
                .hasFieldOrPropertyWithValue("settingsFileContent","title=Day");
    }

    @Test
    @DisplayName("설정파일 파싱 테스트")
    public void testParseSettings(){
        String settingsFileContent = "titleFormat=Day\nGitHub=https://github.io/\ntableFormat=>\nbodyFormat=# {sub}\n";
        SettingsParserImpl sut = new SettingsParserImpl(settingsFileContent);

        Settings settings = sut.getParsedSettings(settingsFileContent);

        assertThat(settings.toString()).contains("Day","https://github.io/",">","# {sub}");
    }

    @Test
    @DisplayName("설정 내용이 none이면 none")
    public void testParseSettingsWhenHasNone(){
        String settingsFileContent = "titleFormat=Day\nGitHub=none\ntableFormat=>\nbodyFormat=# {sub}\n";
        SettingsParserImpl sut = new SettingsParserImpl(settingsFileContent);

        Settings settings = sut.getParsedSettings(settingsFileContent);

        assertThat(settings.toString()).contains("Day","none",">","# {sub}");
    }

    @Test
    @DisplayName("설정 내용이 없으면 none")
    public void testParseSettingsWhenHasNull(){
        String settingsFileContent = "titleFormat=Day\nGitHub=\ntableFormat=>\nbodyFormat=# {sub}\n";
        SettingsParserImpl sut = new SettingsParserImpl(settingsFileContent);

        Settings settings = sut.getParsedSettings(settingsFileContent);

        assertThat(settings.toString()).contains("Day","none",">","# {sub}");
    }

    @Test
    @DisplayName("필수 값 없으면 exception")
    public void testParseSettingsWhenEssentialHasEmpty(){
        String settingsFileContent = "titleFormat=\nGitHub=none\ntableFormat=>\nbodyFormat=# {sub}\n";
        SettingsParserImpl sut = new SettingsParserImpl(settingsFileContent);

        assertThatThrownBy(()->{sut.getParsedSettings(settingsFileContent);})
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Essential Format is Empty.");
    }

}