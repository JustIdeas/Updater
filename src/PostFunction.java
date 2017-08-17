
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostFunction {

	private static final String FORM_NUMBER_LOGIN = "201";
	private static final String FORM_NUMBER_UPDATE_FIRMWARE = "101";

	private String user;
	private String password;
	private String url;

	private CloseableHttpClient client;
	static int result;
	private static final Logger LOGGER = Logger.getLogger(PostFunction.class.getName());

	public PostFunction(final String url, final String user, final String password) {
		this.user = user;
		this.password = password;
		this.url = url;
//Set some parameters to the httpclientbuilder
	    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	    cm.setMaxTotal(128);
	    cm.setDefaultMaxPerRoute(24);
	    RequestConfig.Builder requestBuilder = RequestConfig.custom();
	    requestBuilder = requestBuilder.setConnectTimeout(5000);
	    requestBuilder = requestBuilder.setSocketTimeout(5000);

	    HttpClientBuilder builder = HttpClientBuilder.create();
	    builder.setDefaultRequestConfig(requestBuilder.build());
	    builder.setConnectionManager(cm);
	    this.client = builder.build();
	
	}

	/**
	 * 
	 * Metodo para fazer atualizacao de firmware do equipamento.
	 * 
	 * @param file
	 *            Arquivo de Firmware
	 * @return Resposta de confirmacao
	 */
	public Boolean updateFirmware(File file) {

		try {
			LOGGER.log(Level.INFO, "loginPost INIT");
			this.loginPost();
			LOGGER.log(Level.INFO, "loginPost END");

			LOGGER.log(Level.INFO, "sendFilePost INIT");
			this.sendFilePost(file);
			LOGGER.log(Level.INFO, "sendFilePost END");

		} catch (ClientProtocolException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}

		return true;

	}

	private void sendFilePost(File file) throws ClientProtocolException, IOException {

		HttpEntity entity = MultipartEntityBuilder.create().addTextBody("formNumber", FORM_NUMBER_UPDATE_FIRMWARE)
				.addBinaryBody("btnBrowse", file, ContentType.create("application/octet-stream"), file.getName())
				.build();

		HttpPost httpPost = new HttpPost(url);

		httpPost.setEntity(entity);

     try{
		client.execute(httpPost);
     }catch(IOException e){
    	 System.out.println("has ended the file transfer");
    	 result = 1;
  	  return;
     }
   // System.out.println(httpPost.isAborted());

		//System.out.println(response);

	}

	private void loginPost() throws ClientProtocolException, IOException {
		try {

			int count = 0;

			HttpPost httpPost = new HttpPost(this.url);
			// add header
			httpPost.setHeader("User-Agent", "Mozilla/5.0");

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("user", user));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("formNumber", FORM_NUMBER_LOGIN));

			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(httpPost);

			InputStream body = response.getEntity().getContent();

			String outResponse = getStringFromInputStream(body);
			if (outResponse.contains("login")) {
				this.loginPost();
				LOGGER.log(Level.INFO, "Tentativa" + count++);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// LOGGER.log(Level.INFO, "LOGOU!");

	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		result = 0;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				// sb.append(line.trim());
				System.out.println(line);
				if (line.trim().equals("id                          = setTimeout( \"start()\", 1000 );")) {
					LOGGER.log(Level.INFO, "Update OK");
					result = 1;
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	int resulting() {
		return result;

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * CGIIntelbras cgiIntelbras = new
	 * CGIIntelbras("http://192.168.7.49/cgi-bin/firmware.cgi", "admin",
	 * "lockinet.");
	 * 
	 * cgiIntelbras.updateFirmware(new
	 * File("/home/lu050023/Python_projects/Bandwidth/AP300-v1.1.5-ptbr.bin"));
	 * }
	 */

}
