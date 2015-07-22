package project.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import project.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

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
public class LoadoutInventory {

    public enum Page {
        LOADOUTS,

        CONFIGURE
    }

    public static Inventory getInventory(Player player, Page page) {
        Map<Enchantment, Integer> stockEnchantments = new HashMap<>();

        stockEnchantments.put(Enchantment.LUCK, 1);

        Inventory inventory = Bukkit.createInventory(null, 27, "Loadouts");
        ItemStack[] loadouts = new ItemStack[8];
        ItemStack unpurchased = new ItemBuilder().type(Material.GLASS).name("§3§l§oUnpurchased Loadout Slot").lore("§7You haven't purchased this slot yet.", "", "§6§lCLICK §7to purchase.").build();
        ItemStack purchasedSelected = new ItemBuilder().type(Material.WOOL).name("§3§l§oLoadout #1").enchantments(stockEnchantments).lore("§7You currently have this loadout selected.", "", "§a§lRIGHT-CLICK §7to deselect.", "§c§lLEFT-CLICK §7to edit.").data((byte) 5).build();
        ItemStack purchasedUnselected = new ItemBuilder().type(Material.WOOL).name("§3§l§oLoadout #2").lore("§7You currently do not have this loadout selected.", "", "§a§lRIGHT-CLICK §7to select.", "§c§lLEFT-CLICK §7to edit.").data((byte) 5).build();

        for (int i = 10; i < 16; i++) {
            for (ItemStack loadout : loadouts) {
                inventory.setItem(i, loadout);
            }
        }

        // 10

        inventory.setItem(10, purchasedSelected);
        inventory.setItem(11, purchasedUnselected);
        inventory.setItem(12, unpurchased);
        inventory.setItem(13, unpurchased);
        inventory.setItem(14, unpurchased);
        inventory.setItem(15, unpurchased);
        inventory.setItem(16, unpurchased);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) continue;

            inventory.setItem(i, new ItemBuilder().type(Material.STAINED_GLASS_PANE).name("§3§l§oLoadouts").data((byte) 3).build());
        }

        return inventory;
    }
}