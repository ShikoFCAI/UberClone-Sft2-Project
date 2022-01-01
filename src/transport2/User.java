package transport2;

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
    
    
    
    public boolean Existuser(String mobilephone, DataBase ad)
    {
    	//Admin admin= new Admin();
    	boolean alreadyregistred=false;
    	
    	for(int i=0; i<ad.users.size() ;i++) 
    	{
    		String subscribermobile=ad.users.get(i).getMobilenumber();
    		
    		if(subscribermobile.equalsIgnoreCase(mobilephone))
    		{
    			alreadyregistred=true;  // the new subscriber is already exist
    		}
    	}
		
    	return alreadyregistred;
    }
    
    
    
    public void Login(DataBase db)
    {
		  //Admin a
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
	    	//int s=users.size();
	    	
	    	for(int i=0 ; i<db.users.size() ;i++)
	    	{
	    		//User u=new User();
	    		//u=users.get(i);
	    		if(db.users.get(i).getMobilenumber().equalsIgnoreCase(loginmobile) && db.users.get(i).getPassword().equalsIgnoreCase(lginpassword))
	    		{
	    			if(db.users.get(i).getIdentity().equalsIgnoreCase("driver"))
	    			{
	    				Driver dr=new Driver();
	    				dr= GetDriver(db, db.users.get(i));
	    				dr.DriverMenue(db, dr);
	    			    done=true;
	    			}
	    			
	    			else if(db.users.get(i).getIdentity().equalsIgnoreCase("customer")) 
	    			{
	    				//Customer customer=new Customer();
	    				UserMenue(db);
	    				done=true;
	    			}
	    			else if(db.users.get(i).getIdentity().equalsIgnoreCase("admin")) 
	    			{
	    				Admin ad=new Admin();
	    				//admin.Adminmenue(a);
	    				ad.Adminmenue(db);
	    				done=true;
	    			}
	    		}
	        }
	    	
	    	if(done==false)
	        {
	        	 System.out.println("Failed login");
	        	 Login(db);
	        }		 	
    }
    
    
    
    
    
    public void Registeration(DataBase db)
    {
 	   	int choice=0;
 	   	
 	   	do {
 		    		System.out.println("Enter your role: (1).for Driver  (2).for Customer ");
 		        	choice= input.nextInt();
 		        	
 		        	if(choice==1){
 		        		RegisterDiver(db);
 		        	}
 		        	
 		        	else if(choice==2) {
 		        		RegisterCustomer(db);
 		        	}
 		        	else {
 		        		System.out.println("Please enter a valid choice");
 		        	}
 	   	 }while(choice!=1 || choice!=2);	
    }
    
    
    
    
   public void RegisterDiver(DataBase db)
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
			
			if(!Existuser(drive.getMobilenumber(), db))
			{
				//admin.VerifyDriver(drive);
				db.pendingdrivers.add(drive);
				System.out.println("Waiting until the admin approve you...");
				
				Login(db);
			}
			else
			{
				System.out.println("This mobile number is already registered");
			}
			
     }
   
   
   
   
   public void RegisterCustomer(DataBase db)
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
			
			if(!Existuser(c.getMobilenumber(), db))
			{
				db.systemcustomers.add(c);
				db.users.add(c);
				System.out.println("Your registeration done successfully ");
				Login(db);
			}
			else 
			{
				System.out.println("This mobile number is already registered");
			}
			
   }
    
    
   
   
   public void NotifyDrivers(String source, DataBase db)
  	{
  		Driver d = new Driver();
  		
  		d.RecieveRequests(source,db);	
  		
  		//System.out.println("Can you give your rate about the driver: (yes/No)");
  	}
   
   
   
    public void requestRide(DataBase db)
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
		
		this.NotifyDrivers(source, db);
	}
    
	
	/*public void RateDriver(Driver d)
	{
		System.out.print("Rate Your driver: ");
		float r=input.nextFloat();
		if(r>=1&&r<=5)
		{
			d.rates.add(r);
		}
	}*/
	
	
	public void UserMenue(DataBase db)
	{
		System.out.println("            User Menue        ");
		int des;
		boolean loggedout=false;
		while (true)
		{
			System.out.println("1. Request Ride");
			System.out.println("2. Logout");

			des=input.nextInt();
			if(des==1) {
				requestRide(db);
			}
			else if(des==2) {
            	System.out.println(" Thanks for Using our up, hope you nice trips");
            	loggedout=true;
            	break;
			}
		}
		
		if(loggedout == true) {
	    	Login(db);
	    }

		
	}
	
	public transport2.Driver GetDriver(DataBase db, User user) 
	{
		Driver driver=new Driver();
		for (int x=0; x<db.systemdrivers.size() ; x++) 
		{
			if(db.systemdrivers.get(x).getMobilenumber().equalsIgnoreCase(user.getMobilenumber())) 
			{
				driver=db.systemdrivers.get(x);
				
			}
			
		}
		
		return driver;
	}
	
	
}



