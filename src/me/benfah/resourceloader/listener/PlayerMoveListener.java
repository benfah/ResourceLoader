package me.benfah.resourceloader.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import me.benfah.resourceloader.main.ResourceLoader;

public class PlayerMoveListener implements Listener{

	ArrayList<String> pList = new ArrayList<String>();
	
	ResourceLoader inst;
	public PlayerMoveListener(ResourceLoader inst)
	{
		this.inst = inst;
	}
	
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent e)
	{
		if(e.getPlayer().hasPermission("resourceloader.request"))
		{
		Player p = e.getPlayer();
			if(!inst.getConfig().getString("resourcepack-link").equalsIgnoreCase("replace_me"))
			{
				if(!pList.contains(e.getPlayer().getName()))
				{
					p.setResourcePack(inst.getConfig().getString("resourcepack-link"));
					pList.add(p.getName());
				}
				
				
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent e)
	{
		pList.remove(e.getPlayer().getName());
	}
}
