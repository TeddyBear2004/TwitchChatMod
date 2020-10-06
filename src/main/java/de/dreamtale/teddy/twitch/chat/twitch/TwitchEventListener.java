package de.dreamtale.teddy.twitch.chat.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.dreamtale.teddy.twitch.chat.Main;

import java.util.ArrayList;

public class TwitchEventListener {
    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event){
        ArrayList<String> toIgnore = (ArrayList<String>)Main.twitchChatConfig.get("toIgnore");
        assert toIgnore != null;
        for(String s : toIgnore)
            if(s.equals(event.getUser().getName()))
                return;

        String message = Main.twitchChatConfig.getString("message");
        if(message == null)
            return;
        message = message.replaceAll("%User%", event.getUser().getName());
        message = message.replaceAll("%Message%", event.getMessage());
        message = message.replaceAll("\\$", "§");
        message = message.replaceAll(new String(new byte[]{(byte)194}), "");

        System.out.println(message);
        Main.sendMessageOnlyToClient(message);
    }
}
