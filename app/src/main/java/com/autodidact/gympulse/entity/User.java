package com.autodidact.gympulse.entity;

import java.io.Serializable;

/**
 * Created by timcoulson on 04/12/14.
 */
public class User implements Serializable{
    private Plan plan;

    public User(Plan plan){
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
