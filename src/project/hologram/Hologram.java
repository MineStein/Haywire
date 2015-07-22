package project.hologram;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

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
public class Hologram {

    String[] lines;
    ArmorStand[] mounts;
    World world;
    Location location;

    public String[] getLines() {
        return lines;
    }

    public ArmorStand[] getMounts() {
        return mounts;
    }

    public World getWorld() {
        return world;
    }

    public Location getLocation() {
        return location;
    }

    public Hologram(Location location, String... lines) {
        this.lines = lines;
        this.world = location.getWorld();
        this.location = location;

        int counter = 0;

        ArmorStand[] array = new ArmorStand[lines.length];

        for (int i = 0; i < lines.length; i++) {
            ArmorStand mount = this.world.spawn(location.subtract(0, counter * 0.2, 0), ArmorStand.class);

            counter += 1;

            mount.setVisible(false);
            mount.setGravity(false);
            mount.setCustomName(lines[i]);
            mount.setCustomNameVisible(true);

            array[i] = mount;
        }

        this.mounts = array;
    }
}
