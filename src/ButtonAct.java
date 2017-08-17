import java.io.IOException;

public class ButtonAct {

	SendFw sendfw = new SendFw();
	// socketTest socket = new socketTest();

	int resultado;

	public int Actioncheck(String ip, String port, String username, String password, String diretorio,
			String dircompress) throws Throwable {
		System.out.println("Entrou no bot�o");
		System.out.println(diretorio);
		System.out.println(ip);
		// InitSSHServer initssh = new InitSSHServer();
		try {
			// CommandUpdate commandupdate = new CommandUpdate();

			// if(initssh.InitSSH(ip, port, username, password) != 0){

			// Thread.sleep(6000);

			sendfw.firmWare(ip, port, diretorio, username, password);
			if (sendfw.result == 1) {
				resultado = 1;
			} else {
				resultado = 0;
			}

		} catch (IOException e) {
			return resultado;

		}
		return resultado;

	}

}
