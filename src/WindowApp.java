import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.tools.ant.taskdefs.Sleep;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class WindowApp extends JFrame {

	JFrame Updater;
	public JLabel lblFirmwareDirectory;
	public JButton btnEnviar;
	public JButton btnStopThread;
	public JTextField UsernameField;
	public JTextField PasswordField;
	public JLabel lblNomeHotspot;
	public JLabel lblNomeAP300;
	public JLabel lblNomeWOM5000;
	public JLabel lblNomeWOM5000i;
	public JLabel lblNomeWOM5000MIMO;
	public JLabel HOTSPOT300DIRCOMPLETO;
	public JLabel AP300DIRCOMPLETO;
	public JLabel WOM5000DIRCOMPLETO;
	public JLabel WOM5000iDIRCOMPLETO;
	public JLabel WOM5000MIMODIRCOMPLETO;
	public JFormattedTextField DefaultPort;
	public JButton btnListaIps;
	public JButton btnRangeIps;
	public JButton btnApagarcores;
	public JFormattedTextField IP1;
	public JFormattedTextField IP2;
	public JFormattedTextField IP3;
	public JFormattedTextField IPR1;
	public JFormattedTextField IPR2;
	public JPanel PANEL3Bytes;
	public JLabel label;
	public JLabel PANERange;
	public JLabel LBLAtuaHot;
	public JLabel LBLAtuaAP;
	public JLabel LBLAtuaW5;
	public JLabel LBLAtuaW5i;
	public JLabel LBLAtuaW5M;
	public JLabel LBLEncHot;
	public JLabel LBLEncAP;
	public JLabel LBLEncW5;
	public JLabel LBLEncW5i;
	public JLabel LBLEncW5M;
	public TextArea textArea;

	JTextField[] Botoes = { new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField(), new JTextField() };

	// Controle Bot�o Enviar//
	int controle = 1;

	//

	// Controle arquivos selecionados /sim 1/ ou /n�o 0/

	int ControleFilesHOT = 0;
	int ControleFilesWOM = 0;
	int ControleFilesWOMi = 0;
	int ControleFilesWOMMIMO = 0;
	int ControleFilesAP = 0;

	int AcrH = 0;
	int AcrAP = 0;
	int AcrW5 = 0;
	int AcrW5i = 0;
	int AcrW5M = 0;

	int AcratuH = 0;
	int AcratuAP = 0;
	int AcratuW5 = 0;
	int AcratuW5i = 0;
	int AcratuW5M = 0;

	//
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	// public WindowApp() throws ParseException {
	// initialize();
	// }

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */

	public static Begin t = null;
	public volatile boolean middle = false;

	public class Begin extends Thread {

		public volatile boolean state = false;
		public volatile boolean ThreadControl = false;

		public void run() {

			System.out.println("entrou while " + middle);
			if (ThreadControl == false) {

				if (ThreadControl == true) {
					System.out.println("Entrou IF ThreadControl");

					return;
				}

				btnEnviar.setEnabled(false);
				btnListaIps.setEnabled(false);
				btnRangeIps.setEnabled(false);
				btnApagarcores.setEnabled(false);
				System.out.println("entrou if " + middle);

				InetAddressValidator inetValidator = InetAddressValidator.getInstance();
				ButtonAct buttonact = new ButtonAct();
				GetVersion getversion = new GetVersion();
				socketTest socket = new socketTest();
				String port = DefaultPort.getText();

				// Pegando informa��o da Jlabel e formatando com dupla barra

				String dirCompletoHOTSPOT300 = HOTSPOT300DIRCOMPLETO.getText();
				String dirCompletoAP300 = AP300DIRCOMPLETO.getText();
				String dirCompletoWOM5000 = WOM5000DIRCOMPLETO.getText();
				String dirCompletoWOM5000i = WOM5000iDIRCOMPLETO.getText();
				String dirCompletoWOM5000MIMO = WOM5000MIMODIRCOMPLETO.getText();

				// String para armazenar informa��o somente do nome da arquivo
				// da firmware
				String dircompressHOTSPOT300 = lblNomeHotspot.getText();
				String dircompressAP300 = lblNomeAP300.getText();
				String dircompressWOM5000 = lblNomeWOM5000.getText();
				String dircompressWOM5000i = lblNomeWOM5000i.getText();
				String dircompressWOM5000MIMO = lblNomeWOM5000MIMO.getText();

				String diretorioHOT = dirCompletoHOTSPOT300;
				String diretorioAP = dirCompletoAP300.replace("\\", "\\\\");
				String diretorioW5000 = dirCompletoWOM5000;
				String diretorioW5000i = dirCompletoWOM5000i;
				String diretorioW5000MIMO = dirCompletoWOM5000MIMO;

				// Adiciona dupla barra por causa do formato de acesso ao
				// diretorio
				/*
				 * String diretorioHOT =
				 * dirCompletoHOTSPOT300.replace("\\", "\\\\"); String
				 * diretorioAP = dirCompletoAP300.replace("\\", "\\\\"); String
				 * diretorioW5000 = dirCompletoWOM5000.replace("\\", "\\\\");
				 * String diretorioW5000i =
				 * dirCompletoWOM5000i.replace("\\", "\\\\"); String
				 * diretorioW5000MIMO =
				 * dirCompletoWOM5000MIMO.replace("\\", "\\\\");
				 */

				String username = UsernameField.getText();
				String password = PasswordField.getText();

				///////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				////////////////////////////// CONTROLE 1, LISTA
				/////////////////////////////////////////////////////////////////////////////////// IP///////////////////////////////
				////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////

				if (controle == 1) {
					for (int i = 0; i < Botoes.length; i++) {
						Botoes[i].setBackground(Color.WHITE);
						Botoes[i].setToolTipText("");
						if (ThreadControl == true) {
							System.out.println("Entrou IF ThreadControl");

							return;
						}

						String num = Botoes[i].getText();

						try {
							if (UsernameField.getText().isEmpty() == true) {
								UsernameField.setBackground(Color.RED);
								UsernameField.setToolTipText("Insira um usu�rio, Ex: admin");
								textArea.append("Insira um usu�rio de acesso" + "\n");
								ThreadControl = false;
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;
							} else {
								UsernameField.setBackground(Color.WHITE);
							}
							if (PasswordField.getText().isEmpty() == true) {
								PasswordField.setBackground(Color.RED);
								PasswordField.setToolTipText("Insira uma senha, Ex: admin");
								textArea.append("Insira uma senha de acesso" + "\n");
								ThreadControl = false;
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;
							} else {
								PasswordField.setBackground(Color.WHITE);
							}

							if (DefaultPort.getText().isEmpty() == true) {
								DefaultPort.setBackground(Color.RED);
								DefaultPort.setToolTipText("Insira uma porta, Ex: 80");
								textArea.append("Insira uma porta de acesso" + "\n");
								ThreadControl = false;
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;

							} else {
								DefaultPort.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

							}

							if (Botoes[i].getText().isEmpty() == true) {

								Botoes[i].setBackground(Color.RED);
								Botoes[i].setToolTipText("Coloque o IP");
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

							} else {
								Botoes[i].setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

								if (Botoes[i].getText().isEmpty() == false
										&& inetValidator.isValidInet4Address(num) == false) {
									Botoes[i].setToolTipText("Insira o IP corretamente, Ex: 10.0.0.1");
									System.out.println("Erro de preenchimento no IP");
									Botoes[i].setBackground(Color.YELLOW);
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}

								} else {
									textArea.append("Iniciando processo de atualiza��o... IP " + num + "\n");
									Botoes[i].setBackground(Color.WHITE);
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}
								}

								if (inetValidator.isValidInet4Address(num) == true
										&& socket.isReachable(num, port) == 1) {

									Botoes[i].setBackground(Color.YELLOW);

									getversion.Info(num, port, username, password);

									System.out.println(getversion.resultado);
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}
									if (getversion.resultado == 1) {
										AcrH++;
										String HOTUP = Integer.toString(AcrH);
										LBLEncHot.setText(HOTUP);

										textArea.append("Produto no IP " + num + " � um HotSpot 300" + "\n");
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}

										if (ControleFilesHOT == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}

										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesHOT == 1) {
											if (buttonact.Actioncheck(num, port, username, password, diretorioHOT,
													dircompressHOTSPOT300) != 0) {

												Botoes[i].setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + num
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuH++;
												String HOTATU = Integer.toString(AcratuH);
												LBLAtuaHot.setText(HOTATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}

											} else {
												textArea.append("N�o conseguiu conectar no IP " + num + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ num + " e se selecionou o arquivo de FW" + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 2) {
										textArea.append("Produto no IP " + num + " � um AP 300" + "\n");
										AcrAP++;
										String APUP = Integer.toString(AcrAP);
										LBLEncAP.setText(APUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesAP == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}

										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesAP == 1) {
											if (buttonact.Actioncheck(num, port, username, password, diretorioAP,
													dircompressAP300) != 0) {

												Botoes[i].setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + num
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuAP++;
												String APATU = Integer.toString(AcratuAP);
												LBLAtuaAP.setText(APATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + num + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ num + " e se selecionou o arquivo de FW" + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 3) {
										textArea.append("Produto no IP " + num + " � um WOM5000" + "\n");
										AcrW5++;
										String W5UP = Integer.toString(AcrW5);
										LBLEncW5.setText(W5UP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOM == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}

										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOM == 1) {
											if (buttonact.Actioncheck(num, port, username, password, diretorioW5000,
													dircompressWOM5000) != 0) {

												Botoes[i].setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + num
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5++;
												String W5ATU = Integer.toString(AcratuW5);
												LBLAtuaW5.setText(W5ATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}

											} else {

												textArea.append("N�o conseguiu conectar no IP " + num + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ num + " e se selecionou o arquivo de FW" + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}

									if (getversion.resultado == 4) {
										textArea.append("Produto no IP " + num + " � um WOM5000i" + "\n");
										AcrW5i++;
										String W5iUP = Integer.toString(AcrW5i);
										LBLEncW5i.setText(W5iUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOMi == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}

										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOMi == 1) {
											if (buttonact.Actioncheck(num, port, username, password, diretorioW5000i,
													dircompressWOM5000i) != 0) {

												Botoes[i].setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + num
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5i++;
												String W5iATU = Integer.toString(AcratuW5i);
												LBLAtuaW5i.setText(W5iATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + num + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ num + " e se selecionou o arquivo de FW" + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 5) {
										textArea.append("Produto no IP " + num + " � um WOM 5000 MiMo" + "\n");
										AcrW5M++;
										String W5MUP = Integer.toString(AcrW5M);
										LBLEncW5M.setText(W5MUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOMMIMO == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}

										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOMMIMO == 1) {
											if (buttonact.Actioncheck(num, port, username, password, diretorioW5000MIMO,
													dircompressWOM5000MIMO) != 0) {
												Botoes[i].setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + num
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5M++;
												String W5MATU = Integer.toString(AcratuW5M);
												LBLAtuaW5M.setText(W5MATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + num + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ num + " e se selecionou o arquivo de FW" + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 10) {
										System.out.println(
												"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso.");
										Botoes[i].setBackground(Color.RED);
										textArea.append("N�o conseguiu conectar no IP " + num + "\n");
										textArea.append(
												"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
														+ num + " e se selecionou o arquivo de FW" + "\n");
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
									}
								} else {
									System.out.println(
											"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso.");
									Botoes[i].setBackground(Color.RED);
									textArea.append("N�o conseguiu conectar no IP " + num + "\n");
									textArea.append(
											"Falha na atualiza��o, verifique Usu�rio, senha, porta de acesso e se o IP "
													+ num + " est� ativo" + "\n");

									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}
								}

							}

						} catch (Throwable e1) {
							e1.printStackTrace();

						}
					}
					btnEnviar.setEnabled(true);
					btnListaIps.setEnabled(true);
					btnRangeIps.setEnabled(true);
					btnApagarcores.setEnabled(true);
				}

				///////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				////////////////////////////// CONTROLE 2, RANGE
				/////////////////////////////////////////////////////////////////////////////////// IP///////////////////////////////
				////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////

				if (controle == 2) {

					IP1.setBackground(Color.WHITE);
					IP2.setBackground(Color.WHITE);
					IP3.setBackground(Color.WHITE);
					IPR1.setBackground(Color.WHITE);
					IPR2.setBackground(Color.WHITE);

					System.out.println("Entrou Controle 2");
					boolean checking = IP1.getText().isEmpty();
					IP2.getText().isEmpty();
					IP3.getText().isEmpty();
					IPR1.getText().isEmpty();
					IPR2.getText().isEmpty();

					if (IP1.getText().isEmpty() == true || IP2.getText().isEmpty() == true
							|| IP3.getText().isEmpty() == true || IPR1.getText().isEmpty() == true
							|| IPR2.getText().isEmpty() == true) {
						System.out.println("Insira o IP");
						IP1.setBackground(Color.RED);
						IP2.setBackground(Color.RED);
						IP3.setBackground(Color.RED);
						IPR1.setBackground(Color.RED);
						IPR2.setBackground(Color.RED);
						textArea.append("Insira um Range de IPs" + "\n");
						if (ThreadControl == true) {
							System.out.println("Entrou IF ThreadControl");

							return;
						}
						btnEnviar.setEnabled(true);
						btnListaIps.setEnabled(true);
						btnRangeIps.setEnabled(true);
						btnApagarcores.setEnabled(true);
						return;
					} else {
						IP1.setBackground(Color.WHITE);
						IP2.setBackground(Color.WHITE);
						IP3.setBackground(Color.WHITE);
						IPR1.setBackground(Color.WHITE);
						IPR2.setBackground(Color.WHITE);
						System.out.println("saiu IF empty");
						if (ThreadControl == true) {
							System.out.println("Entrou IF ThreadControl");

							return;
						}
					}

					int ip1 = Integer.parseInt(IP1.getText());
					int ip2 = Integer.parseInt(IP2.getText());
					int ip3 = Integer.parseInt(IP3.getText());
					int ipR1 = Integer.parseInt(IPR1.getText());
					int ipR2 = Integer.parseInt(IPR2.getText());
					if (ThreadControl == true) {
						System.out.println("Entrou IF ThreadControl");

						return;
					}

					if (ipR1 > ipR2) {
						IP1.setBackground(Color.RED);
						IP2.setBackground(Color.RED);
						IP3.setBackground(Color.RED);
						IPR1.setBackground(Color.RED);
						IPR2.setBackground(Color.RED);

						textArea.append(
								"Insira primeiro o ip inicial, para depois o ip final, e n�o o inverso: \n IP inicial:"
										+ ipR1 + "\n IP final:" + ipR2 + "\n");
						btnEnviar.setEnabled(true);
						btnListaIps.setEnabled(true);
						btnRangeIps.setEnabled(true);
						btnApagarcores.setEnabled(true);
						return;

					}

					if (ThreadControl == true) {
						System.out.println("Entrou IF ThreadControl");

						return;
					}
					System.out.println("cHEGOU ANTES DO for");

					for (ipR1 = ipR1; ipR1 <= ipR2; ipR1++) {

						String Range = ip1 + "." + ip2 + "." + ip3 + "." + ipR1;
						System.out.println("IP " + Range);
						if (ThreadControl == true) {
							System.out.println("Entrou IF ThreadControl");

							return;
						}

						try {

							if (IP1.getText().isEmpty() == true || IP2.getText().length() > 3
									|| IP3.getText().length() > 3 || IPR1.getText().length() > 3
									|| IPR2.getText().length() > 3) {

								System.out.println("Um dos campos possui mais de que 3 digitos, veja " + Range + "\n");
								IP1.setBackground(Color.RED);
								IP2.setBackground(Color.RED);
								IP3.setBackground(Color.RED);
								IPR1.setBackground(Color.RED);
								IPR2.setBackground(Color.RED);
								textArea.append("Um dos campos possui mais de que 3 digitos, veja " + Range + "\n");
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;
							} else {
								IP1.setBackground(Color.WHITE);
								IP2.setBackground(Color.WHITE);
								IP3.setBackground(Color.WHITE);
								IPR1.setBackground(Color.WHITE);
								IPR2.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}
							}

							if (UsernameField.getText().isEmpty() == true) {
								UsernameField.setBackground(Color.RED);
								UsernameField.setToolTipText("Insira um usu�rio, Ex: admin");
								textArea.append("Insira um usu�rio de acesso" + "\n");
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;

							} else {
								UsernameField.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}
							}
							if (PasswordField.getText().isEmpty() == true) {
								PasswordField.setBackground(Color.RED);
								PasswordField.setToolTipText("Insira uma senha, Ex: admin");
								textArea.append("Insira uma senha de acesso" + "\n");
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;
							} else {
								PasswordField.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}
							}

							if (DefaultPort.getText().isEmpty() == true) {
								DefaultPort.setBackground(Color.RED);
								DefaultPort.setToolTipText("Insira uma porta, Ex: 80");
								textArea.append("Insira uma porta de acesso" + "\n");
								btnEnviar.setEnabled(true);
								btnListaIps.setEnabled(true);
								btnRangeIps.setEnabled(true);
								btnApagarcores.setEnabled(true);
								return;

							} else {
								DefaultPort.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

							}

							if (IP1.getText().isEmpty() == true || IP2.getText().isEmpty() == true
									|| IP3.getText().isEmpty() == true || IPR1.getText().isEmpty() == true
									|| IPR2.getText().isEmpty() == true) {
								// IP1.getText().isEmpty() == true ||
								// IP2.getText().isEmpty() == true ||
								// IP3.getText().isEmpty() == true ||
								// IPR1.getText().isEmpty() == true ||
								// IPR2.getText().isEmpty() == true
								IP1.setBackground(Color.RED);
								IP2.setBackground(Color.RED);
								IP3.setBackground(Color.RED);
								IPR1.setBackground(Color.RED);
								IPR2.setBackground(Color.RED);

								textArea.append("Insira um Range de IPs" + "\n");
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

							} else {
								IP1.setBackground(Color.WHITE);
								IP2.setBackground(Color.WHITE);
								IP3.setBackground(Color.WHITE);
								IPR1.setBackground(Color.WHITE);
								IPR2.setBackground(Color.WHITE);
								if (ThreadControl == true) {
									System.out.println("Entrou IF ThreadControl");

									return;
								}

								if (checking == false && inetValidator.isValidInet4Address(Range) == false) {

									System.out.println("Erro de preenchimento no IP");
									IP1.setBackground(Color.RED);
									IP2.setBackground(Color.RED);
									IP3.setBackground(Color.RED);
									IPR1.setBackground(Color.RED);
									IPR2.setBackground(Color.RED);
									textArea.append("Erro de preenchimento, verifique " + Range);
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}
								} else {
									textArea.append("Iniciando processo de atualiza��o... IP " + Range + "\n");
									IP1.setBackground(Color.YELLOW);
									IP2.setBackground(Color.YELLOW);
									IP3.setBackground(Color.YELLOW);
									IPR1.setBackground(Color.YELLOW);
									IPR2.setBackground(Color.YELLOW);
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}
								}

								if (inetValidator.isValidInet4Address(Range) == true
										&& socket.isReachable(Range, port) == 1) {

									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");

										return;
									}

									getversion.Info(Range, port, username, password);

									System.out.println(getversion.resultado);
									if (getversion.resultado == 1) {
										AcrH++;
										String HOTUP = Integer.toString(AcrH);
										LBLEncHot.setText(HOTUP);
										textArea.append("Produto no IP " + Range + " � um HotSpot 300" + "\n");
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesHOT == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}
										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesHOT == 1) {
											if (buttonact.Actioncheck(Range, port, username, password, diretorioHOT,
													dircompressHOTSPOT300) != 0 && ControleFilesHOT == 1) {

												IP1.setBackground(Color.GREEN);
												IP2.setBackground(Color.GREEN);
												IP3.setBackground(Color.GREEN);
												IPR1.setBackground(Color.GREEN);
												IPR2.setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + Range
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuH++;
												String HOTATU = Integer.toString(AcratuH);
												LBLAtuaHot.setText(HOTATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ Range + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 2) {
										textArea.append("Produto no IP " + Range + " � um AP 300" + "\n");
										AcrAP++;
										String APUP = Integer.toString(AcrAP);
										LBLEncAP.setText(APUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesAP == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											if (ThreadControl == true) {
												System.out.println("Entrou IF ThreadControl");

												return;
											}
											btnEnviar.setEnabled(true);
											btnListaIps.setEnabled(true);
											btnRangeIps.setEnabled(true);
											btnApagarcores.setEnabled(true);
											return;
										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesAP == 1) {
											System.out.println(diretorioAP);
											if (buttonact.Actioncheck(Range, port, username, password, diretorioAP,
													dircompressAP300) != 0) {

												IP1.setBackground(Color.GREEN);
												IP2.setBackground(Color.GREEN);
												IP3.setBackground(Color.GREEN);
												IPR1.setBackground(Color.GREEN);
												IPR2.setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + Range
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuAP++;
												String APATU = Integer.toString(AcratuAP);
												LBLAtuaAP.setText(APATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ Range + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 3) {
										textArea.append("Produto no IP " + Range + " � um WOM5000" + "\n");
										AcrW5++;
										String W5UP = Integer.toString(AcrW5);
										LBLEncW5.setText(W5UP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOM == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											return;
										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOM == 1) {
											if (buttonact.Actioncheck(Range, port, username, password, diretorioW5000,
													dircompressWOM5000) != 0) {

												IP1.setBackground(Color.GREEN);
												IP2.setBackground(Color.GREEN);
												IP3.setBackground(Color.GREEN);
												IPR1.setBackground(Color.GREEN);
												IPR2.setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + Range
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5++;
												String W5ATU = Integer.toString(AcratuW5);
												LBLEncHot.setText(W5ATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ Range + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}

									if (getversion.resultado == 4) {
										textArea.append("Produto no IP " + Range + " � um WOM5000i" + "\n");
										AcrW5i++;
										String W5iUP = Integer.toString(AcrW5i);
										LBLEncW5i.setText(W5iUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOMi == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											return;
										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOMi == 1) {
											if (buttonact.Actioncheck(Range, port, username, password, diretorioW5000i,
													dircompressWOM5000i) != 0) {

												IP1.setBackground(Color.GREEN);
												IP2.setBackground(Color.GREEN);
												IP3.setBackground(Color.GREEN);
												IPR1.setBackground(Color.GREEN);
												IPR2.setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + Range
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5i++;
												String W5iATU = Integer.toString(AcratuW5i);
												LBLAtuaW5i.setText(W5iATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ Range + "\n");
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											}
										}
									}
									if (getversion.resultado == 5) {
										textArea.append("Produto no IP " + Range + " � um WOM 5000 MiMo" + "\n");
										AcrW5M++;
										String W5MUP = Integer.toString(AcrW5M);
										LBLEncW5M.setText(W5MUP);
										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl");

											return;
										}
										if (ControleFilesWOMMIMO == 0) {
											textArea.append("Selecione um arquivo de firmware" + "\n");
											return;
										} else {
											textArea.append("Enviando arquivo de firmware" + "\n");
										}
										if (ControleFilesWOMMIMO == 1) {
											if (buttonact.Actioncheck(Range, port, username, password,
													diretorioW5000MIMO, dircompressWOM5000MIMO) != 0) {
												IP1.setBackground(Color.GREEN);
												IP2.setBackground(Color.GREEN);
												IP3.setBackground(Color.GREEN);
												IPR1.setBackground(Color.GREEN);
												IPR2.setBackground(Color.GREEN);
												// Thread.sleep(6000);
												textArea.append("Atualiza��o Concluida no IP " + Range
														+ " Aguarde o produto reiniciar" + "\n");
												AcratuW5M++;
												String W5MATU = Integer.toString(AcratuW5M);
												LBLAtuaW5M.setText(W5MATU);
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
											} else {
												if (ThreadControl == true) {
													System.out.println("Entrou IF ThreadControl");

													return;
												}
												textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
												textArea.append(
														"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
																+ Range + "\n");
											}
										}
									}
									if (getversion.resultado == 10) {
										System.out.println(
												"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso.");
										IP1.setBackground(Color.RED);
										IP2.setBackground(Color.RED);
										IP3.setBackground(Color.RED);
										IPR1.setBackground(Color.RED);
										IPR2.setBackground(Color.RED);
										textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
										textArea.append(
												"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso no IP "
														+ Range + "\n");

										if (ThreadControl == true) {
											System.out.println("Entrou IF ThreadControl" + "\n");

											return;
										}
									}
								} else {
									System.out.println(
											"Falha na atualiza��o, verifique Usu�rio, senha e porta de acesso.");
									IP1.setBackground(Color.RED);
									IP2.setBackground(Color.RED);
									IP3.setBackground(Color.RED);
									IPR1.setBackground(Color.RED);
									IPR2.setBackground(Color.RED);
									textArea.append("N�o conseguiu conectar no IP " + Range + "\n");
									textArea.append(
											"Falha na atualiza��o, verifique Usu�rio, senha, porta de acesso e se o IP "
													+ Range + " est� ativo" + "\n");
									System.out.println(t.getState());
									if (ThreadControl == true) {
										System.out.println("Entrou IF ThreadControl");
										textArea.append("Parou atualiza��o" + "\n");
										return;
									}
								}

							}

						} catch (Throwable e1) {

						}
					}
					btnEnviar.setEnabled(true);
					btnListaIps.setEnabled(true);
					btnRangeIps.setEnabled(true);
					btnApagarcores.setEnabled(true);
				}

			}
		}

	};

	public WindowApp() throws ParseException {

		Updater = new JFrame();
		Updater.getContentPane().setLayout(null);
		Updater.getContentPane().setFont(new Font("Arial Black", Font.PLAIN, 11));
		Updater.setResizable(false);
		Updater.setTitle("Updater WOM--AP--HOT");
		Updater.getContentPane().setBackground(Color.LIGHT_GRAY);
		Updater.setBounds(100, 100, 836, 520);
		Updater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Updater.getContentPane().setLayout(null);

		PasswordField = new JTextField();
		PasswordField.setBounds(106, 36, 86, 20);
		Updater.getContentPane().add(PasswordField);

		WOM5000iDIRCOMPLETO = new JLabel("Firmware WOM 5000i");
		WOM5000iDIRCOMPLETO.setVisible(false);
		WOM5000iDIRCOMPLETO.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// WOM5000iDIRCOMPLETO.setBounds(343, 110, 196, 17);
		Updater.getContentPane().add(WOM5000iDIRCOMPLETO);

		// MaskFormatter MASK = new MaskFormatter("###");
		JLabel lblPort = new JLabel("Porta HTTP");
		lblPort.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPort.setBounds(8, 64, 86, 17);
		Updater.getContentPane().add(lblPort);

		DefaultPort = new JFormattedTextField();
		DefaultPort.setBounds(106, 62, 66, 20);
		Updater.getContentPane().add(DefaultPort);

		JLabel label_2 = new JLabel("Atualizados");
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		label_2.setBounds(492, 51, 66, 17);
		Updater.getContentPane().add(label_2);

		LBLAtuaHot = new JLabel("0");
		LBLAtuaHot.setBounds(520, 80, 16, 16);
		Updater.getContentPane().add(LBLAtuaHot);

		LBLAtuaAP = new JLabel("0");
		LBLAtuaAP.setBounds(520, 147, 16, 16);
		Updater.getContentPane().add(LBLAtuaAP);

		LBLAtuaW5 = new JLabel("0");
		LBLAtuaW5.setBounds(520, 211, 16, 16);
		Updater.getContentPane().add(LBLAtuaW5);

		LBLAtuaW5i = new JLabel("0");
		LBLAtuaW5i.setBounds(520, 279, 16, 16);
		Updater.getContentPane().add(LBLAtuaW5i);

		LBLAtuaW5M = new JLabel("0");
		LBLAtuaW5M.setBounds(520, 341, 16, 16);
		Updater.getContentPane().add(LBLAtuaW5M);

		JLabel label_8 = new JLabel("Encontrados");
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 10));
		label_8.setBounds(423, 51, 70, 16);
		Updater.getContentPane().add(label_8);

		LBLEncHot = new JLabel("0");
		LBLEncHot.setBounds(458, 80, 16, 16);
		Updater.getContentPane().add(LBLEncHot);

		LBLEncAP = new JLabel("0");
		LBLEncAP.setBounds(458, 147, 16, 16);
		Updater.getContentPane().add(LBLEncAP);

		LBLEncW5 = new JLabel("0");
		LBLEncW5.setBounds(458, 211, 16, 16);
		Updater.getContentPane().add(LBLEncW5);

		LBLEncW5i = new JLabel("0");
		LBLEncW5i.setBounds(458, 279, 16, 16);
		Updater.getContentPane().add(LBLEncW5i);

		LBLEncW5M = new JLabel("0");
		LBLEncW5M.setBounds(458, 341, 16, 16);
		Updater.getContentPane().add(LBLEncW5M);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblUsername.setBackground(Color.ORANGE);
		lblUsername.setBounds(10, 11, 86, 14);
		Updater.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPassword.setBounds(10, 38, 86, 14);
		Updater.getContentPane().add(lblPassword);

		PANEL3Bytes = new JPanel();
		PANEL3Bytes.setBorder(new TitledBorder(null, "3 Primeiros Bytes", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		PANEL3Bytes.setBounds(0, 111, 147, 59);
		Updater.getContentPane().add(PANEL3Bytes);
		PANEL3Bytes.setLayout(null);

		IP3 = new JFormattedTextField();
		IP3.setBounds(86, 26, 27, 27);
		PANEL3Bytes.add(IP3);

		IP1 = new JFormattedTextField();
		IP1.setBounds(30, 26, 27, 27);
		PANEL3Bytes.add(IP1);

		IP2 = new JFormattedTextField();
		IP2.setBounds(58, 26, 27, 27);
		PANEL3Bytes.add(IP2);

		JPanel PANERange = new JPanel();
		PANERange.setBorder(new TitledBorder(null, "Range de IP", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		PANERange.setBounds(157, 111, 102, 59);
		Updater.getContentPane().add(PANERange);
		PANERange.setLayout(null);

		IPR1 = new JFormattedTextField();
		IPR1.setBounds(21, 26, 27, 27);
		PANERange.add(IPR1);
		Updater.getContentPane()
				.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { btnListaIps, WOM5000iDIRCOMPLETO,
						UsernameField, PasswordField, lblFirmwareDirectory, lblNomeHotspot, HOTSPOT300DIRCOMPLETO,
						AP300DIRCOMPLETO, WOM5000DIRCOMPLETO, WOM5000MIMODIRCOMPLETO, lblNomeAP300, lblNomeWOM5000,
						lblNomeWOM5000i, lblNomeWOM5000MIMO, DefaultPort, btnEnviar }));

		IPR2 = new JFormattedTextField();
		IPR2.setBounds(54, 26, 27, 27);
		PANERange.add(IPR2);

		JLabel label = new JLabel("----");
		label.setBounds(44, 32, 16, 16);
		PANERange.add(label);

		////////
		///////
		/// Cria Lista IPs na inicialização////////
		/////////
		/////////

		int Va = 106;
		int Vb = 106;
		int Vc = 106;
		int Vd = 106;

		int Hb = 20;
		int Hc = 116;
		int Hd = 222;
		int He = 328;

		for (int i = 0; i < 8; i++) {
			Botoes[i].setVisible(true);
			Botoes[i].setBounds(Hb, Va, 70, 20);
			Botoes[i].setToolTipText("192.168.0.1");
			Botoes[i].setFont(new Font("Monospaced", Font.PLAIN, 10));
			Updater.getContentPane().add(Botoes[i]);
			Va += 31;
		}
		for (int b = 8; b < 11; b++) {
			Botoes[b].setVisible(true);
			Botoes[b].setBounds(Hc, Vb, 70, 20);
			Botoes[b].setToolTipText("192.168.0.1");
			Botoes[b].setFont(new Font("Monospaced", Font.PLAIN, 10));
			Updater.getContentPane().add(Botoes[b]);
			Vb += 31;

		}
		/*for (int c = 16; c < 24; c++) {
			Botoes[c].setVisible(true);
			Botoes[c].setBounds(Hd, Vc, 70, 20);
			Botoes[c].setToolTipText("192.168.0.1");
			Botoes[c].setFont(new Font("Monospaced", Font.PLAIN, 10));
			Updater.getContentPane().add(Botoes[c]);
			Vc += 31;

		}
		for (int d = 24; d < 32; d++) {
			Botoes[d].setVisible(true);
			Botoes[d].setBounds(He, Vd, 70, 20);
			Botoes[d].setToolTipText("192.168.0.1");
			Botoes[d].setFont(new Font("Monospaced", Font.PLAIN, 10));
			Updater.getContentPane().add(Botoes[d]);
			Vd += 31;

		}*/

		/////////
		////////
		///////////
		///////////
		///////////
		IP3.setVisible(false);
		IP2.setVisible(false);
		IP1.setVisible(false);
		IPR2.setVisible(false);
		IPR1.setVisible(false);
		PANEL3Bytes.setVisible(false);
		label.setVisible(false);
		PANERange.setVisible(false);

		/////////
		/////////
		//////////
		////////////
		//////////
		UsernameField = new JTextField();
		UsernameField.setBounds(106, 9, 86, 20);
		Updater.getContentPane().add(UsernameField);
		UsernameField.setColumns(10);

		PasswordField.setColumns(10);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(11, 111, 26, 16);
		Updater.getContentPane().add(label_1);

		JLabel lblFirmwareDirectory = new JLabel("Diretorios das firmwares");
		lblFirmwareDirectory.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblFirmwareDirectory.setForeground(Color.BLACK);
		lblFirmwareDirectory.setBackground(Color.ORANGE);
		lblFirmwareDirectory.setBounds(528, 7, 196, 20);
		Updater.getContentPane().add(lblFirmwareDirectory);

		textArea = new TextArea();
		textArea.setBounds(10, 371, 440, 124);
		textArea.setEditable(false);
		Updater.getContentPane().add(textArea);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "HotSpot 300", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		panel.setBounds(558, 51, 272, 55);
		Updater.getContentPane().add(panel);
		panel.setLayout(null);

		lblNomeHotspot = new JLabel("Nome Arquivo");
		lblNomeHotspot.setBounds(14, 22, 141, 14);
		panel.add(lblNomeHotspot);

		JButton FWHOTSPOT300 = new JButton("Browse");
		FWHOTSPOT300.setBounds(167, 18, 91, 23);
		panel.add(FWHOTSPOT300);
		FWHOTSPOT300.setForeground(Color.WHITE);
		FWHOTSPOT300.setBackground(Color.GRAY);
		FWHOTSPOT300.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				FileFilter filter = new FileNameExtensionFilter("My bin", "bin");
				chooser.addChoosableFileFilter(filter);
				int choice = chooser.showOpenDialog(chooser);
				if (choice != JFileChooser.APPROVE_OPTION)
					return;
				HOTSPOT300DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
				lblNomeHotspot.setText(chooser.getSelectedFile().getName());
				if (lblNomeHotspot.getText().startsWith("HOTSPOT")) {
					ControleFilesHOT = 1;
					HOTSPOT300DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
					lblNomeHotspot.setText(chooser.getSelectedFile().getName());

					System.out.println(lblNomeHotspot.getText());
				} else {
					System.out.println("n�o � essa firmwware");
					HOTSPOT300DIRCOMPLETO.setText("Arquivo Incorreto");
					lblNomeHotspot.setText("Incorreto");
					ControleFilesHOT = 0;
				}
			}
		});

		HOTSPOT300DIRCOMPLETO = new JLabel("Firmware HotSpot 300");
		HOTSPOT300DIRCOMPLETO.setVisible(false);
		HOTSPOT300DIRCOMPLETO.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// HOTSPOT300DIRCOMPLETO.setBounds(343, 63, 196, 17);
		Updater.getContentPane().add(HOTSPOT300DIRCOMPLETO);

		AP300DIRCOMPLETO = new JLabel("Firmware AP 300");
		AP300DIRCOMPLETO.setVisible(false);
		AP300DIRCOMPLETO.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// AP300DIRCOMPLETO.setBounds(343, 78, 196, 17);
		Updater.getContentPane().add(AP300DIRCOMPLETO);

		WOM5000DIRCOMPLETO = new JLabel("Firmware WOM 5000");
		WOM5000DIRCOMPLETO.setVisible(false);
		WOM5000DIRCOMPLETO.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// WOM5000DIRCOMPLETO.setBounds(343, 92, 196, 17);
		Updater.getContentPane().add(WOM5000DIRCOMPLETO);

		WOM5000MIMODIRCOMPLETO = new JLabel("Firmware WOM 5000 Mimo");
		WOM5000MIMODIRCOMPLETO.setVisible(false);
		WOM5000MIMODIRCOMPLETO.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// WOM5000MIMODIRCOMPLETO.setBounds(331, 121, 224, 17);
		Updater.getContentPane().add(WOM5000MIMODIRCOMPLETO);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "AP 300", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBounds(558, 115, 272, 55);
		Updater.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblNomeAP300 = new JLabel("Nome Arquivo");
		lblNomeAP300.setBounds(14, 21, 141, 14);
		panel_1.add(lblNomeAP300);

		JButton FWAP300 = new JButton("Browse");
		FWAP300.setBounds(167, 18, 91, 23);
		panel_1.add(FWAP300);
		FWAP300.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				FileFilter filter = new FileNameExtensionFilter("My bin", "bin");
				chooser.addChoosableFileFilter(filter);
				int choice = chooser.showOpenDialog(chooser);
				if (choice != JFileChooser.APPROVE_OPTION)
					return;

				AP300DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
				lblNomeAP300.setText(chooser.getSelectedFile().getName());
				if (lblNomeAP300.getText().startsWith("AP")) {
					ControleFilesAP = 1;

					AP300DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
					lblNomeAP300.setText(chooser.getSelectedFile().getName());

				} else {
					System.out.println("n�o � essa firmwware");
					AP300DIRCOMPLETO.setText("Arquivo Incorreto");
					lblNomeAP300.setText("Incorreto");
					ControleFilesAP = 0;
				}
			}

		});
		FWAP300.setForeground(Color.WHITE);
		FWAP300.setBackground(Color.GRAY);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "WOM 5000", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		panel_2.setBounds(558, 178, 272, 55);
		Updater.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lblNomeWOM5000 = new JLabel("Nome Arquivo");
		lblNomeWOM5000.setBounds(14, 22, 141, 14);
		panel_2.add(lblNomeWOM5000);

		JButton FWWOM5000 = new JButton("Browse");
		FWWOM5000.setBounds(167, 18, 91, 23);
		panel_2.add(FWWOM5000);
		FWWOM5000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				FileFilter filter = new FileNameExtensionFilter("My bin", "bin");
				chooser.addChoosableFileFilter(filter);
				int choice = chooser.showOpenDialog(chooser);
				if (choice != JFileChooser.APPROVE_OPTION)
					return;

				WOM5000DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
				lblNomeWOM5000.setText(chooser.getSelectedFile().getName());
				if (lblNomeWOM5000.getText().startsWith("WOM5000" + "-")) {
					ControleFilesWOM = 1;
					WOM5000DIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
					lblNomeWOM5000.setText(chooser.getSelectedFile().getName());
				} else {
					System.out.println("n�o � essa firmwware");
					WOM5000DIRCOMPLETO.setText("Arquivo Incorreto");
					lblNomeWOM5000.setText("Incorreto");
					ControleFilesWOM = 0;
				}
			}
		});
		FWWOM5000.setForeground(Color.WHITE);
		FWWOM5000.setBackground(Color.GRAY);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "WOM 5000i", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		panel_3.setBounds(558, 242, 272, 55);
		Updater.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		lblNomeWOM5000i = new JLabel("Nome Arquivo");
		lblNomeWOM5000i.setBounds(14, 22, 141, 14);
		panel_3.add(lblNomeWOM5000i);

		JButton FWWOM5000i = new JButton("Browse");
		FWWOM5000i.setBounds(167, 18, 91, 23);
		panel_3.add(FWWOM5000i);
		FWWOM5000i.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				FileFilter filter = new FileNameExtensionFilter("My bin", "bin");
				chooser.addChoosableFileFilter(filter);
				int choice = chooser.showOpenDialog(chooser);
				if (choice != JFileChooser.APPROVE_OPTION)
					return;

				WOM5000iDIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
				lblNomeWOM5000i.setText(chooser.getSelectedFile().getName());
				if (lblNomeWOM5000i.getText().contains("WOM5000" + "i")) {
					ControleFilesWOMi = 1;
					WOM5000iDIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
					lblNomeWOM5000i.setText(chooser.getSelectedFile().getName());
				} else {
					System.out.println("n�o � essa firmwware");
					WOM5000iDIRCOMPLETO.setText("Arquivo Incorreto");
					lblNomeWOM5000i.setText("Incorreto");
					ControleFilesWOMi = 0;
				}
			}
		});
		FWWOM5000i.setForeground(Color.WHITE);
		FWWOM5000i.setBackground(Color.GRAY);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "WOM 5000 MiMo", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		panel_4.setBounds(558, 306, 272, 55);
		Updater.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		lblNomeWOM5000MIMO = new JLabel("Nome Arquivo");
		lblNomeWOM5000MIMO.setBounds(14, 22, 141, 14);
		panel_4.add(lblNomeWOM5000MIMO);

		JButton FWWOM5000MIMO = new JButton("Browse");
		FWWOM5000MIMO.setBounds(167, 18, 91, 23);
		panel_4.add(FWWOM5000MIMO);
		FWWOM5000MIMO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				FileFilter filter = new FileNameExtensionFilter("My bin", "bin");
				chooser.addChoosableFileFilter(filter);
				int choice = chooser.showOpenDialog(chooser);

				if (choice != JFileChooser.APPROVE_OPTION)
					return;

				WOM5000MIMODIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
				lblNomeWOM5000MIMO.setText(chooser.getSelectedFile().getName());
				if (lblNomeWOM5000MIMO.getText().startsWith("WOMMiMo")) {
					ControleFilesWOMMIMO = 1;
					WOM5000MIMODIRCOMPLETO.setText(chooser.getSelectedFile().getAbsolutePath());
					lblNomeWOM5000MIMO.setText(chooser.getSelectedFile().getName());
				} else {
					System.out.println("n�o � essa firmwware");
					WOM5000MIMODIRCOMPLETO.setText("Arquivo Incorreto");
					lblNomeWOM5000MIMO.setText("Incorreto");
					ControleFilesWOMMIMO = 0;
				}
			}
		});
		FWWOM5000MIMO.setForeground(Color.WHITE);
		FWWOM5000MIMO.setBackground(Color.GRAY);

		btnApagarcores = new JButton("Limpar Campos");
		btnApagarcores.setForeground(SystemColor.text);
		btnApagarcores.setBackground(SystemColor.textHighlight);
		btnApagarcores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Limpa as cores setadas de acordo com o status
				for (int i = 0; i < Botoes.length; i++) {

					Botoes[i].setText("");
					Botoes[i].setBackground(Color.WHITE);
				}
				textArea.setText(null);

				IP1.setBackground(Color.WHITE);
				IP2.setBackground(Color.WHITE);
				IP3.setBackground(Color.WHITE);
				IPR1.setBackground(Color.WHITE);
				IPR2.setBackground(Color.WHITE);

				IP1.setText("");
				IP2.setText("");
				IP3.setText("");
				IPR1.setText("");
				IPR2.setText("");

				AcrH = 0;
				AcrAP = 0;
				AcrW5 = 0;
				AcrW5i = 0;
				AcrW5M = 0;

				AcratuH = 0;
				AcratuAP = 0;
				AcratuW5 = 0;
				AcratuW5i = 0;
				AcratuW5M = 0;

				String HOTUP = Integer.toString(AcrH);
				String APUP = Integer.toString(AcrAP);
				String W5UP = Integer.toString(AcrW5);
				String W5iUP = Integer.toString(AcrW5i);
				String W5MUP = Integer.toString(AcrW5M);

				String HOTATU = Integer.toString(AcratuH);
				String APATU = Integer.toString(AcratuAP);
				String W5ATU = Integer.toString(AcratuW5);
				String W5iATU = Integer.toString(AcratuW5i);
				String W5MATU = Integer.toString(AcratuW5M);

				LBLAtuaHot.setText(HOTATU);
				LBLAtuaAP.setText(APATU);
				LBLAtuaW5.setText(W5ATU);
				LBLAtuaW5i.setText(W5iATU);
				LBLAtuaW5M.setText(W5MATU);
				LBLEncHot.setText(HOTUP);
				LBLEncAP.setText(APUP);
				LBLEncW5.setText(W5UP);
				LBLEncW5i.setText(W5iUP);
				LBLEncW5M.setText(W5MUP);

			}
		});
		btnApagarcores.setBounds(701, 458, 129, 28);
		Updater.getContentPane().add(btnApagarcores);

		btnStopThread = new JButton("Parar");
		btnStopThread.setForeground(Color.BLACK);
		btnStopThread.setBackground(UIManager.getColor("Button.disabledText"));
		btnStopThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				middle = false;
				t.ThreadControl = true;
				t.state = false;
				t.interrupt();
				try {
					Thread.sleep(3000);

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnListaIps.setEnabled(true);
				btnRangeIps.setEnabled(true);
				btnApagarcores.setEnabled(true);
				btnEnviar.setEnabled(true);
				textArea.append("Parou atualiza��o");

				System.out.println("Parou Thread");

			}
		});
		btnStopThread.setBounds(639, 427, 85, 25);
		Updater.getContentPane().add(btnStopThread);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				t = new Begin();
				t.state = true;
				t.start();

				t.ThreadControl = false;
				System.out.println("Iniciou Thread");

			}
		});

		btnEnviar.setForeground(Color.BLACK);
		btnEnviar.setBackground(UIManager.getColor("Button.disabledText"));

		/////////////////// A��o Bot�o Enviar Abaixo//////////////////////////

		btnEnviar.setBounds(729, 427, 91, 25);
		Updater.getContentPane().add(btnEnviar);

		JLabel lblNewLabel = new JLabel("Log do progresso:");
		lblNewLabel.setBounds(0, 349, 111, 16);
		Updater.getContentPane().add(lblNewLabel);

		btnRangeIps = new JButton("Range Ips");
		btnRangeIps.setForeground(SystemColor.text);
		btnRangeIps.setBackground(SystemColor.textHighlight);
		btnRangeIps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controle = 2;
				for (int i = 0; i < Botoes.length; i++) {
					Botoes[i].setVisible(false);

				}
				IP3.setVisible(true);
				IP2.setVisible(true);
				IP1.setVisible(true);
				IPR2.setVisible(true);
				IPR1.setVisible(true);
				PANEL3Bytes.setVisible(true);
				label.setVisible(true);
				PANERange.setVisible(true);

			}
		});
		btnRangeIps.setBounds(607, 458, 90, 28);
		Updater.getContentPane().add(btnRangeIps);

		btnListaIps = new JButton("Lista IPs");
		btnListaIps.setForeground(SystemColor.text);
		btnListaIps.setBackground(SystemColor.textHighlight);
		btnListaIps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controle = 1;
				
				IP3.setVisible(false);
				IP2.setVisible(false);
				IP1.setVisible(false);
				IPR2.setVisible(false);
				IPR1.setVisible(false);
				PANEL3Bytes.setVisible(false);
				label.setVisible(false);
				PANERange.setVisible(false);

				int Va = 106;
				int Vb = 106;
				int Vc = 106;
				int Vd = 106;

				int Hb = 20;
				int Hc = 116;
				int Hd = 222;
				int He = 328;

				for (int i = 0; i < 8; i++) {
					Botoes[i].setVisible(true);
					Botoes[i].setBounds(Hb, Va, 70, 20);
					Botoes[i].setToolTipText("192.168.0.1");
					Botoes[i].setFont(new Font("Monospaced", Font.PLAIN, 10));
					Updater.getContentPane().add(Botoes[i]);
					Va += 31;
				}
				for (int b = 8; b < 11; b++) {
					Botoes[b].setVisible(true);
					Botoes[b].setBounds(Hc, Vb, 70, 20);
					Botoes[b].setToolTipText("192.168.0.1");
					Botoes[b].setFont(new Font("Monospaced", Font.PLAIN, 10));
					Updater.getContentPane().add(Botoes[b]);
					Vb += 31;

				}
				/*for (int c = 16; c < 24; c++) {
					Botoes[c].setVisible(true);
					Botoes[c].setBounds(Hd, Vc, 70, 20);
					Botoes[c].setToolTipText("192.168.0.1");
					Botoes[c].setFont(new Font("Monospaced", Font.PLAIN, 10));
					Updater.getContentPane().add(Botoes[c]);
					Vc += 31;

				}
				for (int d = 24; d < 32; d++) {
					Botoes[d].setVisible(true);
					Botoes[d].setBounds(He, Vd, 70, 20);
					Botoes[d].setToolTipText("192.168.0.1");
					Botoes[d].setFont(new Font("Monospaced", Font.PLAIN, 10));
					Updater.getContentPane().add(Botoes[d]);
					Vd += 31;

				}*/

			}
		});
		btnListaIps.setBounds(507, 458, 90, 28);
		Updater.getContentPane().add(btnListaIps);

	}

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					WindowApp window = new WindowApp();
					window.Updater.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
