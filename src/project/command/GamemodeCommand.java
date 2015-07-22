package project.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.rank.RankManager;

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
public class GamemodeCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String usage = "§c§l>> §eUsage: §7/gamemode <gamemode> [player]";

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 3 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §73§e!");

                return;
            }
        }

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {
            String param = arguments[0];

            if (arguments.length == 1) {
                if (sender instanceof Player) {
                    final Player player = (Player) sender;

                    if (param.equalsIgnoreCase("0") || param.equalsIgnoreCase("survival") || param.equalsIgnoreCase("s")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§a§l>> §eGamemode updated to §7" + player.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("1") || param.equalsIgnoreCase("creative") || param.equalsIgnoreCase("c")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§a§l>> §eGamemode updated to §7" + player.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("2") || param.equalsIgnoreCase("adventure") || param.equalsIgnoreCase("a")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§a§l>> §eGamemode updated to §7" + player.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("3") || param.equalsIgnoreCase("spectator") || param.equalsIgnoreCase("sp")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§a§l>> §eGamemode updated to §7" + player.getGameMode().toString() + "§e.");
                    } else {
                        player.sendMessage("§4§lX Unknown sub-command.");
                    }
                } else {
                    sender.sendMessage("§4§lX §cPlease specify a player.");
                }
            } else {
                final Player target = Bukkit.getPlayer(arguments[1]);

                if (target != null) {
                    if (param.equalsIgnoreCase("0") || param.equalsIgnoreCase("survival") || param.equalsIgnoreCase("s")) {
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage("§a§l>> §eGamemode updated to §7" + target.getGameMode().toString() + "§e.");

                        sender.sendMessage("§a§l>> §eYou updated §7" + target.getName() + "'s §egamemode to §7" + target.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("1") || param.equalsIgnoreCase("creative") || param.equalsIgnoreCase("c")) {
                        target.setGameMode(GameMode.CREATIVE);
                        target.sendMessage("§a§l>> §eGamemode updated to §7" + target.getGameMode().toString() + "§e.");

                        sender.sendMessage("§a§l>> §eYou updated §7" + target.getName() + "'s §egamemode to §7" + target.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("2") || param.equalsIgnoreCase("adventure") || param.equalsIgnoreCase("a")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        target.sendMessage("§a§l>> §eGamemode updated to §7" + target.getGameMode().toString() + "§e.");

                        sender.sendMessage("§a§l>> §eYou updated §7" + target.getName() + "'s §egamemode to §7" + target.getGameMode().toString() + "§e.");
                    } else if (param.equalsIgnoreCase("3") || param.equalsIgnoreCase("spectator") || param.equalsIgnoreCase("sp")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage("§a§l>> §eGamemode updated to §7" + target.getGameMode().toString() + "§e.");

                        sender.sendMessage("§a§l>> §eYou updated §7" + target.getName() + "'s §egamemode to §7" + target.getGameMode().toString() + "§e.");
                    } else {
                        sender.sendMessage("§4§lX Unknown sub-command.");
                    }
                } else {
                    sender.sendMessage("§4§lX §cThat player is offline!");
                }
            }
        }
    }
}
