package me.benfah.resourceloader.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.inventory.ItemStack;

public class Utils
{

	
	
	public static boolean isCustomItem(ItemStack item, short damage)
	{
		if(item.getItemMeta().spigot().isUnbreakable())
			if(item.getDurability() == damage)
				return true;
		return false;
	}
	public static boolean isValidURL(String httpLink)
	{
		try {
		    URL url = new URL(httpLink);
		    URLConnection conn = url.openConnection();
		    conn.connect();
		    return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
}
