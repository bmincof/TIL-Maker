package component;

import interfaces.BodyMaker;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileMaker {

    private String pathName;
    private String fileName;
    private BodyMaker bodyMaker = new BodyMakerImpl(new ArrayList<>(Arrays.asList(new String[]{"test1","test2"})));

    public FileMaker(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    public String makefile() {

        File file = new File(pathName+fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created");

                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);

                writer.write(bodyMaker.makeBody());
                writer.close();

                return "success";
            } else {
                System.out.println("File already exists");
                return "failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }

    }
}
