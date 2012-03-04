package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.util.Config;
import me.cmastudios.plugins.WarhubModChat.util.Message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if (player != null)
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED
						+ "You don't have the permissions to do that!");
				return true;
			}
		if (args.length == 0)
			return false;
		String message = "";
		for (String arg : args) {
			message = message + arg + " ";
		}
		if (message.equals(""))
			return false;
		Bukkit.getServer().broadcastMessage(
				Message.colorizeText(Config.config.getString("say-format"))
						.replace("%message", message));
		return true;

	}

}
