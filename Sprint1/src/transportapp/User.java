package transportapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {
	
	static Scanner input = new Scanner(System.in);

    private String username;
    private String mobilenumber;
    private String email;
    private String password;
    private String identity;


    public User() {
    	this.username=null;
    	this.mobilenumber=null;
    	this.email=null;
    	this.password=null;
    	this.identity=null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getUsername() {
        return username;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean validuser(String mobilephone)
    {
    	Admin admin= new Admin();
    	boolean alreadyregistred=true;
    	
    	for(int i=0; i<admin.users.size() ;i++) 
    	{
    		String subscriber=admin.users.get(i).getMobilenumber();
    		
    		if(subscriber.equalsIgnoreCase(subscriber))
    		{
    			alreadyregistred= false;
    		}
    	}
		
    	return alreadyregistred;
    }
    
   public void RegisterDiver(Admin admin)
   {
		    //Admin admin= new Admin();
		   
		    Driver drive=new Driver();
			System.out.println("Enter your user name");
			String username=input.nextLine();
			drive.setUsername(username);
			
			System.out.println("Enter your national id");
			String nationalid=input.nextLine();
			drive.setNationalid(nationalid);
			
			System.out.println("Enter your license number");
			String license=input.nextLine();
			drive.setMobilenumber(license);
			
			
			System.out.println("Enter your mobile number");
			String mobile=input.nextLine();
			drive.setMobilenumber(mobile);

			System.out.println("Do you want to register your email (Yes/No)");
			String answer=input.nextLine();
			
			if(answer.equalsIgnoreCase("yes")) 
			{
				String mail=input.nextLine();
				drive.setEmail(mail);
			}
			
			else if(answer.equalsIgnoreCase("no")) {}
			
			System.out.println("Enter your Pssword");
			String pass=input.nextLine();
			drive.setPassword(pass);
			
			drive.setIdentity("driver");
			
			if(validuser(drive.getMobilenumber()))
			{
				admin.VerifyDriver(drive);
				System.out.println("Waiting until the admin approve you...");
			}
			else
			{
				System.out.println("This mobile number is already registered");
			}
			
     }
   
   public void RegisterCustomer(Admin admin)
   {
		   //Admin admin= new Admin();
		   
		    Customer c= new Customer();
			System.out.println("Enter your user name");
			String username=input.nextLine();
			username=input.nextLine();
			c.setUsername(username);
			
			System.out.println("Enter your mobile number");
			String mobile=input.nextLine();
			c.setMobilenumber(mobile);
			
			System.out.println("Do you want to register your email (Yes/No");
			String answer=input.nextLine();
			
			if(answer.equalsIgnoreCase("yes")) 
			{
				String mail=input.nextLine();
				c.setEmail(mail);
			}
			else if(answer.equalsIgnoreCase("no")) {}
			
			System.out.println("Enter your Pssword");
			String pass=input.nextLine();
			c.setPassword(pass);
			
			c.setIdentity("customer");
			
			if(validuser(c.getMobilenumber()))
			{
				admin.systemcustomers.add(c);
				admin.users.add(c);
				System.out.println("Your registeration done successfully ");
				Login(admin);
			}
			else 
			{
				System.out.println("This mobile number is already registered");
			}
			
   }
    
    
    public void Registeration(Admin admin)
    {
    	int choice=0;
    	
    	do {
	    		System.out.println("Enter your role: (1).for Driver  (2).for Customer ");
	        	choice= input.nextInt();
	        	
	        	if(choice==1)
	        	{
	        		RegisterDiver(admin);
	        	}
	        	
	        	else if(choice==2) 
	        	{
	        			RegisterCustomer(admin);
	        	}
    	 }while(choice!=1 || choice!=2);	
    }
    
    public void Login(Admin a)
    {
	       System.out.println("**************************Login Page**************************");
	       System.out.println("                   "); 
	       boolean done=false;
	       
	        //Admin ad=new Admin();
	    	System.out.println("Enter your mobile number:");
	    	String loginmobile=input.nextLine();
	    	loginmobile=input.nextLine();
	    	System.out.println("");
	    	
	    	System.out.println("Enter your Pasword:");
	    	String lginpassword=input.nextLine();
	    	//lginpassword=input.nextLine();
	    	int s=a.users.size();
	    	
	    	for(int i=0 ; i<s ;i++)
	    	{
	    		User u=new User();
	    		u=a.users.get(i);
	    		if(u.getMobilenumber().equalsIgnoreCase(loginmobile) && u.getPassword().equalsIgnoreCase(lginpassword))
	    		{
	    			if(u.getIdentity().equalsIgnoreCase("driver"))
	    			{
	    				Driver dr=new Driver();
	    				dr.DriverMenue(a);
	    				
	    			    done=true;
	    			}
	    			
	    			else if(u.getIdentity().equalsIgnoreCase("customer")) 
	    			{
	    				//Customer customer=new Customer();
	    				UserMenue(a);
	    				done=true;
	    			}
	    			else if(u.getIdentity().equalsIgnoreCase("admin")) 
	    			{
	    				Admin admin=new Admin();
	    				admin.Adminmenue(a);
	    				done=true;
	    			}
	    		}
	        }
	    	
	    	if(done==false)
	        {
	        	 System.out.println("       Failed login");
	        	 Login(a);
	        }		 	
    }
    
    public void requestRide(Admin a)
	{
		HashMap<String, String>Areas = new HashMap<String, String>();
		
		System.out.println("Please select your source area..");
		String source=input.nextLine();
		source=input.nextLine();
		
		System.out.println("Please select your destinatin area..");
		String destination=input.nextLine();
		destination=input.nextLine();
		
		Areas.put(source, destination);
		System.out.println("Your Ride Request has been received");
		
		NotifyDrivers(source,a);
	}
    
    public void NotifyDrivers(String source, Admin a)
	{
		Driver d = new Driver();
		d.RecieveRequests(source,a);	
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
	
	public void UserMenue(Admin a)
	{
		System.out.println("            User Menue        ");
		int des;
		while (true)
		{
			System.out.println("1. Request Ride");
			System.out.println("2. Logout");

			des=input.nextInt();
			if(des==1) {
				requestRide(a);
			}
			else if(des==2) {
            	System.out.println(" Thanks for Using our up, Nice trip");
            	break;
			}
		}
		
	}
}
