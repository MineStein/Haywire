package project.match;

import org.bukkit.entity.Player;

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
public class MatchManager {

    private static Map<Player, Player> invitations = new HashMap<Player, Player>();
    private static Match currentMatch;

    public static Map<Player, Player> getInvitations() {
        return invitations;
    }

    public static Match getCurrentMatch() {
        return currentMatch;
    }

    public static void setCurrentMatch(Match currentMatch) {
        MatchManager.currentMatch = currentMatch;
    }
}