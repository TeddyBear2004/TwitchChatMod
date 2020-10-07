package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UnBanCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#unban".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 1)
                return;

            Main.client.getChat().unban(Main.twitchChatConfig.getString("channel"), args.get(1));

            Main.sendMessageOnlyToClient(String.format(Objects.requireNonNull(Main.languageConfig.getString("unban_success")), args.get(1)));

        }
    }
}
