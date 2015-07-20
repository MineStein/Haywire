package project.match;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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
public class Match {

    // blue: 208, 145, 356.5
    // red : 208, 145, 371.5

    Player player1, player2;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        player1.teleport(new Location(Bukkit.getWorld("arena"), 208, 145, 356.5));
        player2.teleport(new Location(Bukkit.getWorld("arena"), 208, 145, 371.5));

        player1.setHealth(player1.getMaxHealth());
        player2.setHealth(player2.getMaxHealth());

        player1.setGameMode(GameMode.SURVIVAL);
        player2.setGameMode(GameMode.SURVIVAL);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void endMatch(Player victor) {
        if (victor == null) {
            player1.sendMessage("§c§l>> §eThe round ended in a tie.");
            player2.sendMessage("§c§l>> §eThe round ended in a tie.");
        } else {
            if (victor == player1) {
                player2.sendMessage("§a§l>> §eYou won the match against §7" + player1.getName() + "§e.");
                player1.sendMessage("§c§l>> §eYou lost the match against §7" + player2.getName() + "§e.");
            } else if (victor == player2) {
                player1.sendMessage("§a§l>> §eYou won the match against §7" + player2.getName() + "§e.");
                player2.sendMessage("§c§l>> §eYou lost the match against §7" + player1.getName() + "§e.");
            } else {
                player1.sendMessage("§c§l>> §eThe round ended in a tie.");
                player2.sendMessage("§c§l>> §eThe round ended in a tie.");
            }
        }

        player1.teleport(new Location(Bukkit.getWorld("arena"), 258.5, 151, 362.5));
        player2.teleport(new Location(Bukkit.getWorld("arena"), 258.5, 151, 362.5));

        MatchManager.setCurrentMatch(null);
    }
}
