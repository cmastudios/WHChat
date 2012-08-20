/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cmastudios.plugins.WarhubModChat.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.lang.management.ManagementFactory;


/**
 *
 * @author connor
 */
public class TopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        int pid = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        try {
            Process p = Runtime.getRuntime().exec("top -n 1 -b -p " + pid);
            BufferedReader pin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = pin.readLine()) != null) {
                cs.sendMessage(line);
            }
        } catch (IOException e) {
            cs.sendMessage(ChatColor.RED + "Error: " + e.getLocalizedMessage());
        }
        return true;
    }
    
}
