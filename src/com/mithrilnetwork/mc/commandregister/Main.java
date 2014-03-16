package com.mithrilnetwork.mc.commandregister;

import com.mithrilnetwork.mc.commandregister.commands.CommandCMDRegister;
import com.mithrilnetwork.mc.commandregister.listeners.CommandListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin
{
    public static HashMap<String, CustomCommand> cmdMap = new HashMap<>();
    public static boolean isProtocolLibPresent = false;

    public void onEnable()
    {
        if (getConfig().getConfigurationSection("commands").getKeys(false).size() == 0)
        {
            this.saveDefaultConfig();
            this.getConfig().options().copyDefaults(true);
        }
        this.getConfig().get("commands");
        this.saveConfig();

        loadCommands();

        this.getServer().getPluginManager().registerEvents(new CommandListener(), this);
        this.getCommand("cmdregister").setExecutor(new CommandCMDRegister(this));
        this.getCommand("cr").setExecutor(new CommandCMDRegister(this));

        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null)
        {
            getLogger().info("ProtocolLib is installed, enabling json features.");
            isProtocolLibPresent = true;
        }
    }

    public void loadCommands()
    {
        for (String cmdName : getConfig().getConfigurationSection("commands").getKeys(false))
        {
            cmdMap.put(cmdName, new CustomCommand().loadCommand(this, cmdName));
        }
    }

    public static String parseColor(String message)
    {
        message = message.replaceAll("&0", ChatColor.BLACK + "");
        message = message.replaceAll("&1", ChatColor.DARK_BLUE + "");
        message = message.replaceAll("&2", ChatColor.DARK_GREEN + "");
        message = message.replaceAll("&3", ChatColor.DARK_AQUA + "");
        message = message.replaceAll("&4", ChatColor.DARK_RED + "");
        message = message.replaceAll("&5", ChatColor.DARK_PURPLE + "");
        message = message.replaceAll("&6", ChatColor.GOLD + "");
        message = message.replaceAll("&7", ChatColor.GRAY + "");
        message = message.replaceAll("&8", ChatColor.DARK_GRAY + "");
        message = message.replaceAll("&9", ChatColor.BLUE + "");
        message = message.replaceAll("(?i)&a", ChatColor.GREEN + "");
        message = message.replaceAll("(?i)&b", ChatColor.AQUA + "");
        message = message.replaceAll("(?i)&c", ChatColor.RED + "");
        message = message.replaceAll("(?i)&d", ChatColor.LIGHT_PURPLE + "");
        message = message.replaceAll("(?i)&e", ChatColor.YELLOW + "");
        message = message.replaceAll("(?i)&f", ChatColor.WHITE + "");
        message = message.replaceAll("(?i)&l", ChatColor.BOLD + "");
        message = message.replaceAll("(?i)&o", ChatColor.ITALIC + "");
        message = message.replaceAll("(?i)&m", ChatColor.STRIKETHROUGH + "");
        message = message.replaceAll("(?i)&n", ChatColor.UNDERLINE + "");
        message = message.replaceAll("(?i)&k", ChatColor.MAGIC + "");
        message = message.replaceAll("(?i)&r", ChatColor.RESET + "");
        return message;
    }
}
