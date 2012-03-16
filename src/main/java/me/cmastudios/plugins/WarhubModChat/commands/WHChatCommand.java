package me.cmastudios.plugins.WarhubModChat.commands;

import java.io.IOException;

import me.cmastudios.plugins.WarhubModChat.util.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WHChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		if (arg3.length < 1) {
			arg0.sendMessage("WHChat is enabled");
		} else {
			if (arg3[0].equalsIgnoreCase("set")) {
				if (!arg0.hasPermission("warhub.moderator")) {
					arg0.sendMessage(ChatColor.RED + "You don't have permission");
					return true;
				}
				Config.config.set(arg3[1], arg3[2]);
				try {
					Config.config.save(Config.file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				arg0.sendMessage(arg3[1] + " set to " + arg3[2]);
			}
			if (arg3[0].equalsIgnoreCase("get")) {
				if (!arg0.hasPermission("warhub.moderator")) {
					arg0.sendMessage(ChatColor.RED + "You don't have permission");
					return true;
				}
				arg0.sendMessage(arg3[1] + " is " + Config.config.getString(arg3[1]));
			}
		}
		return true;
	}

}
