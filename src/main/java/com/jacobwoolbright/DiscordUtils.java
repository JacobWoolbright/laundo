package com.jacobwoolbright;

import net.dv8tion.jda.api.entities.Message;

public class DiscordUtils {

    public static Message getMessageById(String messageId, String channelID) {
        return DiscordBotManager.getInstance().getJda().getTextChannelById(channelID).retrieveMessageById(messageId).complete();
    }

}
