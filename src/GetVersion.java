
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

//Resultado � a fun��o que define o modelo do produto
//Se resultado for 1, o modelo � HotSpot 300
//Se resultado for 2, o modelo � AP 300
//Se resultado for 3, o modelo � WOM 5000
//Se resultado for 4, o modelo � WOM 5000i
//Se resultado for 5, o modelo � wom 5000 MIMO

public class GetVersion {

	public double ElapsedSeconds = 10;
	public long begin;
	public boolean started = false;
	int resultado = 0;

	public int Info(String ip, String port, String username, String password) throws Exception {

		CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);

		SendHttp sendhttp = new SendHttp();
		// Efetua o login no equipamento com o usuario e senha padrao

		System.out.print("Efetuando o Login no equipamento" + "");

		try {

			sendhttp.sendPost(ip, port, "formNumber=201&user=" + username + "&password=" + password);

			sendhttp.sendPost(ip, port, "formNumber=114&showFile=generalCmd_file");

		} catch (IOException e) {
			System.out.println("ENtrou catch sendHttp dentro GEtVersion");
			resultado = 10;

		}

		System.out.println("........OK");
		// resultado = 10;
		// return resultado;

		// Thread.sleep(5000);
		try {

			String httpsURL = "http://" + ip + ":" + port + "/cgi-bin/firmware.cgi?formNumber=1";
			URL myurl = new URL(httpsURL);
			HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);

			String inputLine;
			begin = System.currentTimeMillis();
			started = true;
			// System.out.println("<td class=\"tdStatsInfo\">HotSpot 300</td>");
			while ((inputLine = in.readLine()) != null) {
				/*
				 * if(System.currentTimeMillis() - begin> ElapsedSeconds*1000){
				 * System.out.println("Entrou timer SEDNHTTP"); resultado = 10;
				 * return resultado; }
				 */
				System.out.println(inputLine.trim());

				if (inputLine.trim().equals("<td class=\"tdStatsInfo\">HotSpot 300</td>")) {
					System.out.println("foi HOT");

					resultado = 1;
				}
				if (inputLine.trim().equals("<td class=\"tdStatsInfo\">AP 300</td>")) {
					System.out.println("foi AP");
					resultado = 2;
				}

				if (inputLine.trim().equals("<td class=\"tdStatsInfo\">WOM5000</td>")) {
					resultado = 3;
				}
				if (inputLine.trim().equals("<td class=\"tdStatsInfo\">WOM5000i</td>")) {
					resultado = 4;
				}
				if (inputLine.trim().equals("<td class=\"tdStatsInfo\">WOM MiMo</td>")) {
					resultado = 5;
					System.out.println("Entrou WOMMIMO");
				}

			}

			in.close();

			if (resultado != 1 && resultado != 2 && resultado != 3 && resultado != 4 && resultado != 5) {
				resultado = 10;
				System.out.println("N�o pegou vers�o");
			}
		} catch (IOException e) {
			System.out.println("Retornando resultado GET para WINDOW");
			resultado = 10;

		}
		System.out.println("Saiu GetVersion");

		return 0;

	}

	int Testing() {
		return resultado;
	}
}
