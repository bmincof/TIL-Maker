package component;

import interfaces.BodyMaker;

import java.util.ArrayList;
import java.util.LinkedList;

public class BodyMakerImpl implements BodyMaker {

    private final String tableFormat;
    private final String contentFormat;
    private final LinkedList<String> contentList;


    public BodyMakerImpl(String tableFormat, String contentFormat, LinkedList<String> contentList) {
        this.tableFormat = tableFormat;
        this.contentFormat = contentFormat;
        this.contentList = contentList;
    }

    @Override
    public String makeBody() {
        return makeContentTable() + makeContentBody();
    }

    private String makeContentTable() {
        StringBuilder table = new StringBuilder("# 목차\n");

        for (int i=0; i< contentList.size(); i++) {
            String newContent = tableFormat
                    .replace("index", String.valueOf(i+1))
                    .replace("content", contentList.get(i));
            table.append(newContent+makeRef(contentList.get(i))+"\n");
        }
        table.append("<br>\n\n");

        return table.toString();
    }
    private String makeRef(String content) {
        return "(#"+ content.replaceAll(" ","-") + ")";
    }

    private String makeContentBody() {
        StringBuilder body = new StringBuilder();

        for (int i=0; i<contentList.size(); i++) {
            String newBody = contentFormat.replace("content", contentList.get(i));
            body.append(newBody+"\n\n\n");
        }

        return body.toString();
    }

}
