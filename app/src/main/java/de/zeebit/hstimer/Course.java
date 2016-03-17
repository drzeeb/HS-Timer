package de.zeebit.hstimer;

import java.util.ArrayList;

/**
 * Created by Michael on 15.03.2016.
 */
public class Course {
    private String course;
    private ArrayList<Semester> semesters;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }
}
