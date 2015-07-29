package project;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import project.command.*;
import project.hologram.Hologram;
import project.listener.*;
import project.scoreboard.ScoreboardManager;
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
     *  Replace all EssentialsX commands and implement wrappers for all commands
     *  Loadouts
     *  Custom WorldGuard
     *  Optimize swear filter speed
     *  Grammar corrector
     *  Anti-cheat
     *  Achievements
     *  Fix stacktraces
     *  Particle trails for players, arrows, and specific ranks
     *  Instant respawn
     *  Powerups for health and damage
     *  Killstreaks
     *  Combat tags
     *  Multiple matches
     *  Match map selection
     *  2v2 matches
     *  Match kits
     *  Custom WorldEdit
     *  Polls
     *  Make it so that players cannot equip armor on armor stands
     *  Configurable messages
     *  Configurable MOTDs with paginated listings
     *  Pagination API
     *  Custom player API
     *  Finish toggle implementations
     *  Better death tracking with HashMaps
     */

    private static Core plugin;
    private static PluginManager pluginManager;
    private static BukkitScheduler bukkitScheduler;
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

    public static Random getRandom() {
        return random;
    }

    @Override
    public void onEnable() {
        plugin = this;
        pluginManager = Bukkit.getServer().getPluginManager();
        bukkitScheduler = Bukkit.getServer().getScheduler();
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
        getPluginManager().registerEvents(new CommandPreProcessListener(), this);
        getPluginManager().registerEvents(new InventoryListener(), this);

        getBukkitScheduler().scheduleSyncRepeatingTask(this, new WorldManagementTask(), 0L, 10L);
        getBukkitScheduler().scheduleSyncRepeatingTask(this, new AnnouncementTask(), 0L, 100L);

        getCommand("join").setExecutor(new JoinCommand());
        getCommand("loadout").setExecutor(new LoadoutCommand());
        getCommand("match").setExecutor(new MatchCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("announce").setExecutor(new AnnounceCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("pixel").setExecutor(new PixelCommand());
        getCommand("toggle").setExecutor(new ToggleCommand());

        Bukkit.getWorld("pvp_arena").setSpawnLocation(258, 141, 362);

        Bukkit.getOnlinePlayers().forEach(ScoreboardManager::refreshScoreboard);

        Hologram hologram = new Hologram(new Location(Bukkit.getWorld("pvp_arena"), 260.5, 151.5, 362.5), "§eWelcome to §3§lHaywireMC PvP", "§chttp://our-website.net", "§eDeveloped by §7@minestien");
}

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);

        Bukkit.getWorld("pvp_arena").getEntities().stream().filter(entity -> entity.getType().equals(EntityType.ARMOR_STAND)).forEach(org.bukkit.entity.Entity::remove);
    }
}
