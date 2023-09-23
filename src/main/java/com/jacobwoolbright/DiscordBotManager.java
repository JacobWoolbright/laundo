package com.jacobwoolbright;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBotManager {

    public static DiscordBotManager instance;

    private static JDA jda;

    private DiscordBotManager(){
        jda = JDABuilder.createDefault(ConfigReader.getBotToken()).setActivity(Activity.watching("Couch Laundry Room")).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        jda.addEventListener(new DiscordListeners());
    }

    public static DiscordBotManager getInstance() {
        if(instance == null){
            instance = new DiscordBotManager();
        }
        return instance;
    }

    public JDA getJda() {
        return jda;
    }
}
