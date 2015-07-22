package project.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

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
public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        player.sendMessage("§4§lX §cYou cannot break blocks here.");

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        player.sendMessage("§4§lX §cYou cannot place blocks here.");

        event.setCancelled(true);
    }
}
