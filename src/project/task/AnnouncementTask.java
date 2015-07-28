package project.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.Core;
import project.actionbar.ActionBarUtility;

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
public class AnnouncementTask implements Runnable {

    @Override
    public void run() {
        String[] announcements = new String[] { "§d§lDo §5§l/toggle §d§lto change your preferences", "§d§lDo §5§l/join §d§lto join the arena.", "§d§lDo §5§l/help §d§lto request help from staff.", "§d§lDo §5§l/loadout §d§lto select/change your loadouts." };

        for (Player player : Bukkit.getOnlinePlayers()) {
            ActionBarUtility.sendActionBar(player, announcements[Core.getRandom().nextInt(announcements.length)]);
        }
    }
}
