package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.Core;
import project.loadout.LoadoutManager;
import project.match.Match;
import project.match.MatchManager;
import project.storage.ArenaStorage;

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
public class MatchCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String[] usage = new String[] { "§6§l******** §e/match §6§l********", "§7§o/match invite <player>", "§7§o/match accept", "§7§o/match deny" };

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (arguments.length == 0) {
                player.sendMessage(usage);
            } else {
                String argument = arguments[0];

                if (argument.equalsIgnoreCase("invite")) {
                    if (arguments.length == 1) {
                        player.sendMessage(usage);
                    } else {
                        Player target = Bukkit.getPlayer(arguments[1]);

                        if (MatchManager.getCurrentMatch() == null) {
                            if (target != null) {
                                if (!player.getUniqueId().toString().equals(target.getUniqueId().toString())) {
                                    if (MatchManager.getInvitations().containsKey(target)) {
                                        player.sendMessage("§4§lX §cThat player already has a pending invitation!");
                                    } else if (MatchManager.getInvitations().containsValue(target)) {
                                        player.sendMessage("§4§lX §cThat player has already sent a pending invitation!");
                                    } else if (MatchManager.getInvitations().containsKey(player)) {
                                        player.sendMessage("§4§lX §cYou already have a pending request from §7" + MatchManager.getInvitations().get(player).getName() + "§e. Do §7/match deny §e to deny it, or §7/match accept §e to accept it.");
                                    } else if (MatchManager.getInvitations().containsValue(player)) {
                                        player.sendMessage("§4§lX §cYou already have sent a pending invitation!");
                                    } else if (ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
                                        player.sendMessage("§4§lX §cYou cannot send requests whilst in the main arena!");
                                    } else if (ArenaStorage.getInArena().contains(target.getUniqueId().toString())) {
                                        player.sendMessage("§4§lX §cYou cannot send a request to this player, as they are currently in the arena!");
                                        target.sendMessage("§c§l>> §7" + player.getName() + " §ewished to send you a request, but it failed due to you being in the arena!");
                                    } else {
                                        MatchManager.getInvitations().put(target, player);

                                        target.sendMessage("§a§l>> §7" + player.getName() + " §ehas requested that you fight them solo. Do §7/match accept §eor §7/match deny §eto respond. This expires in §720 seconds§e.");
                                        player.sendMessage("§a§l>> §eMatch request sent to §7" + target.getName() + "§e.");

                                        Bukkit.getScheduler().runTaskLater(Core.getPlugin(), () -> {
                                            if (MatchManager.getInvitations().containsKey(target)) {
                                                MatchManager.getInvitations().remove(target);

                                                target.sendMessage("§c§l>> §eThe pending match invitation from §7" + player.getName() + " §ehas expired.");
                                                player.sendMessage("§c§l>> §eThe pending match invitation for §7" + target.getName() + " §ehas expired.");
                                            }
                                        }, 20 * 20);
                                    }
                                } else {
                                    player.sendMessage("§4§lX §cYou cannot send a request to yourself!");
                                }
                            } else {
                                player.sendMessage("§4§lX §cThat player is offline.");
                            }
                        } else {
                            player.sendMessage("§4§lX §cA match is already occurring.");
                        }
                    }
                } else if (argument.equalsIgnoreCase("accept")) {
                    if (MatchManager.getInvitations().containsKey(player)) {
                        if (!ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
                            final Player invitationSender = MatchManager.getInvitations().get(player);

                            MatchManager.getInvitations().remove(player);

                            invitationSender.sendMessage("§a§l>> §7" + player.getName() + " §eaccepted the your invitation to a private match. Teleporting in §710 seconds§e...");
                            player.sendMessage("§a§l>> §eTeleporting in §710 seconds§e...");

                            Bukkit.getScheduler().runTaskLater(Core.getPlugin(), () -> {
                                if (ArenaStorage.getInArena().contains(invitationSender.getUniqueId().toString())) {
                                    invitationSender.sendMessage("§c§l>> §eMatch request cancelled due to you being in the main arena. Please leave and try again.");
                                    player.sendMessage("§c§l>> §eMatch request cancelled due to §7" + invitationSender.getName() + " §ebeing in the main arena. Please leave and try again.");
                                } else if (ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
                                    player.sendMessage("§c§l>> §eMatch request cancelled due to you being in the main arena. Please leave and try again.");
                                    invitationSender.sendMessage("§c§l>> §eMatch request cancelled due to §7" + player.getName() + " §ebeing in the main arena. Please leave and try again.");
                                } else {
                                    MatchManager.setCurrentMatch(new Match(invitationSender, player));

                                    invitationSender.sendMessage("§a§l>> §eYou have joined the match against §7" + player.getName() + "§e.");
                                    player.sendMessage("§a§l>> §eYou have joined the match against §7" + invitationSender.getName() + "§e.");

                                    LoadoutManager.giveLoadout(player);
                                    LoadoutManager.giveLoadout(invitationSender);
                                }
                            }, 20 * 10);
                        } else {
                            player.sendMessage("§4§lX §cYou cannot accept match invitations inside of the arena!");
                        }
                    } else {
                        player.sendMessage("§4§lX §cYou don't have any match requests.");
                    }
                } else if (argument.equalsIgnoreCase("deny")) {
                    if (MatchManager.getInvitations().containsKey(player)) {
                        MatchManager.getInvitations().get(player).sendMessage("§c§l>> §7" + player.getName() + " §ehas denied your match request.");

                        MatchManager.getInvitations().remove(player);

                        player.sendMessage("§c§l>> §cYou denied the pending match request.");
                    } else {
                        player.sendMessage("§4§lX §cYou don't have any match requests.");
                    }
                } else {
                    player.sendMessage(usage);
                }
            }
        } else {
            sender.sendMessage("§4§lX §cYou cannot execute this message from console.");
        }
    }
}
