import java.net.*;  


public class Server { 
	
	static Socket s;
	static ServerSocket ss;
	public static void main(String[] args) throws Exception{
		
		
		ss = new ServerSocket(9999);
		System.out.println("Server-Socket created at 9999");
	    try{
	    
		    
		    while(true){ 
		    	
			        s=ss.accept();
			        System.out.println(s.getInetAddress());
			        new Thread(new Single(s)).start();
		    }
	    }
	    catch(Exception e){
	    	System.out.println(e);
	    }
	    finally{
	    	System.out.println("entered finally block");
	    	ss.close();
	    }
	    
	    
	}
}
	
	   