package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BanCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#ban".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 1)
                return;

            StringBuilder builder = new StringBuilder();
            for(int i = 2; i < args.size(); i++)
                builder.append(" ").append(args.get(i));


            Main.client.getChat().ban(Main.twitchChatConfig.getString("channel"), args.get(1), builder.toString().trim());

            Main.sendMessageOnlyToClient(String.format(Objects.requireNonNull(Main.languageConfig.getString("ban_success")), args.get(1), builder.toString().trim()));

        }
    }
}
