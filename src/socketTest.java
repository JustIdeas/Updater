import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class socketTest {
	
	public int isReachable(String iP, String port) {
	    // Any Open port on other machine
	    // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
	    try {
	    	
	    	
	        try (Socket soc = new Socket()) {
	        	
	        	
	        	int PORTA = Integer.parseInt(port);
	            soc.connect(new InetSocketAddress(iP, PORTA), 3000);
	        	
	        }
	        return 1;
	    } catch (IOException ex) {
	        return 0;
	    }
	}
}

