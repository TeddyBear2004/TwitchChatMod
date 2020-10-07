package de.dreamtale.teddy.twitch.chat;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.core.EventManager;
import com.github.philippheuer.events4j.reactor.ReactorEventHandler;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import de.dreamtale.teddy.twitch.chat.config.FileConfig;
import de.dreamtale.teddy.twitch.chat.minecraft.event.*;
import de.dreamtale.teddy.twitch.chat.twitch.TwitchEventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod("twitchchat")
public class Main {
    public static TwitchClient client;
    public static FileConfig twitchChatConfig = new FileConfig("twitchChatConfig");
    public static FileConfig languageConfig;

    public Main(){
        EventManager eventManager = new EventManager();
        twitchChatConfig.setDefault("clientId", "CLIENT_ID");
        twitchChatConfig.setDefault("clientSecret", "CLIENT_SECRET");
        twitchChatConfig.setDefault("accessToken", "ACCESS_TOKEN");
        twitchChatConfig.setDefault("channel", "CHANNEL_TO_JOIN");
        twitchChatConfig.setDefault("toIgnore", new String[]{"exampleUser1", "exampleUser2"});
        twitchChatConfig.setDefault("language", "lang/en_twitch");
        twitchChatConfig.save();

        languageConfig = new FileConfig(Objects.requireNonNull(twitchChatConfig.getString("language")));
        languageConfig.setDefault("ban_success", "You have banned %s with the reason: %s");
        languageConfig.setDefault("help", "#twitch [Message]\n" +
                "#help\n" +
                "#reload\n" +
                "#ignore [User]\n" +
                "#unignore [User]\n" +
                "#timeout [User] {Reason}\n" +
                "#ban [User] {Reason}\n" +
                "#unban [User]\n");
        languageConfig.setDefault("ignore_success", "Added %s to the ignore list.");
        languageConfig.setDefault("reload_success", "Reload complete!");
        languageConfig.setDefault("receive_message", "$0[$5Twitch$0] $8%User%$0: $7%Message%");
        languageConfig.setDefault("timeout_success", "You have timeout %s for %d seconds with the reason: %s");
        languageConfig.setDefault("unban_success", "You have unbanned %s");
        languageConfig.setDefault("unignore_success", "Removed %s from the ignore list.");
        languageConfig.setDefault("untimeout_success", "You have removed the timeout from %s with the reason: %s");
        languageConfig.save();


        eventManager.registerEventHandler(new ReactorEventHandler());
        eventManager.autoDiscovery();
        eventManager.setDefaultEventHandler(SimpleEventHandler.class);
        eventManager.getEventHandler(SimpleEventHandler.class).registerListener(new TwitchEventListener());

        client = TwitchClientBuilder.builder()
                .withClientId(twitchChatConfig.getString("clientId"))
                .withClientSecret(twitchChatConfig.getString("clientSecret"))
                .withChatAccount(new OAuth2Credential("twitch", Objects.requireNonNull(twitchChatConfig.getString("accessToken"))))
                .withEnableChat(true)
                .withEventManager(eventManager)
                .build();

        client.getChat().joinChannel(twitchChatConfig.getString("channel"));

        MinecraftForge.EVENT_BUS.register(new ReloadCommand());
        MinecraftForge.EVENT_BUS.register(new IgnoreCommand());
        MinecraftForge.EVENT_BUS.register(new UnIgnoreCommand());
        MinecraftForge.EVENT_BUS.register(new TCommand());
        MinecraftForge.EVENT_BUS.register(new TimeOutCommand());
        MinecraftForge.EVENT_BUS.register(new BanCommand());
        MinecraftForge.EVENT_BUS.register(new UnBanCommand());
        MinecraftForge.EVENT_BUS.register(new UnTimeOutCommand());
        MinecraftForge.EVENT_BUS.register(new HelpCommand());
    }

    public static void sendMessageOnlyToClient(String s){
        ITextComponent m = new StringTextComponent(s);

        assert Minecraft.getInstance().player != null;

        Minecraft.getInstance().player.sendMessage(m, PlayerEntity.getUUID(Minecraft.getInstance().player.getGameProfile()));
    }
}
