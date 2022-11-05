package component;

import interfaces.TitleMaker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TitleMakerImpl implements TitleMaker {

    private String titleFormat = "[n일차] day.md";
    private String TILday = "10";
    private String today = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));

    @Override
    public String makeTitle(){

        return titleFormat.replace("n",TILday).replace("day",today);
    }

}
