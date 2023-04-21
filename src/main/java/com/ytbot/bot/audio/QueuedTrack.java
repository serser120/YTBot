/*
 * Copyright 2021 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ytbot.bot.audio;

import com.ytbot.bot.queue.Queueable;
import com.ytbot.bot.utils.FormatUtil;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.User;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class QueuedTrack implements Queueable
{
    private final AudioTrack track;
    
    public QueuedTrack(AudioTrack track, User owner)
    {
        this(track, new RequestMetadata(owner));
    }
    
    public QueuedTrack(AudioTrack track, RequestMetadata rm)
    {
        this.track = track;
        this.track.setUserData(rm);
    }
    
    @Override
    public long getIdentifier() 
    {
        return track.getUserData(RequestMetadata.class).getOwner();
    }
    
    public AudioTrack getTrack()
    {
        return track;
    }

    @Override
    public String toString() 
    {
        String entry = "`[" + FormatUtil.formatTime(track.getDuration()) + "]` ";
        AudioTrackInfo trackInfo = track.getInfo();
        entry = entry + (trackInfo.uri.startsWith("http") ? "[**" + trackInfo.title + "**]("+trackInfo.uri+")" : "**" + trackInfo.title + "**");
        return entry + " - <@" + track.getUserData(RequestMetadata.class).getOwner() + ">";
    }
}