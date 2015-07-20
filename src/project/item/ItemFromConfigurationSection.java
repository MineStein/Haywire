package project.item;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.Core;

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
public class ItemFromConfigurationSection {

    ConfigurationSection section;
    ConfigurationSection enchantsSection;
    Material material;
    String name;
    int amount;
    Map<Enchantment, Integer> enchantments;

    public ItemFromConfigurationSection(String section) {
        this.section = Core.getPlugin().getConfig().getConfigurationSection(section);
        this.enchantsSection = this.section.getConfigurationSection("enchant");

        if (section == null) throw new IllegalArgumentException("Section cannot be null.");

        if (this.enchantsSection == null) {
            this.enchantments = null;
        } else {
            for (String string : enchantsSection.getKeys(false)) {
                for (String enchantmentInfoSectionName : enchantsSection.getConfigurationSection(string).getKeys(false)) {
                    ConfigurationSection enchantmentInfoSection = enchantsSection.getConfigurationSection(string + "." + enchantmentInfoSectionName);
                    Enchantment enchantment = enchantmentInfoSection.get("enchant") == null ? Enchantment.PROTECTION_ENVIRONMENTAL : Enchantment.getById(enchantmentInfoSection.getInt("enchant"));
                    int level = enchantmentInfoSection.get("level") == null ? 1 : enchantmentInfoSection.getInt("level");

                    enchantments.put(enchantment, level);
                }
            }
        }

        this.material = this.section.get("type") == null ? Material.getMaterial(1) : Material.getMaterial(this.section.getInt("type"));
        this.name = this.section.get("name") == null ? this.material.toString() : this.section.getString("name");
        this.amount = this.section.get("amount") == null ? 1 : this.section.getInt("amount");
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.amount); {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName(this.name);

            if (enchantments != null) {
                for (Map.Entry entry : enchantments.entrySet()) {
                    meta.addEnchant((Enchantment) entry.getKey(), (Integer) entry.getValue(), true);
                }
            }

            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }
}
