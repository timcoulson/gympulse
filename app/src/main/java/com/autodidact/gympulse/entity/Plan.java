package com.autodidact.gympulse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.autodidact.gympulse.GymPulse;
/**
 * Created by timcoulson on 04/12/14.
 */
public class Plan implements Serializable{
    private ArrayList<Session> sessions;
    private int currentSessionIndex;

    public Plan(ArrayList<Session> sessions){
        this.sessions = sessions;
        this.currentSessionIndex = 0;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public ArrayList<String> getSessionsString(){
        ArrayList<String> sessionsString = new ArrayList<String>();
        for(Session session: sessions){
            sessionsString.add(session.getName());
        }
        return sessionsString;
    }

    public String[] getSessionsArray(){
        String[] sessionsString = new String[sessions.size()];
        int i = 0;
        for(Session session: sessions){
            sessionsString[i] = session.getName();
            i++;
        }
        return sessionsString;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public Session skipSession() {
        GymPulse.saveSession(getCurrentSession());
        return nextSession();
    }

    public Session nextSession() {
        if(currentSessionIndex == sessions.size() - 1){
            this.currentSessionIndex = 0;
        } else {
            currentSessionIndex++;
        }
        return getCurrentSession();
    }

    public Session getSessionFromName(String name) {
        for (Session s : sessions) {
            if (s.getName().equals(name)) {

                return s;
            }
        }
        return null;
    }

    public Session getCurrentSession() {
        return sessions.get(currentSessionIndex);
    }

    public Plan(){
        // Initialise with Stronglifts
        Exercise squat = new Exercise("squat", 3, 5, 90,100,0);
        Exercise squat2 = new Exercise("squat", 3, 5, 90,100,0);

        Exercise deadlift = new Exercise("deadlift", 3, 1, 90,100,0);
        Exercise bench = new Exercise("bench", 3, 5, 90,60,0);
        Exercise shoulderPress = new Exercise("press", 3, 5, 90,35,0);
        Exercise bentOverRow = new Exercise("BO row", 3, 5, 90,60,0);

        ArrayList<Exercise> exercisesA = new ArrayList<Exercise>(Arrays.asList(squat, bench, bentOverRow));
        ArrayList<Exercise> exercisesB = new ArrayList<Exercise>(Arrays.asList(squat2, shoulderPress, deadlift));

        Session sessionA = new Session("session A", exercisesA);
        Session sessionB = new Session("session B", exercisesB);

        this.sessions = new ArrayList<Session>(Arrays.asList(sessionA, sessionB));
        this.currentSessionIndex = 0;


    }
    public String getCurrentSessionName() {
        return sessions.get(currentSessionIndex).getName();
    }
}
