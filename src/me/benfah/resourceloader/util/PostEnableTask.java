package me.benfah.resourceloader.util;

import java.io.File;
import java.io.IOException;

import me.benfah.resourceloader.api.ResourceRegistry;

public class PostEnableTask implements Runnable{

	@Override
	public void run()
	{
		if(new File("plugins/ResourceLoader", "lastList.dat").exists())
		{
			try {
				ResourceRegistry.init();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
			
		try {
			ZipMerger.initMerge();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
