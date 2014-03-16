package com.mithrilnetwork.mc.commandregister.commands;

import com.mithrilnetwork.mc.commandregister.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandCMDRegister implements CommandExecutor
{
    Main plugin;

    public CommandCMDRegister(Main plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("cmdregister") || cmd.getName().equalsIgnoreCase("cr"))
        {
            if (sender.isOp() || sender.hasPermission("commandregister.command.cmdregister"))
            {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("reload"))
                    {
                        plugin.reloadConfig();
                        Main.cmdMap = new HashMap<>();
                        plugin.loadCommands();
                        sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "CommandRegister" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + "Config reloaded.");
                    } else
                    {
                        sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "CommandRegister" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + "/cr reload " + ChatColor.WHITE + "- Reloads the configuration.");
                    }
                } else
                {
                    sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "CommandRegister" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW + "/cr reload " + ChatColor.WHITE + "- Reloads the configuration.");
                }
            } else
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            }
            return true;
        }
        return false;
    }
}
