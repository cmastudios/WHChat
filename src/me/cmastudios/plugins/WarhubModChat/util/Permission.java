package me.cmastudios.plugins.WarhubModChat.util;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import me.cmastudios.plugins.WarhubModChat.*;

public class Permission {
	Logger log = Logger.getLogger("Minecraft");
    public static PermissionHandler permissionHandler;
    private static boolean UsePermissions;
    public WarhubModChat plugin;
	public static boolean has (Player player, String permissionNode) {
		if (player == null) return true;
		if (player.isOp()) return true;
		if (permissionNode == null && player.isOp() == true) return true;
		if (permissionNode == null && player.isOp() == false) return false;
		if (UsePermissions == true && permissionHandler.has(player, permissionNode)) return true;
		if (player.hasPermission(permissionNode)) return true;
		return false;
	}
	public void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = Bukkit.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.info("[WarhubModChat] Permissions ready using SuperPerms!");
	        UsePermissions = false;
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    log.info("[WarhubModChat] Permissions ready using "+((Permissions)permissionsPlugin).getDescription().getFullName());
	    UsePermissions = true;
	}

}
