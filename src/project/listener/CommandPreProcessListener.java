package project.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

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
public class CommandPreProcessListener implements Listener {

    @EventHandler
    public void onUnknownCommand(PlayerCommandPreprocessEvent event) {
        if (Bukkit.getHelpMap().getHelpTopic(event.getMessage().split(" ")[0]) == null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§4§lX §cCommand not found.");
        }
    }
}
