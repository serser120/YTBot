package com.ytbot.website.botReader;


import com.ytbot.website.model.Server;
import com.ytbot.website.model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class ServerAndVideoBean {
    private final Connection connection;

    public ServerAndVideoBean(Connection connection) {
        this.connection = connection;
    }


    public void addServerAndVideo(Server newServer, Video newVideo) {
        Long serverMaxId = 0L;
        Long videoMaxId = 0L;
        Long historyMaxId = 0L;
        try {
            PreparedStatement query = connection.prepareStatement("select max(id) from server_video_history");
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("max") == null) historyMaxId = 1L;
                else historyMaxId = Long.parseLong(resultSet.getString("max")) + 1;
            }
//Ищем в БД видео с совпадающим url, если есть, увеличиваем количесвто воспроизведений на 1, иначе добавляем новое, в обоих случаях узнаем id этого видео в БД для добавления в историю
            String selectVideo = "select * from videos where url = ?";
            query = connection.prepareStatement(selectVideo);
            query.setString(1, newVideo.getVideoUrl());
            resultSet = query.executeQuery();

            if (resultSet.next()) {
                String selectPlaysOfVideo = "select number_of_plays from videos where url = ?";
                query = connection.prepareStatement(selectPlaysOfVideo);
                query.setString(1, newVideo.getVideoUrl());
                resultSet = query.executeQuery();
                int updateNumberOfPlays = 0;

                if (resultSet.next()) {
                    String temp = resultSet.getString("number_of_plays");
                    updateNumberOfPlays = Integer.parseInt(temp) + 1;
                }

                String updateVideo = "UPDATE videos SET number_of_plays = ? where url = ?";
                query = connection.prepareStatement(updateVideo);
                query.setInt(1, updateNumberOfPlays);
                query.setString(2, newVideo.getVideoUrl());
                query.execute();

                query = connection.prepareStatement("select id from videos where url = ?");
                query.setString(1, newVideo.getVideoUrl());
                resultSet = query.executeQuery();
                if (resultSet.next()) {
                    String temp = resultSet.getString("id");
                    videoMaxId = Long.parseLong(temp);
                }

            } else {
                query = connection.prepareStatement("select max(id) from videos");
                resultSet = query.executeQuery();
                if (resultSet.next()) {
                    if (resultSet.getString("max") == null) videoMaxId = 1L;
                    else videoMaxId = Long.parseLong(resultSet.getString("max")) + 1;
                }
                query = connection.prepareStatement("INSERT INTO videos (id, number_of_plays, length, title, url, you_tube_identifier) VALUES (nextval('video_seq'),1 ,? ,? ,? ,?)");
                query.setString(1, newVideo.getVideoLength());
                query.setString(2, newVideo.getVideoTitle());
                query.setString(3, newVideo.getVideoUrl());
                query.setString(4, newVideo.getYouTubeIdentifier());
                query.execute();
            }

            //Ищем в БД сервер с совпадающим discordID, если есть, обновляем название, иначе добавляем новый, в обоих случаях узнаем id сервера в БД для добавления в историю
            String selectServer = "select * from servers where discord_id = ?";
            query = connection.prepareStatement(selectServer);
            query.setString(1, newServer.getServerDiscordID());
            resultSet = query.executeQuery();

            if (resultSet.next()) {
                String updateServer = "UPDATE servers SET name = ? where discord_id = ?";
                query = connection.prepareStatement(updateServer);
                query.setString(1, newServer.getServerName());
                query.setString(2, newServer.getServerDiscordID());
                query.execute();

                query = connection.prepareStatement("select id from servers where discord_id = ?");
                query.setString(1, newServer.getServerDiscordID());
                resultSet = query.executeQuery();
                if (resultSet.next()) {
                    String temp = resultSet.getString("id");
                    serverMaxId = Long.parseLong(temp);
                }

            } else {
                query = connection.prepareStatement("select max(id) from servers");
                resultSet = query.executeQuery();
                if (resultSet.next()) {
                    if (resultSet.getString("max") == null) serverMaxId = 1L;
                    else serverMaxId = Long.parseLong(resultSet.getString("max")) + 1;
                }
                String insertServer = "INSERT INTO servers (id, discord_id, name) VALUES (nextval('server_seq'), ?, ?)";
                query = connection.prepareStatement(insertServer);
                query.setString(1, newServer.getServerDiscordID());
                query.setString(2, newServer.getServerName());
                query.executeUpdate();
            }
//Добавляем в историю id сервера, id видео и дату
            String insertServerVideoHistory = "INSERT INTO server_video_history (id, server_id, video_id, playback_date) VALUES (?, ?, ?,'" + LocalDateTime.now() + "')";
            query = connection.prepareStatement(insertServerVideoHistory);

            query.setLong(1, historyMaxId);
            query.setLong(2, serverMaxId);
            query.setLong(3, videoMaxId);
            query.executeUpdate();
            connection.close();
        } catch (SQLException ex){
            System.out.println("Ошибка записи в БД!!!!");
        }
    }

}
