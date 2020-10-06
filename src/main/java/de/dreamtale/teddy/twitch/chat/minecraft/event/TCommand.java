package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class TCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#twitch".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 1)return;
            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < args.size(); i++)
                builder.append(" ").append(args.get(i));

            Main.client.getChat().sendMessage(Main.twitchChatConfig.getString("channel"), builder.toString().trim());

            String message = Main.twitchChatConfig.getString("message");
            if(message == null)
                return;
            message = message.replaceAll("%User%", "Du");
            message = message.replaceAll("%Message%", builder.toString().trim());
            message = message.replaceAll("\\$", "§");
            message = message.replaceAll(new String(new byte[]{(byte)194}), "");

            Main.sendMessageOnlyToClient(message);


        }
    }
}
