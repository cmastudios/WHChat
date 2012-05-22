package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.WarhubModChat;
import me.cmastudios.plugins.WarhubModChat.util.PlayerInfo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteCommand implements CommandExecutor {
	public static WarhubModChat plugin;
    public MuteCommand(WarhubModChat instance) {
        plugin = instance;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		if (args.length < 1) {
			// No args, display list
			sender.sendMessage(ChatColor.YELLOW + "Muted players:");
			String plrs = "";
			for (String plr : plugin.mutedplrs.keySet()) {
				plrs += plr + ", ";
			}
			sender.sendMessage(ChatColor.YELLOW + plrs);
			sender.sendMessage(ChatColor.YELLOW
					+ "Use /mute <player> to mute someone.");
			return true;
		} else if (args.length == 1) {
			// One argument, mute the player
			String tomute;
			OfflinePlayer tomuteplayer = Bukkit.getServer().getOfflinePlayer(args[0]);
			if (tomuteplayer != null) {
				tomute = tomuteplayer.getName();
			} else {
				sender.sendMessage(ChatColor.RED + args[0] + " has never joined the server before!");
			    return true;	
			}
			if (sender.hasPermission("warhub.moderator")) {
				if (plugin.mutedplrs.containsKey(tomute)) {
					plugin.mutedplrs.remove(tomute);
					sender.sendMessage(ChatColor.YELLOW
							+ tomute + " has been unmuted.");
					if (PlayerInfo.isPlayer(tomute)) PlayerInfo.toPlayer(tomute).sendMessage(ChatColor.YELLOW
							+ "You have been unmuted.");
				} else {
					plugin.mutedplrs.put(tomute, 1);
					sender.sendMessage(ChatColor.YELLOW
							+ tomute + " has been muted.");
					if (PlayerInfo.isPlayer(tomute)) PlayerInfo.toPlayer(tomute).sendMessage(ChatColor.YELLOW
							+ "You have been muted.");
				}

			} else {
				sender.sendMessage(ChatColor.RED
						+ "You do not have permissions to mute players.");
			}
			return true;
		}
		return false;
	}

}
