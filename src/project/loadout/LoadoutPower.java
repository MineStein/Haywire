package project.loadout;

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
public enum LoadoutPower {

    HEALING("Healing"),

    PAIN_TOLERANCE("Pain Tolerance"),

    DAMAGE_AURA("Damage Aura");

    String name;

    LoadoutPower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
