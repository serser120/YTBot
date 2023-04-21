package com.ytbot.bot.z_extended;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.ytbot.website.botReader.ReaderAndAdder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


//расширение функционала бота в работе с командами
public class CommandExpander extends ListenerAdapter {

    private static ReaderAndAdder readerAndAdder;

    public static boolean commandFinder(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentDisplay();
        if (!content.contains("YTMusic")) return false;
        if (content.contains("play")) {
            event.getMessage().addReaction("\uD83E\uDD1F").queue();
//            Чтобы выдернуть сервер
            serverInfo(event.getGuild());
            return true;
        }
        if (content.contains("stop")) {
            event.getMessage().addReaction("\uD83E\uDD19").queue();
            return true;
        }
        return false;
    }

    private static ReaderAndAdder ReaderAndAdderMaker() {
        if (readerAndAdder == null) readerAndAdder = new ReaderAndAdder();
        return readerAndAdder;
    }

    public static void trackInfo(AudioTrackInfo info) {
        ReaderAndAdderMaker().addVideo(info.title, info.length, info.uri, info.identifier);
    }

    public static void serverInfo(Guild guild) {
        ReaderAndAdderMaker().addServer(guild.getName(), guild.getIdLong());
    }

}
