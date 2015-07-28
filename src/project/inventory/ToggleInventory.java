package project.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import project.item.ItemBuilder;
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
public class ToggleInventory {

    private static ItemStack getItemStack(Player player, ToggleManager.Toggle toggle) {
        return new ItemBuilder().type((ToggleManager.getToggle(player, toggle) ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK)).name("§" + (ToggleManager.getToggle(player, toggle) ? "a" : "c") + "§l" + (Character.toTitleCase(toggle.getName().charAt(0)) + toggle.getName().substring(1).toLowerCase().replaceAll("-", " "))).build();
    }

    public static Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Toggles");

        for (int i = 0; i < ToggleManager.Toggle.values().length; i++) {
            if (i > 9) break;

            inventory.setItem(i, getItemStack(player, ToggleManager.Toggle.values()[i]));
        }

        return inventory;
    }
}
