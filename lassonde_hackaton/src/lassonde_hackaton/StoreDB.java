package lassonde_hackaton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StoreDB
{
	private ArrayList<Store> stores;
	private File storeDB;
	
	public StoreDB() 
	{
		stores = new ArrayList<Store>();
		storeDB = new File("src/lassonde_hackaton/storeDB.txt");
		
			load();
		
		
	
	}
	
	 private void load() 
	    {
			//name, address, status, name-desc-cost-#inStock
		 try {
	    	Scanner scan = new Scanner(storeDB);
	    	
	    	while(scan.hasNextLine())
	    	{
	    		String[] s = scan.nextLine().split(",");
	    			    		
	    		Store store = new Store(s);
	    		stores.add(store);
	    		
	    	}
	    	scan.close();
		 } catch(FileNotFoundException e) {
			 
		 }
		 
		}
	 
	 public void save() throws IOException
	 {
			BufferedWriter save = new BufferedWriter(new FileWriter(storeDB));
			
		
			HashMap<Product, Integer> inv = new HashMap<Product, Integer>();
			
			for(Store s: stores)
			{
				//name-desc-cost-#inStock
				inv = s.getInventory();
				String strInv = "";
				
				for(Product p: inv.keySet())
				{
					strInv += p.getName()+"-"+p.getDescription()+"-"+p.getCost()+"-"+inv.get(p)+",";
				}
				
				strInv = strInv.substring(0, strInv.length()-1);
			
				save.write(s.getName()+","+s.getAddress()+","+s.getStatus()+","+strInv);
				save.newLine();
			}
			
			save.close();
	 }
	 
	 public ArrayList<Store> getStores()
	 {
		 return stores;
	 }
}
