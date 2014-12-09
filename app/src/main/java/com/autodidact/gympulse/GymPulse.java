package com.autodidact.gympulse;

/**
 * Created by timcoulson on 04/12/14.
 */
import android.content.Context;

import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Plan;
import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.util.ObjectCloner;

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

import static com.autodidact.gympulse.util.InternalStorage.readObject;
import static com.autodidact.gympulse.util.InternalStorage.writeObject;

public class GymPulse extends android.app.Application {

   private static GymPulse instance;

    // Initialise with Stronglifts
    private static Exercise squat = new Exercise("squat", 3, 5, 90,100,0);
    private static Exercise deadlift = new Exercise("deadlift", 3, 1, 90,100,0);
    private static Exercise bench = new Exercise("bench", 3, 5, 90,60,0);
    private static Exercise shoulderPress = new Exercise("press", 3, 5, 90,35,0);
    private static Exercise bentOverRow = new Exercise("BO row", 3, 5, 90,60,0);

    private static ArrayList<Exercise> exercisesA = new ArrayList<Exercise>(Arrays.asList(squat, bench, bentOverRow));
    private static ArrayList<Exercise> exercisesB = new ArrayList<Exercise>(Arrays.asList(squat, shoulderPress, deadlift));

    private static Session sessionA = new Session("session A", exercisesA);
    private static Session sessionB = new Session("session B", exercisesB);

    private static ArrayList<Session> sessions = new ArrayList<Session>(Arrays.asList(sessionA, sessionB));
    private static ArrayList<Session> loggedSessions = new ArrayList<Session>();

    private static Plan plan = new Plan();


    public void restoreState(){

    }

    public static void initDB(Context context) {
        // Read sessions from history, if they don't exit, generate them.
        try {
            GymPulse.setLoggedSessions((ArrayList<Session>) readObject(context, "loggedSessions"));
            if(GymPulse.getLoggedSessions()==null){
                ArrayList<Session> loggedSessions = new ArrayList<Session>();
                writeObject(context, "loggedSessions", loggedSessions);
                GymPulse.setLoggedSessions(loggedSessions);
            }
        } catch (Exception e) {

        }
        // Read sessions from history, if they don't exit, generate them.
        try {
            GymPulse.setPlan((Plan) readObject(context, "plan"));
            if(GymPulse.getPlan()==null){
                Plan plan = new Plan();
                writeObject(context, "plan", plan);
                GymPulse.setPlan(plan);
            }
        } catch (Exception e) {

        }
    }

    public static void persistDB(Context context) {
        // Read sessions from history, if they don't exit, generate them.
        try {
            writeObject(context, "loggedSessions", GymPulse.getLoggedSessions());
            GymPulse.setLoggedSessions(loggedSessions);

        } catch (Exception e) {

        }
        // Read sessions from history, if they don't exit, generate them.
        try {
                writeObject(context, "plan", GymPulse.getPlan());
            }
         catch (Exception e) {

        }
    }

    public static void saveSession(Session session){
        // TODO this is not a great implementation...
        for(Exercise exercise : session.getExercises()){
            for(int set = 0; set < exercise.getSets() ; set++ ){
                if(exercise.getLoggedReps(set)<0){
                    exercise.resetLoggedReps(set);
                }
            }
        }

        Session sessionClone = new Session();

        try {
            sessionClone = (Session) (ObjectCloner.deepCopy(session));
        } catch(Exception e){
            //TODO handle this
            System.out.println("Error");
        }

        sessionClone.setDate();

        loggedSessions.add(sessionClone);



        session.clearSession();
    }

    public static Session getSession(int listPosition) {
        return loggedSessions.get(listPosition);
    }

    public static Session getSessionFromString(String name) {

        Session session = plan.getSessionFromName(name);
        return session;
    }

    public static void setLoggedSessions(ArrayList<Session> ls){
        loggedSessions = ls;
    }

    public static ArrayList<Session> getLoggedSessions(){
        return loggedSessions;
    }

    public static void setPlan(Plan p){
        plan = p;
    }

    public static List<Map<String, String>> getSessionLogList() {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy aaa");

        final List<Map<String, String>> listItem = new ArrayList<Map<String, String>>(loggedSessions.size());
        for (final Session s : loggedSessions) {
            final Map<String, String> listItemMap = new HashMap<String, String>();
            listItemMap.put("sessionName", s.getName());
            listItemMap.put("timestamp", df.format(s.getDate()));
            listItem.add(Collections.unmodifiableMap(listItemMap));
        }

        return Collections.unmodifiableList(listItem);

    }

    public static String[] getSessionList() {
        final String[] sessions = plan.getSessionsArray();
        return sessions;
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
