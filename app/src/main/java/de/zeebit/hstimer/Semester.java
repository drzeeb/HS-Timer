package de.zeebit.hstimer;

import java.util.ArrayList;

/**
 * Created by Michael on 15.03.2016.
 */
public class Semester {
    private String semester;
    private ArrayList<Day> days;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
}
