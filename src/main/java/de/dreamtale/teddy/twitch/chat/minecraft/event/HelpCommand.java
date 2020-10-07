package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HelpCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#help".toLowerCase())){
            event.setCanceled(true);

            String message = Main.languageConfig.getString("help");

            if(message == null || message.equals(""))
                return;

            Main.sendMessageOnlyToClient(message);
        }
    }
}
