package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class UnTimeOutCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#untimeout".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 1)
                return;

            StringBuilder builder = new StringBuilder();
            for(int i = 2; i < args.size(); i++)
                builder.append(" ").append(args.get(i));

            Main.client.getChat().sendMessage(Main.twitchChatConfig.getString("channel"), String.format("/untimeout %s %s", args.get(1), builder.toString().trim()));

            String message = Main.languageConfig.getString("untimeout_success");

            if(message == null || message.equals(""))
                return;

            Main.sendMessageOnlyToClient(String.format(message, args.get(1), builder.toString().trim()));
        }
    }
}
