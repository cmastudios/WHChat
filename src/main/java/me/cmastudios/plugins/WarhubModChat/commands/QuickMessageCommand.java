package me.cmastudios.plugins.WarhubModChat.commands;

import java.util.ArrayList;
import java.util.List;

import me.cmastudios.plugins.WarhubModChat.WarhubModChat;
import me.cmastudios.plugins.WarhubModChat.util.Config;
import me.cmastudios.plugins.WarhubModChat.util.Message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuickMessageCommand implements CommandExecutor {
	public static WarhubModChat plugin;
    public QuickMessageCommand(WarhubModChat instance) {
        plugin = instance;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if (cmd.getName().equalsIgnoreCase("modchat")) {
			if (!sender.hasPermission("warhub.moderator")) {
				sender.sendMessage(ChatColor.RED
						+ "You don't have the permissions to do that!");
				return true;
			}
			if (args.length < 1) {
				if (player == null) {
					System.out.println("You can't use channels from the console, use '/modchat <message>' to chat.");
					return true;
				}
				plugin.channels.put(player, "mod");
				player.sendMessage(ChatColor.YELLOW + "Chat switched to mod.");
			} else {
				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}
				if (message.equals(""))
					return false;
				if (player == null) {
					List<Player> sendto = new ArrayList<Player>();
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						if (p.hasPermission("warhub.moderator")) {
							sendto.add(p);
						}
					}
					for (Player p : sendto) {
						p.sendMessage(Message.colorizeText(Config
								.config.getString("modchat-format")
								.replace("%player", "tommytony")
								.replace("%message", message)));
					}
					System.out.println("[MODCHAT] tommytony: " + message);
					sendto.clear();
					return true;
				}
				if (plugin.channels.containsKey(player)) {
					String channel = plugin.channels.remove(player);
					player.chat(message);
					plugin.channels.put(player, channel);
					channel = null;
				} else {
					plugin.channels.put(player, "mod");
					player.chat(message);
					plugin.channels.remove(player);
				}

			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("alert")) {
			if (player == null) {
				System.out.println("You can't use alert from the console, use '/say <message>' to chat.");
				return true;
			}
			if (!player.hasPermission("warhub.moderator")) {
				player.sendMessage(ChatColor.RED
						+ "You don't have the permissions to do that!");
				return true;
			}
			if (args.length < 1) {
				plugin.channels.put(player, "alert");
				player.sendMessage(ChatColor.YELLOW + "Chat switched to alert.");
			} else {
				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}
				if (message.equals(""))
					return false;
				if (plugin.channels.containsKey(player)) {
					String channel = plugin.channels.remove(player);
					player.chat(message);
					plugin.channels.put(player, channel);
					channel = null;
				} else {
					plugin.channels.put(player, "alert");
					player.chat(message);
					plugin.channels.remove(player);
				}

			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("global")) {
			if (player == null) {
				System.out.println("You can't use global from the console, use '/say <message>' to chat.");
				return true;
			}
			if (args.length < 1) {
				plugin.channels.remove(player);
				player.sendMessage(ChatColor.YELLOW
						+ "Chat switched to global.");
			} else {
				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}
				if (message.equals(""))
					return false;
				if (plugin.channels.containsKey(player)) {
					String channel = plugin.channels.remove(player);
					player.chat(message);
					plugin.channels.put(player, channel);
					channel = null;
				} else {
					player.chat(message);
				}

			}
			return true;
		}

		return false;
	}

}
