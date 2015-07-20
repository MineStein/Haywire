package project.rank;

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
public enum Rank {

    DEFAULT(0, '8', 0),

    ARTIST(1, 'a', 1),

    ARCHITECT(2, 'a', 1),

    DEVELOPER(3, 'a', 1),

    MODERATOR(4, '5', 2),

    ADMIN(5, 'c', 3),

    HEAD(6, '4', 4),

    LEAD_DEVELOPER(7, '4', 4);

    int index, permissionLevel;
    char color;

    Rank(int index, char color, int permissionLevel) {
        this.index = index;
        this.color = color;
        this.permissionLevel = permissionLevel;
    }

    public int getIndex() {
        return index;
    }

    public char getColor() {
        return color;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
