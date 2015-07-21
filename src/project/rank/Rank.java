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

    ASSOCIATE(1, '8', 0),

    VIP(1, 'e', 0),

    ARTIST(3, 'a', 1),

    ARCHITECT(4, 'a', 1),

    DEVELOPER(5, 'a', 1),

    MODERATOR(6, '5', 2),

    ADMIN(7, 'c', 3),

    HEAD(8, '4', 4),

    LEAD_DEVELOPER(9, '4', 4);

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
