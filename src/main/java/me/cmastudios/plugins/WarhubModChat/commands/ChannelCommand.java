package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.WarhubModChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChannelCommand implements CommandExecutor {
	public static WarhubModChat plugin;
    public ChannelCommand(WarhubModChat instance) {
        plugin = instance;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if (player == null) {
			System.out.println("You can't use channels from the console, use '/<say/modchat> <message>' to chat.");
			return true;
		}
		
		
		
		if (args.length < 1) {
			if (!player.hasPermission("warhub.moderator")) {
				player.sendMessage(ChatColor.RED
						+ "You're not a mod, and cannot change channels.");
				return true;
			} else {
				player.sendMessage(ChatColor.RED
						+ "Use '/ch <mod/alert/global>' to change your channel.");
			}
			return true;
		}
		
		
		
		
		
		if (args[0].equalsIgnoreCase("mod")
				|| args[0].equalsIgnoreCase("modchat")
				|| args[0].equalsIgnoreCase("m")) {
			if (!player.hasPermission("warhub.moderator")) {
				player.sendMessage(ChatColor.RED
						+ "You don't have the permissions to do that!");
				return true;
			}
			plugin.channels.put(player, "mod");
			player.sendMessage(ChatColor.YELLOW + "Chat switched to mod.");
			return true;
		}
		if (args[0].equalsIgnoreCase("a")
				|| args[0].equalsIgnoreCase("alert")) {
			if (!player.hasPermission("warhub.moderator")) {
				player.sendMessage(ChatColor.RED
						+ "You don't have the permissions to do that!");
				return true;
			}
			plugin.channels.put(player, "alert");
			player.sendMessage(ChatColor.YELLOW + "Chat switched to alert.");
			return true;
		}
		if (args[0].equalsIgnoreCase("g")
				|| args[0].equalsIgnoreCase("global")) {
			plugin.channels.remove(player);
			player.sendMessage(ChatColor.YELLOW
					+ "Chat switched to global.");
			return true;
		}
		if (!player.hasPermission("warhub.moderator")) {
			player.sendMessage(ChatColor.RED
					+ "You're not a mod, and cannot change channels.");
			return true;
		} else {
			player.sendMessage(ChatColor.RED
					+ "Use '/ch <mod/alert/global>' to change your channel.");
		}
		return true;
	}

}
