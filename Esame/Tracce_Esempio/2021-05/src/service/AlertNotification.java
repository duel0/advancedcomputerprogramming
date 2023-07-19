package service;

import java.io.Serializable;

public class AlertNotification implements Serializable {
    private int componentID;
    private int criticality;

    public AlertNotification(){
        componentID = (int) (Math.random()*5)+1;
        criticality = (int) (Math.random()*3)+1;
    }

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public int getCriticality() {
        return criticality;
    }

    public void setCriticality(int criticality) {
        this.criticality = criticality;
    }
    
}