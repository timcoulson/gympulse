package com.autodidact.gympulse.entity;

import java.util.List;

/**
 * Created by timcoulson on 04/12/14.
 */
public class Plan {
    static List<Session> sessions;

    public static List<Session> getSessions() {
        return sessions;
    }

    public static void setSessions(List<Session> sessions) {
        Plan.sessions = sessions;
    }
}
