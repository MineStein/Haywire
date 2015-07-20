package project.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import project.rank.RankManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ****************************************************************************************
 * Copyright Tyler Grissom © 2013-2015
 * <p>
 * Any code contained within this document, and any associated API's with similar branding
 * are the sole property of Tyler Grissom. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * <p>
 * Thanks, and have a nice day.
 * ****************************************************************************************
 */
public class ChatListener implements Listener {

    private boolean isExpletive(String string) {
        String url = "http://www.wdyl.com/profanity?q=" + string;
        try {
            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

            request.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

            return rootobj.get("response").getAsBoolean();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean containsExpletive(String string) {
        boolean bool = false;
        String[] array = string.trim().split(" ");

        for (String str : array) {
            if (isExpletive(str)) bool = true;
        }

        return bool;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        event.setFormat(RankManager.getRankPrefix(player) + " §7" + player.getName() + " §a" + event.getMessage());
    }
}
