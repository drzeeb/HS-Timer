package de.zeebit.hstimer;

/**
 * Created by Michael on 16.03.2016.
 */

import java.util.ArrayList;
import java.util.Calendar;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandlerScheduler extends DefaultHandler {

    Course course = null;
    Semester semester = null;
    Day day = null;
    Schedule schedule = null;
    Subject subject = null;
    private ArrayList<Course> coursesList = new ArrayList<Course>();
    private ArrayList<Semester> semestersList;
    private ArrayList<Schedule> schedulesList;
    private ArrayList<Day> daysList;
    private ArrayList<Subject> subjectsList;

    public ArrayList<Course> getCourseList() {
        return coursesList;
    }

    // Called when tag starts
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        if (qName.equals("its")) {
            course = new Course();
            course.setCourse("its");
            semestersList = new ArrayList<>();
        } else if(qName.equals("ti")){
            course = new Course();
            course.setCourse("ti");
            semestersList = new ArrayList<>();
        } else if(qName.equals("win")){
            course = new Course();
            course.setCourse("win");
            semestersList = new ArrayList<>();
        } else if(qName.equals("sem1")){
            semester = new Semester();
            semester.setSemester("sem1");
            daysList = new ArrayList<>();
        } else if(qName.equals("mon")){
            day = new Day();
            day.setWeekday(Calendar.MONDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("tue")){
            day = new Day();
            day.setWeekday(Calendar.TUESDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("wed")){
            day = new Day();
            day.setWeekday(Calendar.WEDNESDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("thu")){
            day = new Day();
            day.setWeekday(Calendar.THURSDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("fri")){
            day = new Day();
            day.setWeekday(Calendar.FRIDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("sat")){
            day = new Day();
            day.setWeekday(Calendar.SATURDAY);
            schedulesList = new ArrayList<>();
            if(attributes.getValue("holedayempty").equals("false")){
                day.setHoledayempty(false);
            } else {
                day.setHoledayempty(true);
            }

        } else if(qName.equals("time")){
            schedule = new Schedule();
            schedule.setStart(attributes.getValue("start"));
            schedule.setEnd(attributes.getValue("end"));
            subjectsList = new ArrayList<>();
            if(attributes.getValue("empty").equals("false")){
                schedule.setEmpty(false);
            } else {
                schedule.setEmpty(true);
            }
        } else if(qName.equals("subject")){
            subject = new Subject();
            subject.setName(attributes.getValue("name"));
            subject.setProf(attributes.getValue("prof"));
            subject.setRoom(attributes.getValue("room"));
            subject.setGroup(attributes.getValue("group"));
        }

    }

    // Called when tag closing
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if(qName.equals("its") || qName.equals("ti") || qName.equals("win")){
            course.setSemesters(semestersList);
            coursesList.add(course);
        } else if(qName.equals("sem1")){
            semester.setDays(daysList);
            semestersList.add(semester);
        } else if(qName.equals("mon") || qName.equals("tue") || qName.equals("wed") || qName.equals("thu") || qName.equals("fri") || qName.equals("sat")){
            day.setSchedules(schedulesList);
            daysList.add(day);
        } else if(qName.equals("time")){
            schedule.setSubjects(subjectsList);
            schedulesList.add(schedule);
        } else if(qName.equals("subject")){
            subjectsList.add(subject);
        }
    }

    // Called to get tag characters
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        // We don't need this, because our values are set as an attribute

    }

}
