package lassonde_hackaton;

import java.util.ArrayList;
import java.util.HashMap;

public class Order
{
    private HashMap<Product,Integer> orders;
    private Store store;
    private String deliveryStatus;
    private Driver driver;

    public Order() {
    	orders = new HashMap<Product, Integer>();
    }
    public Order(Order o) {
        this.orders = o.orders;
        this.store = o.store;
        this.deliveryStatus = o.deliveryStatus;
        this.driver = o.driver;
    }
    public HashMap<Product,Integer> getOrders() {
        return orders;
    }
    
    public void addProduct(Product p, int n)
    {
    	orders.put(p, n);
    }
    public void setOrders(HashMap<Product,Integer> orders) {
        this.orders = orders;
    }
    public boolean check(){
        boolean result = true;
        if(!store.getStatus()) {
            result = false;
            return result;
        }
        for(Product p:orders.keySet()) {
            if(orders.get(p) > store.getInventory().get(p)) {
                result = false;
                return result;
            }
        }
        return result;
    }
    public double givePrice() {
        ArrayList<Integer> value = new ArrayList<Integer> (orders.values());
        ArrayList<Product> key = new ArrayList<Product> (orders.keySet());
        double result = 0;
        for(int i = 0; i<orders.size();i++) {
            result = result + key.get(i).getCost()*value.get(i);
        }
        return result;
    }
    
    public int getNumber(Product p)
    {
    	return orders.get(p);
    }

    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    } 



}