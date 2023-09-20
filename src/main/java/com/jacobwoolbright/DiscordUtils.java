package com.jacobwoolbright;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordUtils {

    public static Message getMessageById(String messageId, String channelID) {
        return DiscordBotManager.getInstance().getJda().getTextChannelById(channelID).retrieveMessageById(messageId).complete();
    }

    public static TextChannel getChannelByID(String channelID){
        return DiscordBotManager.getInstance().getJda().getTextChannelById(channelID);
    }

    public static void sendMessage(String channelID, String message){
        getChannelByID(channelID).sendMessage(message).queue();
    }

}
