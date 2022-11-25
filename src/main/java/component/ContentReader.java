package component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ContentReader {

    private ContentReader() {
    }

    public static LinkedList<String> getContents() {
        LinkedList<String> contents = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            label:
            while (true) {
                System.out.println("작성할 주제에 대해 입력");
                System.out.println("0. 종료");
                System.out.println("1. 목록 보기");
                System.out.println("2. 수정");
                System.out.println("3. 삭제");
                System.out.println("*************");

                String content = br.readLine();
                switch (content) {
                    case "0":
                        break label;
                    case "1":
                        System.out.println(Arrays.toString(contents.toArray()));
                        break;
                    case "2": {
                        System.out.println("수정할 목록 번호를 입력하세요");
                        int idx = br.read() - '0';
                        System.out.println("내용을 입력하세요");
                        String modify = br.readLine();

                        contents.set(idx, modify);
                        break;
                    }
                    case "3": {
                        System.out.println("삭제할 목록 번호를 입력하세요");
                        int idx = br.read() - '0';
                        contents.remove(idx);
                        break;
                    }
                    default:
                        contents.add(content);
                        break;
                }
            }
            return contents;
        } catch (IOException e) {
            return contents;
        }
    }
}
