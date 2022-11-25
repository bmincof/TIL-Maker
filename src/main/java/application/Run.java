package application;

import component.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Run {
    public static void main(String[] args) throws IOException {

        String directoryPath = "C:\\TILmaker\\fortest\\";
        String settingsFileName = "settings.txt";

        String settings = "";
        try{
            File settingsFile = new File(directoryPath + settingsFileName);

            FileInputStream fileStream = new FileInputStream(settingsFile);
            //버퍼 선언
            byte[ ] readBuffer = new byte[fileStream.available()];
            while (fileStream.read( readBuffer ) != -1){}
            settings = new String(readBuffer); //출력

            fileStream.close(); //스트림 닫기
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        Settings parsedSettings = new SettingsParserImpl(settings).getParsedSettings();

        //testing
//        InputStream in = generateUserInput("test1\ntest 2\n0\n");
//        System.setIn(in);

        LinkedList<String> contents = ContentReader.getContents();

        String fileName = TitleMakerImpl.builder()
                .titleFormat(parsedSettings.getTitleFormat())
                .github(parsedSettings.getGithub())
                .build().makeTitle();

        String body = new BodyMakerImpl(parsedSettings.getTableFormat(),
                parsedSettings.getContentFormat(),
                contents).makeBody();

        FileMaker fileMaker = new FileMaker(directoryPath, fileName, body);
        fileMaker.makefile();
    }

    public static InputStream generateUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

}
