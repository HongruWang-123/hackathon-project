package lassonde_hackaton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Store 
{
	private String name;
	private String address;
	private HashMap<Product, Integer> inventory;
	private ArrayList<Product> products;
	private boolean status;
	private ArrayList<Order> orders;
	

	public Store() 
	{
		inventory = new HashMap<Product, Integer>();
		orders = new ArrayList<Order>();
		products = new ArrayList<Product>();
		
	}
	public Store(String[] s) 
	{
		this();
		//name, address, status, inventory
		
		name = s[0];
		address = s[1];
		status = s[2].equalsIgnoreCase("true")? true:false;
		
		for(int i=3; i< s.length; i++)
		{
			// this will be the inventory;
			String[] inv = s[i].split("-");
			//name-desc-cost-#inStock
			
			Product p = new Product(inv);
			products.add(p);
			Integer amount = Integer.parseInt(inv[3]);
			
			inventory.put(p, amount);
			
		}
		
	}
	
	public Product getProduct(String name)
	{
		Product p = new Product();;
		
		
		for(int i =0; i< products.size(); i++)
		{
			if(products.get(i).getName().equals(name))
			{
				p = products.get(i);
				break;
			}
		}
		
		return p;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public HashMap<Product, Integer> getInventory() {
		return inventory;
	}
	public void setInventory(HashMap<Product, Integer> inventory) {
		this.inventory = inventory;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public void addInventory(Product product,int number) throws InvalidNumberException{
		if(number < 0) {
			throw new InvalidNumberException("the number of product is not positive");
		}
		int result = this.inventory.get(product) + number;
		this.inventory.replace(product, result);
	}

	public void deleteInventory(Product product,int number) throws InvalidNumberException {
		if(number > inventory.get(product)) {
			//throw new InvalidNumberException("the number of product is not valid");
		}
		int result = this.inventory.get(product) - number;
		this.inventory.replace(product, result);
	}
}