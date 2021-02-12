package lassonde_hackaton;

public class Product
{
	private String description;
	private String name;
	private double cost;
	
	
	public Product()
	{
		
	}
	public Product(String[] inv) 
	{
		// name-desc-cost-#inStock
		super();
		name = inv[0];
		description = inv[1];
		cost = Double.parseDouble(inv[2]);
		
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
