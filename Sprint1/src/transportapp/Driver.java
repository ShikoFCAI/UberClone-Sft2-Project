package transportapp;

import java.util.*;

public class Driver extends User implements DriverInterface{
	
	private String nationalid;
    private String licensenum;
    private boolean state;
    
    public ArrayList<Float> rates=new ArrayList<Float>();;
    
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	//The string is source area that is stored in Favorite areas
	HashMap<String, Integer>FavoriteAreas = new HashMap<String, Integer>();

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
    
    @Override
   	public void ResponseDriver(String approving) {
   		System.out.println(approving);
   	}

    
    public void AddFavoiteArrea()
    {
    	System.out.println("Enter your favorite area:");
    	String favoritearea=input.nextLine();
    	favoritearea=input.nextLine();
    	System.out.println("Enter your offer for this area:");
    	int offer=input.nextInt();
    	
    	FavoriteAreas.put(favoritearea,offer);
    }
    
    public void RecieveRequests(String source, Admin a)
    {
    	String newstate;
    	for(int i=0; i<a.systemdrivers.size();i++)
    	{
    		for(int y=0; y<FavoriteAreas.size(); y++)
    		{
    			//admin.systemdrivers.get(i).FavoriteAreas.get(y).equalsIgnoreCase(source)
    			if(a.systemdrivers.get(i).FavoriteAreas.containsKey(source))
    			{
    				int z= FavoriteAreas.get(source);
    				String drivername=a.systemdrivers.get(i).getUsername();
    				System.out.println("Your ride hass been found with driver "+drivername+" and the cost: "+z);
    				System.out.println("Average Rate: "+calculate_Average_Rate(a.systemdrivers.get(i)));
    				System.out.println("Yes or no");
    				newstate=input.nextLine();
    				if(newstate.equalsIgnoreCase("yes"))
    				{
    					this.setState(true);
    					ResponseRideRequests();
    				    RateDriver(a.systemdrivers.get(i));
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
		
		System.out.println("OK, In the way for you");
	}
	
	public void RateDriver()
	{
		System.out.print("Rate Your driver: ");
		float r=input.nextFloat();
		if(r>=1&&r<=5)
		{
			rates.add(r);
		}
	}
	
	public void ListRating()
	{
		for(float y:rates) {
			System.out.print(y+" ");
		}
	}
	
	public float calculate_Average_Rate(Driver d)
	{
		int total_users_rated=d.rates.size();
		float sum=0;
		for(float y:d.rates)
		{
			sum+=y;
		}
		
		int sum_of_max_rating_of_user_count = total_users_rated*5;
		float rating= (sum*5)/sum_of_max_rating_of_user_count ;
		
		return rating;
	}
	
	public void DriverMenue(Admin ad)
	{
		System.out.println("Driver Menue:        ");
		
		int des;
	    while (true)
		{
			System.out.println("1. Add favorite Area");
			System.out.println("2. List rates");
			System.out.println("3. Request Ride");
			System.out.println("4. Logout");
			des=input.nextInt();
			
			if(des==1) {
				
				AddFavoiteArrea();
			}
			else if(des==2)
			{
				ListRating();
			}
			else if(des==3)
			{
				requestRide(ad);
			}
            else if(des==4) {
            	System.out.println(" Thanks for Using our up, Nice trip");
				break;
			}
		}

	}

}
