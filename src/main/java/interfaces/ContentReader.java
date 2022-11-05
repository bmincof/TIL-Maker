package interfaces;

import java.io.InputStream;

/**
 * 사용자로부터 작성할 내용 입력받는 객체
 *
 */

public interface ContentReader {

    public void makeContentList(InputStream inputStream);
    public String getContentList();
}
