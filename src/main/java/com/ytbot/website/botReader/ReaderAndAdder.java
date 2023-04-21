package com.ytbot.website.botReader;

import com.ytbot.website.model.Server;
import com.ytbot.website.model.Video;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public class ReaderAndAdder {
    private Video video = new Video();
    private Server server = new Server();

    public void addVideo(String title, Long length, String url, String identifier) {
        int sec = (int)(length/1000)%60;
        int min = (int)(length/1000/60)%60;
        int hour = (int)(length/1000/60/60);
        String secStr;
        String minStr;
        if (sec<10) secStr = "0" + sec;
        else secStr = "" + sec;
        if (min<10) minStr = "0" + min;
        else minStr = "" + min;
        String time = hour + " : " + minStr + " : " + secStr;
        video.setVideoTitle(title);
        video.setVideoLength(time);
        video.setVideoUrl(url);
        video.setYouTubeIdentifier("https://www.youtube.com/embed/" + identifier);
        if (fieldChecker()) fieldsTransmitter();
    }

    public void addServer(String serverName, Long serverDiscordID) {
        server.setServerName(serverName);
        server.setServerDiscordID(serverDiscordID.toString());
        if (fieldChecker()) fieldsTransmitter();
    }

    public boolean fieldChecker() {
        return video.getVideoUrl() != null && server.getServerDiscordID() != null;
    }

    public void fieldsTransmitter(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfigContext.class);
        ServerAndVideoBean serverAndVideoBean = ctx.getBean(ServerAndVideoBean.class);
        serverAndVideoBean.addServerAndVideo(server, video);
        video = new Video();
        server = new Server();
    }
}
