package de.zeebit.hstimer;

import java.util.ArrayList;

/**
 * Created by Michael on 15.03.2016.
 */
public class Day {
    private int weekday;
    private boolean holedayempty;
    private ArrayList<Schedule> schedules;


    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public boolean isHoledayempty() {
        return holedayempty;
    }

    public void setHoledayempty(boolean holedayempty) {
        this.holedayempty = holedayempty;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }
}
