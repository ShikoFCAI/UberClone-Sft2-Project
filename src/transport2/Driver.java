package transport2;

import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Driver extends User implements DriverInterface{
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();  
	private String nationalid;
    private String licensenum;
    private boolean state;
    
    public ArrayList<Float> rates=new ArrayList<Float>();
	HashMap<String, Integer>FavoriteAreas = new HashMap<String, Integer>();

    
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	//The string is source area that is stored in Favorite areas
    public Driver() {
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public void setLicensenum(String licensenum) {
        this.licensenum = licensenum;
    }

    public String getNationalid() {
        return nationalid;
    }

    public String getLicensenum() {
        return licensenum;
    }
    
    
    public void DriverMenue(DataBase db, Driver dr)
	{
		System.out.println("Driver Menue:        ");
		
		int des;
		boolean loggedout = false;
	    while (true)
		{
			System.out.println("1. Add favorite Area");
			System.out.println("2. List rates");
			System.out.println("3. Request Ride");
			System.out.println("4. Calculate average of your rate");
			System.out.println("5. Logout");
			des=input.nextInt();
			
			if(des==1) {
				
				AddFavoiteArrea();
			}
			else if(des==2)
			{
				ListRating(dr);
			}
			
			else if(des==3)
			{
				 requestRide(db);
			}
			
			else if(des==4)
			{
				System.out.println("Your average rate Mr/ms "+ dr.getUsername() +" is: " +calculate_Average_Rate(dr));
			}
            else if(des==5) {
            	System.out.println(" Thanks for Using our up, Nice trip");
            	loggedout = true;
				break;

            }
		}
	    
	    if(loggedout == true) {
	    	Login(db);
	    }

	}

    
    
    
    
    @Override
   	public void ResponseDriver(String approving) {
   		System.out.println(approving);
   	}

    
    public void AddFavoiteArrea()
    {
    	System.out.println( "How many areas do you want to add" ); 
    	
    	int nums= input.nextInt();
    	
    	for(int i=0;i<nums;i++)
    	{
    		System.out.println("Enter your favorite area:");
        	String favoritearea=input.nextLine();
        	favoritearea=input.nextLine();
        	System.out.println("Enter your price for this area:");
        	int price=input.nextInt();
        	
        	FavoriteAreas.put(favoritearea,price);
    	}
    	
    }
    
    public void RecieveRequests(String source, DataBase db)
    {
    	String newstate;
    	
    	for(int i=0; i<db.systemdrivers.size();i++)
    	{
    		
    		for(int y=0; y<db.systemdrivers.get(i).FavoriteAreas.size(); y++)
    		{
    			
    			//admin.systemdrivers.get(i).FavoriteAreas.get(y).equalsIgnoreCase(source)
    			if(db.systemdrivers.get(i).FavoriteAreas.containsKey(source))
    			{
    				
    				int z= db.systemdrivers.get(i).FavoriteAreas.get(source);
    				String drivername=db.systemdrivers.get(i).getUsername();
    				System.out.println("Your ride hass been found with driver "+drivername+" and the cost: "+z);
    				//System.out.println("Average Rate: "+calculate_Average_Rate(a.systemdrivers.get(i)));
    				System.out.println("Would you like to accept this ride (Yes/No)");
    				System.out.println("The driver has arrived to your location");
    				System.out.println("The Driver's name: " + drivername);
    				System.out.println("The Cost of the ride: " + z + "$");
    				System.out.println("The Time of the ride: " + dtf.format(now));
    				newstate=input.nextLine();
    				
    				if(newstate.equalsIgnoreCase("yes"))
    				{
    					this.setState(true);
    					ResponseRideRequests();
    					DriverArrivedToLocation(drivername, z);
    					DriverArrivedToDest(drivername, z);
    					System.out.println("Do you want to give a rate for the driver: (yes/skip");
    					String agree=input.nextLine();
    					agree=input.nextLine();
    					
    					if(agree.equalsIgnoreCase("yes")) {
        				    RateDriver(db.systemdrivers.get(i));
    					}
    					
    					break;
    				}
    				
    				else{
    					continue;
    				}
    			}
    		}
    	}
    }

	@Override
	public void ResponseRideRequests() {
		System.out.println("OK, On the way to you");
	}
	
	public void DriverArrivedToLocation(String driver, int cost) {
		System.out.println("The driver has arrived to your location");
		System.out.println("The Driver's name: " + driver);
		System.out.println("The Cost of the ride: " + cost + "$");
		System.out.println("The Time of the ride: " + dtf.format(now));
	}
	
	public void DriverArrivedToDest(String driver, int cost) {
		System.out.println("The driver has arrived to your location");
		System.out.println("The Driver's name: " + driver);
		System.out.println("The Cost of the ride: " + cost + "$");
		System.out.println("The Time of the ride: " + dtf.format(now));
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
	public void ListRating(Driver d)
	{
		for(float y:d.rates) {
			System.out.print(y+"  ");
		}
	}
	
	public float calculate_Average_Rate(Driver d)
	{
		int total_users_rated= d.rates.size();
		float sum=0;
		for(float y:d.rates)
		{
			sum+=y;
		}
		
		//int sum_of_max_rating_of_user_count = total_users_rated*5;
		//float rating= (sum*5)/sum_of_max_rating_of_user_count ;
		float rateing = sum/total_users_rated;
		
		return rateing;
	}
	
	
}
