package component;

import java.util.StringTokenizer;

public class SettingsParserImpl {

    private final String settingsFileContent;

    public SettingsParserImpl(String settingsFileContent){
        this.settingsFileContent = settingsFileContent;
    }

    public Settings getParsedSettings(){

        return parse(settingsFileContent);
    }

    private Settings parse(String settingsFileContent) throws IllegalStateException{
        StringTokenizer st = new StringTokenizer(settingsFileContent,"\n");
        String[] parsed = new String[4];
        int i = 0;

        while(st.hasMoreTokens()){
            String setting = st.nextToken().split("=",-1)[1];
            if(!setting.equals("")){
                parsed[i++] = setting;
            } else {
                parsed[i++] = "none";
            }
        }

        Settings parsedSettings = new Settings(parsed[0],parsed[1],parsed[2],parsed[3]);

        if(parsedSettings.isValid()) {
            return parsedSettings;
        } else {
            throw new IllegalStateException("Wrong Settings! : Essential Format is Empty.");
        }
    }

}
