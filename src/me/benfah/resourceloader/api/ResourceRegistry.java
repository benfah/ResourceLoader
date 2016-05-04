package me.benfah.resourceloader.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ResourceRegistry
{
	public static ArrayList<String> resourceList = new ArrayList<String>();
	public static ArrayList<String> oldResourceList = new ArrayList<String>();
	
	
	public static void add(String httpLink)
	{
		resourceList.add(httpLink);
	}
	
	public static void addAll(String... httpLink)
	{
		for(String s : httpLink)
		{
		resourceList.add(s);
		}
	}
	
	public static void shutdown() throws IOException 
	{
		FileOutputStream fos = new FileOutputStream(new File("plugins/ResourceLoader/lastList.dat"));
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(resourceList);
		oos.close();
		fos.close();
	}
	@SuppressWarnings("unchecked")
	public static void init() throws IOException, ClassNotFoundException 
	{
		FileInputStream fis = new FileInputStream(new File("plugins/ResourceLoader/lastList.dat"));
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		fis.close();
		oldResourceList = (ArrayList<String>) obj;
	}
	public static void remove(String httpLink)
	{
		resourceList.remove(httpLink);
	}
	
	public static void removeAll(String... httpLink)
	{
		for(String s : httpLink)
		{
		resourceList.remove(s);
		}
	}
	
	
}
