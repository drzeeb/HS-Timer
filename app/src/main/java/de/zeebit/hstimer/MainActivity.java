package de.zeebit.hstimer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ArrayList<Course> courses = null;
    private String course = "";
    private String semester = "";
    private String groupInformatic = "";
    private String groupProgramming = "";

    private void showMainFragment(){
        final ViewGroup container = (ViewGroup)findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_main, null));
        container.findViewById(R.id.btnNext).setOnClickListener(this);
    }

    private void showDaysFragment(){
        initialize();
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_days, null));
        container.findViewById(R.id.btnMonday).setOnClickListener(this);
        container.findViewById(R.id.btnTuesday).setOnClickListener(this);
        container.findViewById(R.id.btnWednesday).setOnClickListener(this);
        container.findViewById(R.id.btnThursday).setOnClickListener(this);
        container.findViewById(R.id.btnFriday).setOnClickListener(this);
        container.findViewById(R.id.btnSaturday).setOnClickListener(this);
        container.findViewById(R.id.btnToday).setOnClickListener(this);
    }

    private void loadCourses(){
        parseXML();
    }

    private void loadSharedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        course = preferences.getString("course", "");
        semester = preferences.getString("semester", "");
        groupInformatic = preferences.getString("groupInformatic", "");
        groupProgramming = preferences.getString("groupProgramming", "");
    }

    private void writeSharedPreferences(String course, String semester, String groupInformatic, String groupProgramming){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("settingsfilled", "true");
        editor.putString("course", course);
        editor.putString("semester", semester);
        editor.putString("groupInformatic", groupInformatic);
        editor.putString("groupProgramming", groupProgramming);
        editor.apply();

    }
    private void initialize(){
        loadSharedPreferences();
        loadCourses();
    }

    private void showMonday(){
        Day d;
        d = getChoosenDay(Calendar.MONDAY);
        showTimerFragment(d);
    }

    private void showTuesday(){
        Day d;
        d = getChoosenDay(Calendar.TUESDAY);
        showTimerFragment(d);
    }

    private void showWednesday(){
        Day d;
        d = getChoosenDay(Calendar.WEDNESDAY);
        showTimerFragment(d);
    }

    private void showThursday(){
        Day d;
        d = getChoosenDay(Calendar.THURSDAY);
        showTimerFragment(d);
    }

    private void showFriday(){
        Day d;
        d = getChoosenDay(Calendar.FRIDAY);
        showTimerFragment(d);
    }

    private void showSaturday(){
        Day d;
        d = getChoosenDay(Calendar.SATURDAY);
        showTimerFragment(d);
    }

    private Day getChoosenDay(int day){
        Day choosenDay = new Day();
        for(Course cCourse : courses){
            if(cCourse.getCourse().equals(course)){
                for(Semester sem : cCourse.getSemesters()){
                    if(sem.getSemester().equals(semester)){
                        for(Day d : sem.getDays()){
                            if(d.getWeekday() == day){
                                choosenDay = d;
                            }
                        }
                    }
                }
            }
        }
        return choosenDay;
    }

    private String translateWeekday(int weekday){
        String result = "";
        if(weekday == Calendar.MONDAY){result = getResources().getString(R.string.monday_long);}
        if(weekday == Calendar.TUESDAY){result = getResources().getString(R.string.tuesday_long);}
        if(weekday == Calendar.WEDNESDAY){result = getResources().getString(R.string.wednesday_long);}
        if(weekday == Calendar.THURSDAY){result = getResources().getString(R.string.thursday_long);}
        if(weekday == Calendar.FRIDAY){result = getResources().getString(R.string.friday_long);}
        if(weekday == Calendar.SATURDAY){result = getResources().getString(R.string.saturday_long);}

        return result;
    }

    private void showTimerFragment(Day d){
        initialize();
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_timer, null));
        TextView day = (TextView) container.findViewById(R.id.tvDay);
        day.setText(translateWeekday(d.getWeekday()));
        TextView first = (TextView) container.findViewById(R.id.tvFirst);
        TextView firstSubject = (TextView) container.findViewById(R.id.tvFirstSubject);
        TextView firstRoom = (TextView) container.findViewById(R.id.tvFirstRoom);

        TextView second = (TextView) container.findViewById(R.id.tvSecond);
        TextView secondSubject = (TextView) container.findViewById(R.id.tvSecondSubject);
        TextView secondRoom = (TextView) container.findViewById(R.id.tvSecondRoom);

        TextView third = (TextView) container.findViewById(R.id.tvThird);
        TextView thirdSubject = (TextView) container.findViewById(R.id.tvThirdSubject);
        TextView thirdRoom = (TextView) container.findViewById(R.id.tvThirdRoom);

        TextView fourth = (TextView) container.findViewById(R.id.tvFourth);
        TextView fourthSubject = (TextView) container.findViewById(R.id.tvFourthSubject);
        TextView fourthRoom = (TextView) container.findViewById(R.id.tvFourthRoom);

        TextView fifth = (TextView) container.findViewById(R.id.tvFifth);
        TextView fifthSubject = (TextView) container.findViewById(R.id.tvFifthSubject);
        TextView fifthRoom = (TextView) container.findViewById(R.id.tvFifthRoom);

        TextView sixth = (TextView) container.findViewById(R.id.tvSixth);
        TextView sixthSubject = (TextView) container.findViewById(R.id.tvSixthSubject);
        TextView sixthRoom = (TextView) container.findViewById(R.id.tvSixthRoom);

        int i = 1;

        for(Schedule s : d.getSchedules()){
            if(i==1){
                first.setText(s.getStart() + " - " + s.getEnd());
            } else if(i==2){
                second.setText(s.getStart() + " - " + s.getEnd());
            } else if(i==3){
                third.setText(s.getStart() + " - " + s.getEnd());
            } else if(i==4){
                fourth.setText(s.getStart() + " - " + s.getEnd());
            } else if(i==5){
                fifth.setText(s.getStart() + " - " + s.getEnd());
            } else if(i==6){
                sixth.setText(s.getStart() + " - " + s.getEnd());
            }
            if(!d.isHoledayempty()){
                for(Subject sub : s.getSubjects()){

                    if(i==1 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(firstSubject.getText().length()>0){
                            firstSubject.setText(firstSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            firstRoom.setText(firstRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            firstSubject.setText(sub.getName() + " " + sub.getProf());
                            firstRoom.setText(sub.getRoom());
                        }
                    }

                    if(i==2 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(secondSubject.getText().length()>0){
                            secondSubject.setText(secondSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            secondRoom.setText(secondRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            secondSubject.setText(sub.getName() + " " + sub.getProf());
                            secondRoom.setText(sub.getRoom());
                        }
                    }

                    if(i==3 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(thirdSubject.getText().length()>0){
                            thirdSubject.setText(thirdSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            thirdRoom.setText(thirdRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            thirdSubject.setText(sub.getName() + " " + sub.getProf());
                            thirdRoom.setText(sub.getRoom());
                        }
                    }

                    if(i==4 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(fourthSubject.getText().length()>0){
                            fourthSubject.setText(fourthSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            fourthRoom.setText(fourthRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            fourthSubject.setText(sub.getName() + " " + sub.getProf());
                            fourthRoom.setText(sub.getRoom());
                        }
                    }

                    if(i==5 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(fifthSubject.getText().length()>0){
                            fifthSubject.setText(fifthSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            fifthRoom.setText(fifthRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            fifthSubject.setText(sub.getName() + " " + sub.getProf());
                            fifthRoom.setText(sub.getRoom());
                        }
                    }

                    if(i==6 && !s.isEmpty() && (sub.getGroup().equals("") || sub.getGroup().equals(groupInformatic) || sub.getGroup().equals(groupProgramming))){
                        if(sixthSubject.getText().length()>0){
                            sixthSubject.setText(sixthSubject.getText() + "\n" + sub.getName() + " " + sub.getProf());
                            sixthRoom.setText(sixthRoom.getText() + "\n" + sub.getRoom());
                        } else {
                            sixthSubject.setText(sub.getName() + " " + sub.getProf());
                            sixthRoom.setText(sub.getRoom());
                        }
                    }
                }
            }
            i++;
        }

        container.refreshDrawableState();

    }

    private void showErrorSunday(){
        Toast.makeText(this,getResources().getString(R.string.errorsunday),Toast.LENGTH_SHORT).show();
    }

    private void showDefaultError(){
        Toast.makeText(this,getResources().getString(R.string.errordefault),Toast.LENGTH_SHORT).show();
    }

    private void showToday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY: showMonday();
                break;
            case Calendar.TUESDAY: showTuesday();
                break;
            case Calendar.WEDNESDAY: showWednesday();
                break;
            case Calendar.THURSDAY: showThursday();
                break;
            case Calendar.FRIDAY: showFriday();
                break;
            case Calendar.SATURDAY: showErrorSunday();
                break;
            default: showDefaultError();
        }
    }

    private boolean checkSpinnerValues(){
        boolean error = false;
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        Spinner spinnerChooseCourse = (Spinner) container.findViewById(R.id.spinnerChooseCourse);
        Spinner spinnerChooseSemester = (Spinner) container.findViewById(R.id.spinnerChooseSemester);
        Spinner spinnerChooseInf = (Spinner) container.findViewById(R.id.spinnerChooseInf);
        Spinner spinnerChooseProgn = (Spinner) container.findViewById(R.id.spinnerChooseProgn);

        if(spinnerChooseCourse.getSelectedItem().toString().equals("")){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorrequired),Toast.LENGTH_SHORT).show();
            error = true;
        } else if(spinnerChooseSemester.getSelectedItem().toString().equals("")){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorrequired),Toast.LENGTH_SHORT).show();
            error = true;
        } else if(spinnerChooseInf.getSelectedItem().toString().equals("")){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorrequired),Toast.LENGTH_SHORT).show();
            error = true;
        } else if(spinnerChooseProgn.getSelectedItem().toString().equals("")){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorrequired),Toast.LENGTH_SHORT).show();
            error = true;
        }
        if(!error){
            saveSpinnerValues(spinnerChooseCourse, spinnerChooseSemester, spinnerChooseInf, spinnerChooseProgn);
        }
        return error;
    }

    private void saveSpinnerValues(Spinner sCourse, Spinner sSemester, Spinner sInf, Spinner sProgn){
        String strCourse, strSemester, strInf, strProgn;
        strCourse = translateCourse(sCourse.getSelectedItem().toString());
        strSemester = translateSemester(sSemester.getSelectedItem().toString());
        strInf = getResources().getString(R.string.informatic_prefix) + translateGroup(sInf.getSelectedItem().toString());
        strProgn = getResources().getString(R.string.programming_prefix) + translateGroup(sProgn.getSelectedItem().toString());

        writeSharedPreferences(strCourse, strSemester, strInf, strProgn);
    }

    private String translateCourse(String value){
        String result = "";

        if(value.equals(getResources().getString(R.string.its_long))){
            result = getResources().getString(R.string.its_short);
        } else if(value.equals(getResources().getString(R.string.ti_long))){
            result = getResources().getString(R.string.ti_short);
        } else if(value.equals(getResources().getString(R.string.win_long))){
            result = getResources().getString(R.string.win_short);
        }
        return result;
    }

    private String translateSemester(String value){
        String result = "";
        if(value.equals(getResources().getString(R.string.semesterone_long))){
            result = getResources().getString(R.string.semesterone_short);
        }
        return result;
    }

    private String translateGroup(String value){
        String result = "";
        if(value.equals(getResources().getString(R.string.groupone_long))){
            result = getResources().getString(R.string.groupone_short);
        } else if(value.equals(getResources().getString(R.string.grouptwo_long))){
            result = getResources().getString(R.string.grouptwo_short);
        } else if(value.equals(getResources().getString(R.string.groupthree_long))){
            result = getResources().getString(R.string.groupthree_short);
        } else if(value.equals(getResources().getString(R.string.groupfour_long))){
            result = getResources().getString(R.string.groupfour_short);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnMonday){
            showMonday();
        } else if(v.getId()==R.id.btnTuesday){
            showTuesday();
        } else if(v.getId()==R.id.btnWednesday){
            showWednesday();
        } else if(v.getId()==R.id.btnThursday){
            showThursday();
        } else if(v.getId()==R.id.btnFriday){
            showFriday();
        } else if(v.getId()==R.id.btnSaturday){
            showSaturday();
        } else if(v.getId()==R.id.btnToday){
            showToday();
        } else if(v.getId()==R.id.btnNext){
            if(!checkSpinnerValues()){
                showDaysFragment();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String settingsfilled = preferences.getString("settingsfilled", "");
        if(settingsfilled.equalsIgnoreCase("")){
            showMainFragment();
        } else{
            showDaysFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void parseXML() {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLHandler myXMLHandler = new XMLHandler();
            xr.setContentHandler(myXMLHandler);
            String ret = "";
            try {
                InputStream inputStream = getAssets().open("erstsemester.xml");

                if ( inputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        stringBuilder.append(receiveString);
                    }

                    inputStream.close();
                    ret = stringBuilder.toString();
                }
            }
            catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
            } catch (IOException e) {
                Log.e("login activity", "Can not read file: " + e.toString());
            }


            InputSource inStream = new InputSource();
            inStream.setCharacterStream(new StringReader(ret));
            xr.parse(inStream);

            courses = myXMLHandler.getCourseList();
        }
        catch (Exception e) {
            Log.e("Error", e.getStackTrace().toString());
        }

    }

    @Override
    public void onBackPressed() {
        ViewGroup vg = (ViewGroup) this.getWindow().findViewById(R.id.container);
        if(vg.getChildAt(0).getId() == R.id.layout_timer){
            showDaysFragment();
        } else if(vg.getChildAt(0).getId() == R.id.layout_days){
            super.onBackPressed();
        } else if(vg.getChildAt(0).getId() == R.id.layout_main) {
            super.onBackPressed();
        }
    }
}
