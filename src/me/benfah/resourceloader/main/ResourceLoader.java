package me.benfah.resourceloader.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.benfah.resourceloader.api.ResourceRegistry;
import me.benfah.resourceloader.command.CommandResource;
import me.benfah.resourceloader.listener.PlayerMoveListener;
import me.benfah.resourceloader.util.PostEnableTask;
import me.benfah.resourceloader.util.ZipMerger;

public class ResourceLoader extends JavaPlugin{
	
	@Override
	public void onEnable()
	{
		getCommand("resource").setExecutor(new CommandResource());
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		if(!getDataFolder().exists())
		getDataFolder().mkdirs();
		ResourceRegistry.add("https://www.dropbox.com/s/loua16pv2olcvxq/XtraPack.zip?dl=1");
		
		if(!new File(getDataFolder(), "config.yml").exists())
		saveDefaultConfig();
		
		if(getConfig().getString("resourcepack-link").endsWith("?dl=0"))
		{
			getConfig().set("resourcepack-link", getConfig().getString("resourcepack-link").substring(0, getConfig().getString("resourcepack-link").length() - 1) + "1");
			saveConfig();

		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new PostEnableTask());
		
	}
	public static void log(String msg)
	{
		Bukkit.getLogger().log(Level.INFO, msg, true);

	}
	@Override
	public void onDisable()
	{
		try {
			ResourceRegistry.shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
