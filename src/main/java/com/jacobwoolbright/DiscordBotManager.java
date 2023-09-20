package com.jacobwoolbright;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

public class DiscordBotManager {

    public static DiscordBotManager instance;

    private static JDA jda;

    private DiscordBotManager(){
        jda = JDABuilder.createDefault("MTE0MzMxMDU1ODYzMjg4NjQwNA.Gax9SF.h43tSmy2fyhAnZm2tnUgE3aC8GS-tVKXiwkeDs").setActivity(Activity.watching("Couch Laundry Room")).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
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
