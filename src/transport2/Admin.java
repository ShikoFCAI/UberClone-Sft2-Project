package transport2;

import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends User implements AdminInterface{
	
	static Scanner input1=new Scanner(System.in);
	
	private String identity="admin";
	
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	
	/*public ArrayList<User>users= new ArrayList<>();

	public ArrayList<User>systemcustomers= new ArrayList<>();
	public ArrayList<Driver>pendingdrivers= new ArrayList<>();
	public ArrayList<Driver>systemdrivers= new ArrayList<>();
	public ArrayList<Admin>Admins= new ArrayList<>();*/
	
	public void Adminmenue(DataBase db)
	{
		boolean loggedout = false;

		while(true)
		{
			Driver driver =new Driver();
			System.out.println("   Admin Menue   ");
			System.out.println("1. Verify the pending drivers");
			System.out.println("2. Suspend Account");
			System.out.println("3. Request Ride");
			System.out.println("4. Logout");
			int des=input.nextInt();
			if(des==1)
			{
				VerifyDrivers(db);
			}
			else if(des==2)
			{
				SusbendUser(db);
			}
			else if(des==3)
			{
				requestRide(db);
			}
			else if(des==4)
			{
				loggedout=true;
				break;
			}		
		}
		
		if(loggedout == true) {
	    	Login(db);
	    }

			
	}

	/*public void VerifyDrivers(Driver driver)
	{
		systemdrivers.add(driver);
		users.add(driver);
		System.out.println("The driver "+driver.getUsername()+" has been added to system drivers");
		driver.ResponseDriver("You are apprroved to join application ");	
			
	}*/
	
	public void VerifyDrivers(DataBase db)
	{
		System.out.println("approved / not approved");
		System.out.println("The new drivers are: ");
		String decision;
		
		for(int x=0; x<db.pendingdrivers.size(); x++)
		{
			//Driver y = new Driver();
			//y = pendingdrivers.get(x);
			System.out.println("driver"+(x+1)+": is "+db.pendingdrivers.get(x).getUsername());
			System.out.println("his mobile number is: "+db.pendingdrivers.get(x).getMobilenumber()+" with liscence number "+db.pendingdrivers.get(x).getLicensenum()+" and national id "+db.pendingdrivers.get(x).getNationalid());
			
			decision= input1.nextLine();
			
			if(decision.equalsIgnoreCase("approved")) 
			{
				db.systemdrivers.add(db.pendingdrivers.get(x));
				db.users.add(db.pendingdrivers.get(x));
			}
			else {
				
				continue;
			}
		}
	}
	
	public void SusbendUser(DataBase db)
	{
		System.out.println("Enter the mobile number of user you want to susbend");
		String username;
		String hisphone=input1.nextLine();

		for(int i=0;i<db.users.size();i++)
		{
			if(db.users.get(i).getMobilenumber().equalsIgnoreCase(hisphone))
			{
				username=db.users.get(i).getUsername();
				db.users.remove(i);
				if(db.users.get(i).getIdentity().equalsIgnoreCase("driver"))
				{
					db.systemdrivers.remove(i);
				}
				else if(db.users.get(i).getIdentity().equalsIgnoreCase("customer")) {
					db.systemcustomers.remove(i);
				}
				System.out.println(username+"'s account has been susbended successfully");
				break;
			}
		}
	}


}
