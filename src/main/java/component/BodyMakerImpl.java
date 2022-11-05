package component;

import interfaces.BodyMaker;

import java.util.ArrayList;

public class BodyMakerImpl implements BodyMaker {

    private final ArrayList<String> contentList;
    private final String tableFormat = ">## [index. content](#content)";
    private final String bodyFormat = "# content";


    public BodyMakerImpl(ArrayList<String> contentList) {
        this.contentList = contentList;
    }

    /**
     *
     *
     * @return
     */

    private String makeContentTable() {
        StringBuilder table = new StringBuilder("# 목차\n");

        for (int i=0; i< contentList.size(); i++) {
            String newContent = tableFormat
                    .replace("index", String.valueOf(i+1))
                    .replaceAll("content", contentList.get(i));
            table.append(newContent+"\n");
        }
        table.append("<br>\n\n");

        return table.toString();
    }

    private String makeContentBody() {
        StringBuilder body = new StringBuilder();

        for (int i=0; i<contentList.size(); i++) {
            String newBody = bodyFormat.replace("content", contentList.get(i));
            body.append(newBody+"\n\n\n");
        }

        return body.toString();
    }

    @Override
    public String makeBody() {
        return makeContentTable() + makeContentBody();
    }

}
