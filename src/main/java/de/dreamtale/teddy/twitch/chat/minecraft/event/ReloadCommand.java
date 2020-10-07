package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ReloadCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#reload".toLowerCase())){
            event.setCanceled(true);
            Main.twitchChatConfig.reload();
            Main.sendMessageOnlyToClient(Objects.requireNonNull(Main.languageConfig.getString("reload_success")));
        }
    }
}
