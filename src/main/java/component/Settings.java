package component;

public class Settings {
    private final String titleFormat;
    private final String github;
    private final String tableFormat;
    private final String contentFormat;

    public Settings(String titleFormat, String github, String tableFormat, String contentFormat) {
        this.titleFormat = titleFormat;
        this.github = github;
        this.tableFormat = tableFormat;
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
