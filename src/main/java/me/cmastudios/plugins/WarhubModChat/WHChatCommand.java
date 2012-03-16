package me.cmastudios.plugins.WarhubModChat;

import java.io.IOException;

import me.cmastudios.plugins.WarhubModChat.util.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WHChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		if (arg3.length < 1) {
			arg0.sendMessage("WHChat is enabled");
		} else {
			if (arg3[0].equalsIgnoreCase("set")) {
				if (!((Player) arg0).getName().equals("cmastudios")) {
					arg0.sendMessage("Only cmastudios can use this command");
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
		}
		return true;
	}

}
