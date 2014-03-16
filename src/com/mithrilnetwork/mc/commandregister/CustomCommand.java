package com.mithrilnetwork.mc.commandregister;

import java.util.List;

public class CustomCommand
{
    private Main plugin;
    private String cmdName;
    private List<String> lines;
    private String permissionNode = "";
    private Boolean json = false;

    public CustomCommand loadCommand(Main plugin, String cmdName)
    {
        this.plugin = plugin;
        this.cmdName = cmdName;
        permissionNode = plugin.getConfig().getString("commands." + cmdName + ".permission").toLowerCase();
        lines = plugin.getConfig().getStringList("commands." + cmdName + ".lines");
        json = plugin.getConfig().getBoolean("commands." + cmdName + ".json");

        return this;
    }

    public String getCmdName()
    {
        return cmdName;
    }

    public List<String> getLines()
    {
        return lines;
    }

    public String getPermissionNode()
    {
        return permissionNode;
    }

    public Boolean isUsingJson()
    {
        return json;
    }
}
