package com.jacobwoolbright;

public class Status {

    private String statusMessage;
    private int timeLeft;
    private boolean isRunning;
    private boolean isAvailable;
    private final String machineID;

    public Status(String machineID) {
        this.machineID = machineID;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getMachineID() {
        return machineID;
    }

    public boolean isDryer(){
        return Integer.parseInt(machineID) > 100;
    }

    public boolean isWasher(){
        return Integer.parseInt(machineID) < 100;
    }

    public String getStatusString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ").append(this.statusMessage);
        sb.append("\n");
        if(this.isRunning){
            sb.append("Time left: ").append(this.timeLeft);
        }
        return sb.toString();


    }
}
