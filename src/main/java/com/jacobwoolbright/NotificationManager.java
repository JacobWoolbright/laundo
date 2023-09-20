package com.jacobwoolbright;

import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.Objects;

public class NotificationManager {

    private static ArrayList<Notification> notifications = new ArrayList<>();

    private static void addNotification(Notification notification){
        notifications.add(notification);
    }

    public static void createNotification(String machineID, Message message){
        addNotification(new Notification(machineID, message));
    }

    public static void removeNotification(Notification notification){
        notifications.remove(notification);
    }

    public static void checkNotification(Status status){

        ArrayList<Notification> finishedNotifications = new ArrayList<>();

        for(Notification noti : notifications){
            if(noti.getMachineID().equals(status.getMachineID())){
                if (status.getTimeLeft() <= 5) {
                    noti.getMessage().reply("machine #" + noti.getMachineID() + " has < 5 minutes!").queue();
                    finishedNotifications.add(noti);
                } else if (status.getTimeLeft() <= 10) {
                    if (noti.getNotiAmount() >= 2) {
                        noti.setNotiAmount(1);
                        noti.getMessage().reply("machine #" + noti.getMachineID() + " has < 10 minutes!").queue();
                    }
                } else if (status.getTimeLeft() <= 15) {
                    if (noti.getNotiAmount() == 3) {
                        noti.getMessage().reply("machine #" + noti.getMachineID() + " has < 15 minutes!").queue();
                    }
                }
            }
        }
        for(Notification noti : finishedNotifications){
            removeNotification(noti);
        }
    }
}
