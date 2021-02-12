package lassonde_hackaton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserDB 
{
	private HashMap<String, String> logins;
	private ArrayList<User> users;
	private File loginFile, userFile;

	public UserDB() 
	{
		logins = new HashMap<String, String>();
		users = new ArrayList<User>();

		loginFile = new File("src/lassonde_hackaton/Logins.txt");
		userFile = new File("src/lassonde_hackaton/personDB.txt");
	
		try 
		{
			load();
		}
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
	}


	public void addUser(User u, String userName, String password)
	{
		logins.put(userName, password);
		users.add(u);
		u.setUsername(userName);
	}

	public void deleteUser(User u, String password)
	{
		//if the password matches then delete from db
		if(password.equals(logins.get(u.getUserName())));
		logins.remove(u.getUserName());
		users.remove(u); 

	}

	public User login(String username, String password) throws InvalidLoginException
	{
		// check the user pass combo is correct
		if(!logins.containsKey(username))
			throw new InvalidLoginException("invalid user id or password");
		if(!logins.get(username).equals(password))
			throw new InvalidLoginException("invalid user id or password");

		//find a person with the right login info
		int index = 0;
		boolean found = false;
		for(int i=0; i<users.size() && !found; )
		{
			//System.out.println(users.get(i).getUserName()+", "+username);
			
			if(users.get(i).getUserName().equals(username))
			{
				index = i;
				found = true;
				
			}
			i++;
		}

		if(found)
			return users.get(index);
		else
			return null;

	}

	public void save() throws IOException
	{

		BufferedWriter saveUser = new BufferedWriter(new FileWriter(userFile));
		BufferedWriter saveLogin= new BufferedWriter(new FileWriter(loginFile));

		//write the current user logins and ppl to the txt file
		for(int i=0; i< users.size();i++)
		{
			String s = users.get(i).getallInfo();		
			saveUser.write(s);
			saveUser.newLine();

		}

		for(String s: logins.keySet())
		{
			saveLogin.write(s+","+logins.get(s));
			saveLogin.newLine();
		}
		saveUser.close();
		saveLogin.close();
	}

	private void load() throws FileNotFoundException
	{
		//load in the info then set the obj data for the user list and login map
		Scanner scan = new Scanner(userFile);
		Scanner scan2 = new Scanner(loginFile);

		while(scan.hasNextLine())
		{
			String[] userData = scan.nextLine().split(",");
			User p = new User(userData);   
			users.add(p);   	
		}


		while(scan2.hasNextLine())
		{
			String[] loginInfo = scan2.nextLine().split(",");
			logins.put(loginInfo[0], loginInfo[1]);

		}
		scan.close();
		scan2.close();

	}

	public void changePassword(User u, String currentPass, String newPass) throws InvalidLoginException
	{
		if(!logins.get(u.getUserName()).equals(currentPass))
			throw new InvalidLoginException("invalid user id or password");

		logins.put(u.getUserName(), newPass);
	}
}
