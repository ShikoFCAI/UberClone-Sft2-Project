package transport2;

import java.util.*;

public class Customer extends User  {
	Scanner input= new Scanner(System.in);
	
	/*public void requestRide()
	{
		HashMap<String, String>Areas = new HashMap<String, String>();
		
		System.out.println("Please select your source area..");
		String source=input.nextLine();
		
		System.out.println("Please select your destinatin area..");
		String destination=input.nextLine();
		
		Areas.put(source, destination);
		
		NotifyDrivers(source);
	}
	
	public void NotifyDrivers(String source)
	{
		Driver d=new Driver();
		d.RecieveRequests(source);	
	}
	
	public void RateDriver(Driver d)
	{
		System.out.print("Rate Your driver: ");
		float r=input.nextFloat();
		if(r>=1&&r<=5)
		{
			d.rates.add(r);
		}
	}
	
	public void UserMenue()
	{
		System.out.println("            User Menue        ");
		Customer cust1=new Customer();
		int des;
		while (true)
		{
			System.out.println("1. Request Ride");
			System.out.println("2. Logout");

			des=input.nextInt();
			if(des==1) {
				cust1.requestRide();
			}
			else if(des==2) {
            	System.out.println(" Thanks for Using our up, Nice trip");
            	break;
			}
		}
		
	}*/

}
