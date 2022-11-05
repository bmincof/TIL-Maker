import component.FileMaker;
import component.TitleMakerImpl;

public class Run {
    public static void main(String[] args) {

        String title = new TitleMakerImpl().makeTitle();
        FileMaker fileMaker = new FileMaker("C:\\example\\",title);
        fileMaker.makefile();
    }
}
