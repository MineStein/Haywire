package project.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

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
public class ItemListener implements Listener {

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(final PlayerPickupItemEvent event) {
        final Player player = event.getPlayer();

        event.setCancelled(true);
    }
}
