package project.scoreboard;

import org.bukkit.entity.Player;
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
public class ScoreboardManager {

    public static void refreshScoreboard(Player player) {
        SimpleScoreboard scoreboard = new SimpleScoreboard("§3§lHaywireMC");

        scoreboard.add("§ePixels");
        scoreboard.add("§7" + StatisticManager.getPixels(player));
        scoreboard.add("§eKills");
        scoreboard.add("§7" + StatisticManager.getKills(player));
        scoreboard.add("§eDeaths");
        scoreboard.add("§7" + StatisticManager.getDeaths(player));

        scoreboard.build();
        scoreboard.send(player);
    }
}
