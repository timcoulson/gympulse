package com.autodidact.gympulse;

/**
 * Created by timcoulson on 04/12/14.
 */
import android.content.Context;

import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Plan;
import com.autodidact.gympulse.entity.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GymPulse extends android.app.Application {

    private static GymPulse instance;

    // Initialise with Stronglifts
    private static Exercise squat = new Exercise("squat", 3, 5, 90,100);
    private static Exercise deadlift = new Exercise("deadlift", 3, 1, 90,100);
    private static Exercise bench = new Exercise("bench", 3, 5, 90,60);
    private static Exercise shoulderPress = new Exercise("shoulder press", 3, 5, 90,35);
    private static Exercise bentOverRow = new Exercise("bent-over row", 3, 5, 90,60);

    private static List<Exercise> exercisesA = Arrays.asList(squat, bench, bentOverRow);
    private static List<Exercise> exercisesB = Arrays.asList(squat, shoulderPress, deadlift);

    private static Session sessionA = new Session("session A", exercisesA);
    private static Session sessionB = new Session("session B", exercisesB);

    private static List<Session> sessions = Arrays.asList(sessionA, sessionB);
    private static List<Session> loggedSessions = new ArrayList<Session>();


    private static Plan plan = new Plan(sessions);

    public static void saveSession(Session session){
        Calendar cal = Calendar.getInstance();
        session.setDate();
        loggedSessions.add(session);
        session.clearSession();
    }

    public static List<Map<String, String>> getSessionLogList() {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy aaa");

        final List<Map<String, String>> listItem = new ArrayList<Map<String,String>>(loggedSessions.size());
        for(final Session s: loggedSessions){
            final Map<String,String> listItemMap = new HashMap<String, String>();
            listItemMap.put("sessionName",s.getName());
            listItemMap.put("timestamp",df.format(s.getDate()));
            listItem.add(Collections.unmodifiableMap(listItemMap));
        }



// Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String reportDate = df.format(today);

        return Collections.unmodifiableList(listItem);
    }

    public GymPulse() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static Plan getPlan(){
        return plan;
    }
}
