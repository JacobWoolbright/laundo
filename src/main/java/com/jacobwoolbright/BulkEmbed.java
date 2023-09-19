package com.jacobwoolbright;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class BulkEmbed {

    public static MessageEmbed StatusEmbed(){

        ArrayList<Status> statuses = CheckStatus.checkAll();

        statuses.sort(Comparator.comparing(Status::getMachineID));

        int openWashers = 0;
        int openDryers = 0;
        int closedWashers = 0;
        int closedDryers = 0;
        String washers = "";
        String dryers = "";
        for(Status status: statuses){
            if(status.isAvailable()){
                if(status.isDryer()){
                    openDryers += 1;
                }
                else{
                    openWashers += 1;
                }
            }
            else{
                if(status.isDryer()){
                    closedDryers += 1;
                }
                else{
                    closedWashers += 1;
                }
            }
            if(!status.isAvailable() & status.isRunning()){
                if(status.isDryer()){
                    dryers += status.getMachineID() + ": " + status.getTimeLeft() + "min\n";
                }
                else{
                    washers += status.getMachineID() + ": " + status.getTimeLeft() + "min\n";
                }
            }
        }

        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Laundry Status");

        eb.setColor(Color.blue);

        eb.addField("Open Washers: " + openWashers, washers, true);
        eb.addField("Open Dryers: " + openDryers, dryers, true);

        eb.setAuthor("Jacob Woolbright");

        return eb.build();
    }

}
