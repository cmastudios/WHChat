package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.WarhubModChat;
import me.cmastudios.plugins.WarhubModChat.util.PlayerInfo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
	public static WarhubModChat plugin;
    public MuteCommand(WarhubModChat instance) {
        plugin = instance;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player=(Player)sender;
		}
		if (args.length < 1) {
			player.sendMessage(ChatColor.YELLOW + "Muted players:");
			String plrs = "";
			for (String plr : plugin.mutedplrs.keySet()) {
				plrs += plr + ", ";
			}
			player.sendMessage(ChatColor.YELLOW + plrs);
			player.sendMessage(ChatColor.YELLOW
					+ "Use /mute <player> to mute someone.");
			return true;
		} else if (args.length == 1) {
			Player todeafen = PlayerInfo.toPlayer(args[0]);
			if (todeafen == player && player.hasPermission("warhub.moderator")) {
				if (plugin.mutedplrs.containsKey(player.getName())) {
					plugin.mutedplrs.remove(player.getName());
					todeafen.sendMessage(ChatColor.YELLOW
							+ "You have been unmuted.");
				} else {
					plugin.mutedplrs.put(player.getName(), 1);
					todeafen.sendMessage(ChatColor.YELLOW
							+ "You have been muted.");
				}
			} else if (player.hasPermission("warhub.moderator")) {
				if (plugin.mutedplrs.containsKey(todeafen.getName())) {
					plugin.mutedplrs.remove(todeafen.getName());
					player.sendMessage(ChatColor.YELLOW
							+ todeafen.getName() + " has been unmuted.");
					todeafen.sendMessage(ChatColor.YELLOW
							+ "You have been unmuted.");
				} else {
					plugin.mutedplrs.put(todeafen.getName(), 1);
					player.sendMessage(ChatColor.YELLOW
							+ todeafen.getName() + " has been muted.");
					todeafen.sendMessage(ChatColor.YELLOW
							+ "You have been muted.");
				}

			} else {
				player.sendMessage(ChatColor.RED
						+ "You do not have permissions to mute players.");
			}
			return true;
		}
		return false;
	}

}
