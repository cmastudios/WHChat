package me.cmastudios.plugins.WarhubModChat;

import java.util.ArrayList;
import java.util.List;

import me.cmastudios.plugins.WarhubModChat.util.Config;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class plrLstnr extends PlayerListener {
	public static WarhubModChat plugin;
	 
    public plrLstnr(WarhubModChat instance) {
        plugin = instance;
    }
    @SuppressWarnings("static-access")
	public void onPlayerChat (PlayerChatEvent event) {
    	if (event.getMessage().contains("\u00A7") && !plugin.permissions.has(event.getPlayer(), "warhub.moderator")) {
    		event.setMessage(event.getMessage().replaceAll("\u00A7[0-9a-fA-FkK]", ""));
    	}
    	if (plugin.channels.containsKey(event.getPlayer())) {
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("mod")) {
    			List<Player> sendto = new ArrayList<Player>();
    			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    				if (plugin.permissions.has(p, "warhub.moderator")) {
    					sendto.add(p);
    				}
    			}
    			for (Player p : sendto) {
    				p.sendMessage(plugin.messageUtil.colorizeText(Config.read("modchat-format")).replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			}
    			plugin.log.info("[MODCHAT] "+event.getPlayer().getDisplayName()+": "+event.getMessage());
    			sendto.clear();
    			event.setCancelled(true);
    		}
    		if (plugin.channels.get(event.getPlayer()).equalsIgnoreCase("alert")) {
    			Bukkit.getServer().broadcastMessage(plugin.messageUtil.colorizeText(Config.read("alert-format")).replace("%player", event.getPlayer().getDisplayName()).replace("%message", event.getMessage()));
    			event.setCancelled(true);
    		}
    	}
    }
    public void onPlayerJoin (PlayerJoinEvent event) {
    	plugin.channels.remove(event.getPlayer());
    }

}
