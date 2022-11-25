package component;

import lombok.Getter;

// TODO : setting 파싱할 때 \r 문자 빼고 저장 필요
@Getter
public class Settings {
    private final String titleFormat;
    private final String github;
    private final String tableFormat;
    private final String contentFormat;

    public Settings(String titleFormat, String github, String tableFormat, String contentFormat) {
        this.titleFormat = titleFormat.substring(0,titleFormat.length() - 1);       // because of \r, cannot make file
        this.github = github.substring(0,github.length() - 1);
        this.tableFormat = tableFormat.substring(0,tableFormat.length() - 1);
        this.contentFormat = contentFormat;
    }

    @Override
    public String toString() {
        return String.format("{titleFormat = %s, github = %s, tableFormat = %s, contentFormat = %s}"
                , titleFormat, github, tableFormat, contentFormat);
    }

    public boolean isValid(){

        boolean titleIsValid = !this.titleFormat.equals("none");
        boolean tableIsValid = !this.tableFormat.equals("none");
        boolean contentIsValid = !this.contentFormat.equals("none");

        return  titleIsValid && tableIsValid && contentIsValid;
    }

}
