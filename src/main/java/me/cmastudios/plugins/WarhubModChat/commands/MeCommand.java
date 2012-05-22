package me.cmastudios.plugins.WarhubModChat.commands;

import me.cmastudios.plugins.WarhubModChat.WarhubModChat;
import me.cmastudios.plugins.WarhubModChat.WarhubModChatListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MeCommand implements CommandExecutor {
	public static WarhubModChat plugin;
    public MeCommand(WarhubModChat instance) {
        plugin = instance;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player=(Player)sender;
		} else {
	    	if (args.length < 1) return false;
			String message = "";
			for (String arg : args) {
				message = message + arg + " ";
			}
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (!plugin.ignores.contains(p.getName()))
					p.sendMessage("* Server "
							+ message);
			}
			System.out.println("* Server "
							+ message);
			return true;
		}
    	if (plugin.mutedplrs.containsKey(player.getName())) {
    		player.sendMessage(ChatColor.RED + "You are muted.");
    		return true;
    	}
    	if (args.length < 1) return false;
		String message = "";
		for (String arg : args) {
			message = message + arg + " ";
		}
		if (message.contains("\u00A7"))
			message = message.replaceAll("\u00A7[0-9a-zA-Z]", "");
		message = new WarhubModChatListener(plugin).Capslock(player, message);
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!plugin.ignores.contains(p.getName()))
				p.sendMessage("* " + ChatColor.WHITE
						+ player.getDisplayName() + ChatColor.WHITE + " "
						+ message);
		}
		return true;
	}

}
