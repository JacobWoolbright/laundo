package com.jacobwoolbright;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordUtils {

    public static Message getMessageById(String messageId, String channelID) {
        return DiscordBotManager.getJda().getTextChannelById(channelID).retrieveMessageById(messageId).complete();
    }

}
