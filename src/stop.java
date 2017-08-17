import javax.swing.JFrame;
import javax.swing.JTextField;

public class stop implements Runnable
{
	JFrame Updater;
	@Override
	public void run()
	{
		
		Updater = new JFrame();
		JTextField PasswordField = new JTextField();
		PasswordField.setBounds(106, 36, 86, 20);
		Updater.getContentPane().add(PasswordField);
		PasswordField.setColumns(10);
	}
	
}
