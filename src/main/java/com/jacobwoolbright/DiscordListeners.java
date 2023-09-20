package com.jacobwoolbright;

import com.sun.istack.internal.NotNull;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class DiscordListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(event.getAuthor().getId().equals("1143310558632886404")){
            return;
        }

        String message = event.getMessage().getContentDisplay();

        if(message.startsWith("!status ")){

        } else if (message.startsWith("!notify")) {
            String[] split = message.split(" ");
            NotificationManager.createNotification(split[1], event.getMessage());
            event.getMessage().reply("I will notify you about machine #" + split[1]).queue();
        }
        else if(message.startsWith("!help")){
//            event.getMessage().reply("!status (machineId) --> Gives status of the provided machine\n!bulk --> gives the status of all machines").queue();
        }
        else if(message.startsWith("!graph")){
            String[] split = message.split(" ");
            if(split[1].equals("washer") || split[1].equals("wash")){
                if(split.length == 3){
                    GraphMaker.generateLineGraphWashers(split[2]);
                } else if (split.length == 4) {
                    GraphMaker.generateLineGraphWashers(split[2], split[3]);
                }
                else{
                    GraphMaker.generateLineGraphWashers();
                }
            } else if (split[1].equals("dryer") || split[1].equals("dry")) {
                if(split.length == 3){
                    GraphMaker.generateLineGraphDryers(split[2]);
                } else if (split.length == 4) {
                    GraphMaker.generateLineGraphDryers(split[2], split[3]);
                }
                else{
                    GraphMaker.generateLineGraphDryers();
                }
            }
            else{
                event.getMessage().reply("Please use !graph washer or !graph dryer.").queue();
                return;
            }
            event.getMessage().reply("Here's your graph:").addFiles(FileUpload.fromData(new File("time_line_graph.png"))).queue();
        } else if (message.startsWith("!test")) {
            FilterGraph.getGraphPointsDryer();
        }
    }

    @Override
    public void onReady(@org.jetbrains.annotations.NotNull ReadyEvent event) {

        Message message = DiscordUtils.getMessageById("1143904083648979006","1143900612875010148");

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                message.editMessageEmbeds(BulkEmbed.StatusEmbed()).queue();
            }
        }, 3 * 60 * 1000, 3 * 60 * 1000); // 3 minutes in milliseconds
    }
}
