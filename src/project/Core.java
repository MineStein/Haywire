package project;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import project.command.*;
import project.listener.*;
import project.task.AnnouncementTask;
import project.task.WorldManagementTask;

import java.util.Random;

/**
 * ****************************************************************************************
 * Copyright Tyler Grissom © 2013-2015
 * <p/>
 * Any code contained within this document, and any associated API's with similar branding
 * are the sole property of Tyler Grissom. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * <p/>
 * Thanks, and have a nice day.
 * ****************************************************************************************
 */
public class Core extends JavaPlugin {

    /** TODO
     *  Shadow mutes
     *  Scoreboard
     *  Replace all EssentialsX commands and implement wrappers for all commands
     *  Custom "command not found" message
     *  Loadouts
     *  /help for contacting staff
     *  Instant respawns
     *  Multiple ranks
     *  Custom world guard
     *  Optimize swear filter speed
     *  Toggles for blood, chat, private messages, match requests, and default chat color
     *  Grammar corrector
     *  Anti-cheat
     *  Achievements
     *  Fix stacktraces
     */

    private static Core plugin;
    private static PluginManager pluginManager;
    private static BukkitScheduler bukkitScheduler;
    private static ScoreboardManager scoreboardManager;
    private static Random random;

    public static Core getPlugin() {
        return plugin;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }

    public static ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public static Random getRandom() {
        return random;
    }

    @Override
    public void onEnable() {
        plugin = this;
        pluginManager = Bukkit.getServer().getPluginManager();
        bukkitScheduler = Bukkit.getServer().getScheduler();
        scoreboardManager = Bukkit.getServer().getScoreboardManager();
        random = new Random();

        getConfig().options().copyDefaults(true);
        saveConfig();

        getPluginManager().registerEvents(new JoinListener(), this);
        getPluginManager().registerEvents(new QuitListener(), this);
        getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getPluginManager().registerEvents(new RespawnListener(), this);
        getPluginManager().registerEvents(new MoveListener(), this);
        getPluginManager().registerEvents(new BlockListener(), this);
        getPluginManager().registerEvents(new DamageListener(), this);
        getPluginManager().registerEvents(new ItemListener(), this);
        getPluginManager().registerEvents(new PingListener(), this);
        getPluginManager().registerEvents(new CheatListener(), this);
        getPluginManager().registerEvents(new SignListener(), this);
        getPluginManager().registerEvents(new DeathListener(), this);
        getPluginManager().registerEvents(new ChatListener(), this);

        getBukkitScheduler().scheduleSyncRepeatingTask(this, new WorldManagementTask(), 0L, 10L);
        getBukkitScheduler().scheduleSyncRepeatingTask(this, new AnnouncementTask(), 0L, 100L);

        getCommand("join").setExecutor(new JoinCommand());
        getCommand("loadout").setExecutor(new LoadoutCommand());
        getCommand("match").setExecutor(new MatchCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
