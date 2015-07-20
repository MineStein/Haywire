package project.listener;

import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
public class SignListener implements Listener {

    @EventHandler
    public void onSignClick(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            final BlockState blockState = event.getClickedBlock().getState();

            if (blockState instanceof Sign) {
                final Sign sign = (Sign) blockState;

                if (sign.getLine(0).equalsIgnoreCase("[Join]")) {
                    player.performCommand("join");
                } else if (sign.getLine(0).equalsIgnoreCase("[Loadouts]")) {
                    player.performCommand("loadouts");
                }
            }
        }
    }
}
