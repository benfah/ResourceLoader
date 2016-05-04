package me.benfah.resourceloader.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.benfah.resourceloader.api.ResourceRegistry;
import me.benfah.resourceloader.util.Utils;
import me.benfah.resourceloader.util.ZipMerger;



public class CommandResource implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("resource"))
		{
			if(args.length >= 1)
			{
				if(args[0].equalsIgnoreCase("addlink") && sender.hasPermission("resourceloader.addlink"))
				{
					if(args.length == 2)
					{
						if(Utils.isValidURL(args[1]))
						{
							ResourceRegistry.add(args[1]);
						}
					}
				}
				else
				if(args[0].equalsIgnoreCase("pack") && sender.hasPermission("resourceloader.pack"))
				{
					ZipMerger.initMerge();
					sender.sendMessage("Done!");
				}
				if(args[0].equalsIgnoreCase("list") && sender.hasPermission("resourceloader.list"))
				{
					sender.sendMessage("Here are all loaded registered resourcepacks:");
					for(int i = 0; i < ResourceRegistry.resourceList.size(); i++)
					{
						String s = ResourceRegistry.resourceList.get(i);
						sender.sendMessage(i + " - " +  s);
					}
				}
			}
		}
		return false;
	}
	
	
	
}
