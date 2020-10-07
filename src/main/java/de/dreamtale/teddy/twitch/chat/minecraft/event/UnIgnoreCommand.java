package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UnIgnoreCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#unignore".toLowerCase())  || event.getMessage().toLowerCase().startsWith("#removeignore".toLowerCase())){
            event.setCanceled(true);
            List<String> args = Arrays.asList(event.getMessage().split(" "));

            if(args.size() <= 1)return;

            ArrayList<String> toIgnore = (ArrayList<String>)Main.twitchChatConfig.get("toIgnore");

            assert toIgnore != null;
            toIgnore.remove(args.get(1));

            Main.twitchChatConfig.setObject("toIgnore", toIgnore);

            Main.twitchChatConfig.save();

            String message = Main.languageConfig.getString("unignore_success");

            if(message == null || message.equals(""))
                return;

            Main.sendMessageOnlyToClient(String.format(message, args.get(1)));
        }
    }
}
