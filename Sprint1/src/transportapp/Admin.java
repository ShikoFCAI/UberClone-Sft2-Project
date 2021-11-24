package transportapp;

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

	
	public ArrayList<User>users= new ArrayList<>();

	public ArrayList<User>systemcustomers= new ArrayList<>();
	//public ArrayList<Driver>pendingdrivers= new ArrayList<>();
	public ArrayList<Driver>systemdrivers= new ArrayList<>();
	public ArrayList<Admin>Admins= new ArrayList<>();
	
	public void Adminmenue(Admin a)
	{
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
				VerifyDriver(driver);
			}
			else if(des==2)
			{
				SusbendUser(a);
			}
			else if(des==3)
			{
				requestRide(a);
			}
			else if(des==4)
			{
				break;
			}
			
		}
			
	}


	public void VerifyDriver(Driver driver)
	{
		systemdrivers.add(driver);
		users.add(driver);
		System.out.println("The driver "+driver.getUsername()+" has been added to system drivers");
		driver.ResponseDriver("You are apprroved to join application ");	
			
	}
	
	public void SusbendUser(Admin a)
	{
		System.out.println("Enter the mobile number of user you want to susbend");
		String username;
		String hisphone=input1.nextLine();

		for(int i=0;i<a.users.size();i++)
		{
			if(a.users.get(i).getMobilenumber().equalsIgnoreCase(hisphone))
			{
				username=a.users.get(i).getUsername();
				a.users.remove(i);
				System.out.println(username+"'s account has been susbended successfully");
				break;
			}
		}
	}
	
}
