package com.mithrilnetwork.mc.commandregister.listeners;

import com.mithrilnetwork.mc.commandregister.CustomCommand;
import com.mithrilnetwork.mc.commandregister.Main;
import com.mithrilnetwork.mc.commandregister.utils.JSONUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener
{
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        String cmd = event.getMessage().substring(1).toLowerCase().split(" ")[0];

        if (Main.cmdMap.containsKey(cmd))
        {
            Bukkit.getLogger().info(player.getName() + " issued server command: " + event.getMessage());
            CustomCommand cc = Main.cmdMap.get(cmd);
            if (player.hasPermission(cc.getPermissionNode()) || cc.getPermissionNode() == null || cc.getPermissionNode() == "" || cc.getPermissionNode() == " " || cc.getPermissionNode().isEmpty())
            {
                if (cc.isUsingJson() && Main.isProtocolLibPresent)
                {
                    for (String line : cc.getLines())
                    {
                        if (!line.startsWith("{"))
                        {
                            player.sendMessage(Main.parseColor(line));
                        } else
                        {
                            JSONUtils.sendJSONMessage(player, Main.parseColor(JSONUtils.JSONSafe(line)));
                        }
                    }
                } else
                {
                    for (String line : cc.getLines())
                    {
                        player.sendMessage(Main.parseColor(line));
                    }
                }
            } else
            {
                player.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
            }
            event.setCancelled(true);
        }
    }
}
