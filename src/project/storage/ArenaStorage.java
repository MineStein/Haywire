package project.storage;

import java.util.ArrayList;
import java.util.List;

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
public class ArenaStorage {

    private static List<String> inArena = new ArrayList<>();
    private static List<String> spawnKill = new ArrayList<>();

    public static List<String> getSpawnKill() {
        return spawnKill;
    }

    public static List<String> getInArena() {
        return inArena;
    }
}
