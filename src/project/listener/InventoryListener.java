package project.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.toggle.ToggleManager;

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
public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals("Toggles")) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null) {
                ItemStack item = event.getCurrentItem();

                if (item.getType().equals(Material.EMERALD_BLOCK)) {
                    event.getCurrentItem().setType(Material.REDSTONE_BLOCK);

                    ItemMeta newMeta = event.getCurrentItem().getItemMeta().clone();

                    newMeta.setDisplayName(newMeta.getDisplayName().replaceAll("§a", "§c"));

                    event.getCurrentItem().setItemMeta(newMeta);

                    if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("blood")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.BLOOD);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("swearing")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.SWEARING);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("messages")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.PRIVATE_MESSAGES);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("match")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.MATCH_REQUESTS);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.GHAST_DEATH, 1F, 1F);
                        player.sendMessage("§4§lX §cUnknown toggle!");
                    }
                } else {
                    event.getCurrentItem().setType(Material.EMERALD_BLOCK);

                    ItemMeta newMeta = event.getCurrentItem().getItemMeta().clone();

                    newMeta.setDisplayName(newMeta.getDisplayName().replaceAll("§c", "§a"));

                    event.getCurrentItem().setItemMeta(newMeta);

                    if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("blood")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.BLOOD);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("swearing")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.SWEARING);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("messages")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.PRIVATE_MESSAGES);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("match")) {
                        ToggleManager.toggle(player, ToggleManager.Toggle.MATCH_REQUESTS);

                        SoundUtility.playSound(player, Sound.CLICK);
                    } else {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.GHAST_DEATH, 1F, 1F);
                        player.sendMessage("§4§lX §cUnknown toggle!");
                    }
                }
            }
        }
    }
}