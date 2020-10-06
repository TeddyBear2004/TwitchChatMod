package de.dreamtale.teddy.twitch.chat.minecraft.event;

import de.dreamtale.teddy.twitch.chat.Main;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HelpCommand {
    @SubscribeEvent
    public void onSendEvent(ClientChatEvent event){
        if(event.getMessage().toLowerCase().startsWith("#help".toLowerCase())){
            event.setCanceled(true);

            StringBuilder builder = new StringBuilder();

            builder.append("#twitch [Message]").append("\n");
            builder.append("#help").append("\n");
            builder.append("#reload").append("\n");
            builder.append("#ignore [User]").append("\n");
            builder.append("#unignore [User]").append("\n");
            builder.append("#timeout [User] [duration[seconds]] {Reason}").append("\n");
            builder.append("#untimeout [User] {Reason}").append("\n");
            builder.append("#ban [User] {Reason}").append("\n");
            builder.append("#unban [User]");
            Main.sendMessageOnlyToClient(builder.toString());
        }
    }
}
