package project.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import project.match.MatchManager;
import project.statistic.StatisticManager;

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
public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(final PlayerDeathEvent event) {
        final Player player = event.getEntity();

        event.setDeathMessage(null);

        StatisticManager.incrementDeaths(player);

        player.sendMessage("§c§l>> §eYou died! §7+1 §edeath!");

        if (player.getKiller() != null) {
            StatisticManager.incrementKills(player.getKiller());
            StatisticManager.addPixels(player.getKiller(), 10);

            player.getKiller().sendMessage("§a§l>> §eYou killed §7" + player.getName() + "§e! §7+1 §ekill, §7+10 §epixels!");
        }

        if (MatchManager.getCurrentMatch().getPlayer1().getUniqueId().toString().equals(player.getUniqueId().toString())) {
            MatchManager.getCurrentMatch().endMatch(MatchManager.getCurrentMatch().getPlayer1());
        } else if (MatchManager.getCurrentMatch().getPlayer2().getUniqueId().toString().equals(player.getUniqueId().toString())) {
            MatchManager.getCurrentMatch().endMatch(MatchManager.getCurrentMatch().getPlayer2());
        }
    }
}
