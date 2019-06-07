package testdns;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Date;


public class TestDns {
	
	final static String hostname = "www.google.com";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// only required for Java SE 5 and lower:
        //Security.setProperty("networkaddress.cache.ttl", "30");

        System.out.println("Security.getProperty networkaddress.cache.ttl:" + Security.getProperty("networkaddress.cache.ttl"));
        System.out.println("System.getProperty networkaddress.cache.ttl:" + System.getProperty("networkaddress.cache.ttl"));
        System.out.println("Security.getProperty networkaddress.cache.negative.ttl:" + Security.getProperty("networkaddress.cache.negative.ttl"));
        System.out.println("System.getProperty networkaddress.cache.negative.ttl:" + System.getProperty("networkaddress.cache.negative.ttl"));
        
        int i = 0;
        while(true) {
            
            try {
                makeRequest();
                InetAddress inetAddress = InetAddress.getLocalHost();
                System.out.println(new Date());
                inetAddress = InetAddress.getByName(hostname);
                displayStuff(hostname, inetAddress);
                
                
                if(i==2) {
                	java.security.Security.setProperty("networkaddress.cache.ttl", "0");
                	//static {
                	//    java.security.Security.setProperty ("networkaddress.cache.ttl" , "60");   
                	//}
                	System.out.println("::===> 0");
                }
                if(i==10) {
                	java.security.Security.setProperty("networkaddress.cache.ttl", "-1");
                	System.out.println("::===> -1");
                }
                
                System.out.println("::End i ["+i+"]");
                System.out.println("Security.getProperty networkaddress.cache.ttl:" + Security.getProperty("networkaddress.cache.ttl"));
                System.out.println("System.getProperty networkaddress.cache.ttl:" + System.getProperty("networkaddress.cache.ttl"));
                System.out.println("Security.getProperty networkaddress.cache.negative.ttl:" + Security.getProperty("networkaddress.cache.negative.ttl"));
                System.out.println("System.getProperty networkaddress.cache.negative.ttl:" + System.getProperty("networkaddress.cache.negative.ttl"));
                
                
            } catch (UnknownHostException e) {
                e.printStackTrace();
                break;
            }
            try {
                Thread.sleep(5L*1000L);
            } catch(Exception ex) {}
            i++;
            if(i==30) break;
        }
		
	    
	}

	
    public static void displayStuff(String whichHost, InetAddress inetAddress) {
        System.out.println("Which Host:" + whichHost);
        System.out.println("Canonical Host Name:" + inetAddress.getCanonicalHostName());
        System.out.println("Host Name:" + inetAddress.getHostName());
        System.out.println("Host Address:" + inetAddress.getHostAddress());
    }

    public static void makeRequest() {
        try {
            URL url = new URL("http://"+hostname+"/");
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader ird = new InputStreamReader(is);
            BufferedReader rd = new BufferedReader(ird);
            String res;
            while((res = rd.readLine()) != null) {
                System.out.println(res);
                break;
            }
            rd.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}




