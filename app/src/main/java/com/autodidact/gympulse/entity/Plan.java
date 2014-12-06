package com.autodidact.gympulse.entity;

import java.io.Serializable;
import java.util.List;
import com.autodidact.gympulse.GymPulse;
/**
 * Created by timcoulson on 04/12/14.
 */
public class Plan implements Serializable{
    private List<Session> sessions;
    private int currentSessionIndex;

    public Plan(List<Session> sessions){
        this.sessions = sessions;
        this.currentSessionIndex = 0;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
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

    public Session getCurrentSession() {
        return sessions.get(currentSessionIndex);
    }

    public String getCurrentSessionName() {
        return sessions.get(currentSessionIndex).getName();
    }
}
