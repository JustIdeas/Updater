import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.Scp;


public class SendFw {

	int result;

	public int firmWare(String ip, String port, String diretorio, String username, String password) throws Exception {

		try {
			result = 0;
			System.out.println(diretorio);
			PostFunction cgi = new PostFunction("http://" + ip + ":" + port +
			"/cgi-bin/firmware.cgi", username, password);

			cgi.updateFirmware(new File(diretorio));
            if(cgi.resulting() !=0){
            	result =1;
            }
           

			/*
			 * org.apache.tools.ant.taskdefs.optional.ssh.Scp scp = new Scp();
			 * int portSSH = 22; String srvrSSH = ip; String userSSH = username;
			 * 
			 * String pswdSSH = new String(password);// new String ( //
			 * JPasswordField.getPassword() // ); String localFile = diretorio;
			 * String remoteDir = "/tmp/";
			 * 
			 * scp.setPort(portSSH); scp.setLocalFile(localFile);
			 * scp.setTodir(userSSH + ":" + pswdSSH + "@" + srvrSSH + ":" +
			 * remoteDir); scp.setProject(new Project()); scp.setTrust(true);
			 * scp.execute();
			 */

		} catch (Exception e) {

			return result;
		}

		return result;

	}

}
