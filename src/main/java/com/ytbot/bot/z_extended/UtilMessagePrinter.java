package com.ytbot.bot.z_extended;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

//my personal settings to send chat messages
public class UtilMessagePrinter extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        CommandExpander.commandFinder(event);
        //muteIfMassage(event);
    }

    //Мутит за любое сообщение в чат
    public static void muteIfMassage(MessageReceivedEvent event) {
        if (!event.getAuthor().getName().equals("serser120") &&
                !event.getAuthor().getName().equals("Volodya")
                && !event.getAuthor().isBot()) {
            event.getGuild().getDefaultChannel().sendMessage("Ты, заткнись").submit();
//            event.getJDA().getCategories().forEach(category -> {
//                category.getTextChannels()
//                        .forEach(textChannel -> textChannel.sendMessage("Ты, заткнись").submit());
//            });
            //event.getMember().mute(true).timeout(5, TimeUnit.SECONDS).submit();
        }
    }

    //Приветствие при старте
    public static void greetingsMessage(JDA jda) throws InterruptedException {
        jda.awaitReady().getCategories().get(1).getTextChannels().get(0).sendMessage("Стартуем")
                .timeout(5, TimeUnit.SECONDS)
                .submit();
    }

//    //Печать сообщения
//    public static void printerMessage(MessageReceivedEvent event) {
//        if (CommandExpander.commandFinder(event)) {
//            event.getJDA().getCategories().forEach(category -> {
//                category.getTextChannels()
//                        .forEach(textChannel -> textChannel.sendMessage(CommandExpander.getTrackInfo()).submit());
//            });
//            event.getMember().mute(true).timeout(5, TimeUnit.SECONDS).submit();
//        }
//    }

}

