import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;





public class SendHttp {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public double ElapsedSeconds = 10;
	 public long begin;
	  public boolean started = false;
	public int sendPost(String ip, String port, String parametros) throws Exception
	{
		
	try{
		
			
		
		String url = "http://" + ip +":"+port+"/cgi-bin/firmware.cgi?";
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(3000);
        con.setReadTimeout(4000);
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = parametros;
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
	

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			begin = System.currentTimeMillis();
	 		started=true;
			response.append(inputLine);
			/*if(System.currentTimeMillis() - begin> ElapsedSeconds*1000){
				System.out.println("Entrou timer SEDNHTTP");
				return 1;
			}*/
			System.out.println(inputLine);
		}
		in.close();
		
		
		
		
		
		
	}catch(IOException e){
		System.out.println("ENtrou catch sendhttp");
	
		
	}
	System.out.println("N�o entrou Catch SendHttp");
	
	return 0;
		}

}