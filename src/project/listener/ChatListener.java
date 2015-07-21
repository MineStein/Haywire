package project.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import project.mute.MuteManager;
import project.rank.RankManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

    private static Map<String, String> repeatFilter = new HashMap<>();

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

    private boolean isShouting(String string) {
        int charLength = string.toCharArray().length;
        int capitalizedCount = 0;

        for (char character : string.toCharArray()) {
            if (Character.toTitleCase(character) == character) capitalizedCount++;
        }

        if (charLength == capitalizedCount) return true;

        if (charLength > 6) {
            if (charLength % 2 == 0) { // is even
                if (capitalizedCount > charLength / 2) return true;
            } else { // is odd
                if (capitalizedCount > (charLength / 2 + 0.5)) return true;
            }
        }

        return false;
    }

    private String correctGrammar(String string) {
        String revision = WordUtils.capitalize(string);
        char punctuation = string.charAt(string.toCharArray().length - 1);

        if (punctuation != '.' && punctuation != '!' && punctuation != '?' && punctuation != ';') revision += ".";

        return revision;
    }

    private void notify(String string) {
        String revision = string.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (revision.contains(player.getName().replaceAll("[^a-zA-Z]", "").toLowerCase().trim())) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (containsExpletive(event.getMessage()) || isExpletive(event.getMessage())) {
            player.sendMessage("§4§lX §cWatch your language!");

            event.setCancelled(true);

            return;
        }

        if (repeatFilter.containsKey(player.getUniqueId().toString())) {
            String previousMessage = repeatFilter.get(player.getUniqueId().toString());
            String revision1, revision2;

            revision1 = event.getMessage().trim().replaceAll("[^a-zA-Z ]", "").toLowerCase();
            revision2 = revision1.replaceAll(" ", "");
            
            if (revision2.equalsIgnoreCase(previousMessage)) {
                player.sendMessage("§4§lX §cThat message is too similar to your previous message.");
                
                event.setCancelled(true);

                return;
            } else {
                repeatFilter.put(player.getUniqueId().toString(), revision2);
            }
        } else {
            repeatFilter.put(player.getUniqueId().toString(), event.getMessage());
        }

        if (isShouting(event.getMessage())) {
            player.sendMessage("§4§lX §cThere is no need to shout.");

            event.setCancelled(true);

            return;
        }

        if (MuteManager.isMuted(player)) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer.getUniqueId().toString().equals(player.getUniqueId().toString())) continue;

                event.getRecipients().remove(onlinePlayer);
            }
        }

        String message = correctGrammar(event.getMessage());

        notify(message);

        event.setFormat(RankManager.getRankPrefix(player) + " §7" + player.getName() + " §a" + ChatColor.translateAlternateColorCodes('&', message));
    }
}
