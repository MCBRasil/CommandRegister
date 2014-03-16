package com.mithrilnetwork.mc.commandregister.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class JSONUtils
{
    public static PacketContainer chat = new PacketContainer(PacketType.Play.Server.CHAT);

    public static String JSONSafe(String msg)
    {
        msg = msg.replace("\\", "\\\\");
        msg = msg.replace("'", "\\\'");
        return msg;
    }

    public static void sendJSONMessage(Player player, String msg)
    {
        chat.getChatComponents().write(0, WrappedChatComponent.fromJson(msg));

        try
        {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, chat);
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
