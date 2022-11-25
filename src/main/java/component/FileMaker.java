package component;

import java.io.*;

public class FileMaker {

    private final String directoryPath;
    private final String fileName;
    private final String body;
    public FileMaker(String directoryPath, String fileName, String body) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.body = body;
    }

    public String makefile() {

        File file = new File(directoryPath, fileName);

        try {
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);

                writer.write(body);
                writer.close();

                return "성공 : 파일 생성";
            } else {
                return "실패 : 이미 존재하는 파일명";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
}
