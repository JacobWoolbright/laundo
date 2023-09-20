package com.jacobwoolbright;

import net.dv8tion.jda.api.entities.Message;

public class Notification {

    private final String machineID;
    private final Message message;
    private int notiAmount = 3;

    public Notification(String machineID, Message message){
        this.machineID = machineID;
        this.message = message;
    }

    public String getMachineID() {
        return machineID;
    }

    public Message getMessage() {
        return message;
    }

    public int getNotiAmount() {
        return notiAmount;
    }

    public void setNotiAmount(int notiAmount) {
        this.notiAmount = notiAmount;
    }
}
