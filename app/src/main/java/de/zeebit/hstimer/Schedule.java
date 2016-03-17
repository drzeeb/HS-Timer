package de.zeebit.hstimer;

import java.util.ArrayList;

/**
 * Created by Michael on 15.03.2016.
 */
public class Schedule {
    private String start;
    private String end;
    private boolean empty;
    private ArrayList<Subject> subjects;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
