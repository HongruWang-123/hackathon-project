package lassonde_hackaton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class MainDemo {

	public static void main(String[] args)
	{
		String username;
		String email;
		String name;
		String address;
		String paymentInfo;
		String gender;
		String password;
		Order shoppingOrder = new Order();
		UserDB b = new UserDB();
		StoreDB sdb= new StoreDB();
		User u = new User();
		User new1 = new User();
		System.out.println("Welcome To Medly\nOrder over the counter medicine and pick up perscriptions online\n");
		System.out.println("New User? Press 1 to sign up.\nAlready have an account? Press 2 to login.");
		Scanner userinput = new Scanner(System.in);  // Create a Scanner object

		int option = userinput.nextInt();  // Read user input

		if(option == 1)
		{
			userinput.nextLine();

			System.out.println("Enter your username: ");
			username = userinput.nextLine();
			new1.setUsername(username);

			System.out.println("Enter your password: ");
			password = userinput.nextLine();

			System.out.println("Enter your email: ");
			email = userinput.nextLine();
			new1.setEmail(email);

			System.out.println("Enter your name: ");
			name = userinput.nextLine();
			new1.setName(name);

			System.out.println("Enter your address: ");
			address = userinput.nextLine();
			new1.setName(address);

			System.out.println("Enter your paymentInfo: ");
			paymentInfo = userinput.nextLine();
			new1.setPaymentInfo(paymentInfo);

			System.out.println("Enter your gender: ");
			gender = userinput.nextLine();
			new1.setGender(gender);

			System.out.println("Confirm the information then to save it press 3");
			int option2 = userinput.nextInt();

			if(option2 == 3) 
			{
				b.addUser(new1, username, password);
				try {	b.save();} catch (IOException e) {e.printStackTrace();	}
			}
			else 
			{
				System.out.println("you are not choosing the right button, bye");
				System.exit(0);
			}
		}
		else if(option == 2) {

			userinput.nextLine();

			System.out.println("Enter your username: ");
			username = userinput.nextLine();

			System.out.println("Enter your password: ");
			password = userinput.nextLine();

			try 
			{	
				u =new User( b.login(username, password));
			}
			catch(InvalidLoginException e)
			{
				System.out.println("Wrong login info, bye");
				System.exit(0);
			}

		}
		else 
		{
			System.out.println("Enter the right button, Bye");
			System.exit(0);
		} 
		//the user is now sign'd up or logged in - options page
		User u2 = u.getUserName()==null? new1: u; 
		//u2 is the signed in user

		int option3;
		do
		{
			System.out.println("Welcome," + u2.getName());
			System.out.println("What do you want to do?\n 1.check your orders\n 2.check stores and buy\n 3.to exit");
			System.out.println("Enter the option by press the number");

			option3 = userinput.nextInt(); 

			if(option3 == 1) 
			{

				if(u2.getOrder() == null )
				{
					System.out.println(u2.getName()+", you have no orders");
				}
				else
				{
					Order o2 = (u.getOrder());

					//System.out.println("your store is " + o2.getStore().getName());
					System.out.println("your delivery status is: not purchased yet" );
					System.out.println("your driver is: Dragon"  );
					System.out.println("-------------------------------------");
					
					for(Product p : o2.getOrders().keySet()) 
					{
						System.out.println("-"+p.getName()+" x"+o2.getOrders().get(p));
					}
					
					System.out.println("-------------------------------------\n");
					System.out.println("the total price for your purchase is " + o2.givePrice());
					System.out.println("Confirm your purchase: yes or no");

					userinput.nextLine();
					String confirmation = userinput.nextLine();
					if(confirmation.equals("yes")) {

						for(Product p : o2.getOrders().keySet()) 
						{
						//o2.getStore().deleteInventory(p, o2.getNumber(p));
						}

						try 
						{
							sdb.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//System.out.println("your order is complete!");
						}
						
						System.out.println("your order is complete!");
						
						System.exit(0);
					}
				}
			}
			else if(option3 == 2) 
			{   
				int option4 = 1;

				System.out.println("----------------------");
				//display the stores + products
				ArrayList<Store> stores = sdb.getStores();

				for(int i=0; i< stores.size(); i++)
				{
					System.out.println((i+1)+"."+stores.get(i).getName()+", "+stores.get(i).getAddress());

				}

				System.out.println("\nselect a store to make a purchase at");

				//select store
				int storeSelection = userinput.nextInt();

				if(storeSelection> stores.size())
				{
					System.out.println("invalid, good bye");
					System.exit(0);
				}

				Store selectedStore = stores.get(storeSelection-1);

				for(Product p: selectedStore.getInventory().keySet())
				{
					System.out.println(p.getName()+", "+p.getDescription()+": "+p.getCost());
				}

				System.out.println("\ntype -1 to go back to home\nOr type the product name that you want");

				userinput.nextLine();
				String orderSelection = userinput.nextLine();

				if(!orderSelection.equals("-1"))
				{
					System.out.println("enter how many you want (>=1)");
					int n = userinput.nextInt();
					
					if(stores.get(storeSelection-1).getInventory().get(selectedStore.getProduct(orderSelection)) < n) {
						System.out.println("Sorry, the number of product is not enough, try another store, please try another store");
						System.exit(0);
					}
					shoppingOrder.addProduct(selectedStore.getProduct(orderSelection), n);
					shoppingOrder.setStore(selectedStore);

					u2.setOrder(shoppingOrder);


				}


			}
			else if(option3 == 3)
			{
				System.out.println("Good bye!");
				System.exit(0);
			}
			else
			{
				//invalid
				System.out.println("Good bye!");
				System.exit(0);
			}

			System.out.println("\n\n-------------------");
		} while(option3 != 3);
		userinput.close();

	}

}