package project.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import project.match.MatchManager;
import project.rank.RankManager;
import project.storage.ArenaStorage;
import project.title.TitleUtility;

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
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        event.setQuitMessage("§c§l>> " + RankManager.getRankPrefix(player) + " §7" + player.getName() + " §ehas left.");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            TitleUtility.sendTabHF(onlinePlayer, "§3§lHaywire PvP", "§eThere are §7" + (Bukkit.getOnlinePlayers().size() - 1) + "§e/§7" + Bukkit.getServer().getMaxPlayers() + " §eplayers online.");
        }

        if (ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
            ArenaStorage.getInArena().remove(player.getUniqueId().toString());
        }

        if (MatchManager.getCurrentMatch().getPlayer1().getUniqueId().toString().equals(player.getUniqueId().toString())) {
            MatchManager.getCurrentMatch().endMatch(MatchManager.getCurrentMatch().getPlayer1());
        } else if (MatchManager.getCurrentMatch().getPlayer2().getUniqueId().toString().equals(player.getUniqueId().toString())) {
            MatchManager.getCurrentMatch().endMatch(MatchManager.getCurrentMatch().getPlayer2());
        }
    }
}
