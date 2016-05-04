package me.benfah.resourceloader.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.zeroturnaround.zip.ZipUtil;

import me.benfah.resourceloader.api.ResourceRegistry;

public class ZipMerger {   
    
	public static ArrayList<File> fileList = new ArrayList<File>();
	
	public static File xtraFile;
	
    public static boolean initMerge()
    {
    	try {
    		
			startDownload();
	    	unpackAndPackAll();
	    	return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    public static void startDownload() throws Exception
    {
    	if(!ResourceRegistry.oldResourceList.equals(ResourceRegistry.resourceList))
    	{
    		log("Starting to download Zips.");
	
		    	for(String s : ResourceRegistry.resourceList)
		    	{
		    		
			    	URL website = new URL(s);
					ReadableByteChannel rbc = Channels.newChannel(website.openStream());
					String uuid = UUID.randomUUID().toString();
					File f = new File("plugins/ResourceLoader/" + uuid + ".zip");
					FileOutputStream fos = new FileOutputStream(f);
					
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					fos.close();
					if(s.contains("loua16pv2olcvxq"))
		    		{
		    			xtraFile = new File("plugins/ResourceLoader/" + uuid + ".zip");
		    		}
					else
						fileList.add(f);

					
		    	}
    	
	    	log("Done!");
    	}
    	else
    	log("All Resourcepacks are up to date! Skipping.");
    }
    public static void unpackAndPackAll() throws IOException
    {
    	if(!ResourceRegistry.oldResourceList.equals(ResourceRegistry.resourceList))
    	{
	    	log("Merging all ZIPs.");
	    		
	    		ZipUtil.unpack(xtraFile, new File("resourcecache"));
    	
		    	for(File f : fileList)
		    	{
		    		ZipUtil.unpack(f, new File("resourcecache"));
		    	}
		    	String s = "finalRP-" + UUID.randomUUID().toString().replace("-", "").substring(10) + ".zip";
		    	ZipUtil.pack(new File("resourcecache"), new File("plugins/ResourceLoader/" + s));
		    log("Done!");
		    log("Please upload the " + s + " file to DropBox and set the link in the config.yml!");
    	}
    	else
    	{
    		log("All is up to date! Skipping.");
    	}
    }
    
    public static void log(String msg)
	{
		Bukkit.getLogger().log(Level.INFO, msg, true);

	}
}