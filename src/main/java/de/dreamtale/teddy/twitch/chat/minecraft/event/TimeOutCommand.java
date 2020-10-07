package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TimeOutCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#timeout".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 2)
                return;

            Duration duration;
            try{
                duration = Duration.ofSeconds(Long.parseLong(args.get(2)));
            }catch(NumberFormatException e){
                return;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 3; i < args.size(); i++)
                builder.append(" ").append(args.get(i));

            Main.client.getChat().timeout(Main.twitchChatConfig.getString("channel"), args.get(1), duration, builder.toString().trim());

            String message = Main.languageConfig.getString("timeout_success");

            if(message == null || message.equals(""))
                return;

            Main.sendMessageOnlyToClient(String.format(message, args.get(1), duration.getSeconds(), builder.toString().trim()));

        }
    }
}
