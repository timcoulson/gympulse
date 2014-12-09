package com.autodidact.gympulse;

/**
 * Created by timcoulson on 04/12/14.
 */
import android.content.Context;
import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Plan;
import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.util.ObjectCloner;
import static com.autodidact.gympulse.util.InternalStorage.readObject;
import static com.autodidact.gympulse.util.InternalStorage.writeObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GymPulseModel extends android.app.Application {

    private static GymPulseModel instance;
    private static ArrayList<Session> loggedSessions = new ArrayList<Session>();
    private static Plan plan;

    public static void initDB(Context context) {
        try {
            GymPulseModel.setLoggedSessions((ArrayList<Session>) readObject(context, "loggedSessions"));
            GymPulseModel.setPlan((Plan) readObject(context, "plan"));
        } catch (Exception readError) {
            ArrayList<Session> loggedSessions = new ArrayList<Session>();
            GymPulseModel.setLoggedSessions(loggedSessions);
            Plan plan = new Plan();
            GymPulseModel.setPlan(plan);
            try {
                writeObject(context, "loggedSessions", loggedSessions);
                writeObject(context, "plan", plan);
            } catch (Exception writeError){

            }
        }
    }

    public static void persistDB(Context context) {
        try {
            writeObject(context, "loggedSessions", GymPulseModel.getLoggedSessions());
            writeObject(context, "plan", GymPulseModel.getPlan());
            GymPulseModel.setLoggedSessions(loggedSessions);
        } catch (Exception e) {
        }
    }

    public static void saveSession(Session session, Context context){
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
        }
        sessionClone.setDate();
        loggedSessions.add(sessionClone);
        session.clearSession();
        plan.nextSession();
        GymPulseModel.persistDB(context);
    }

    public static Session getSession(int listPosition) {
        return loggedSessions.get(listPosition);
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

    public static String skipSession(Context context) {
        Session nextSession = plan.nextSession();
        persistDB(context);
        return nextSession.getName();
    }

    public GymPulseModel() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static Plan getPlan(){
        return plan;
    }

}
