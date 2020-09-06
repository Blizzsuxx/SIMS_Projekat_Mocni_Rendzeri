package view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXLoginPane;

import controler.LoginMenadzer;
import net.miginfocom.swing.MigLayout;

public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton logButton;
	private JButton registerButton;
	private JButton guestButton;
	private JTextField korisnickoIme;
	private JPasswordField sifra;
	
	
	public LoginWindow(LoginMenadzer loginMenadzer) throws HeadlessException {
		// TODO Auto-generated constructor stub
		getContentPane().setLayout(new MigLayout());
		setSize(425, 275);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		
		JXLoginPane pane = new JXLoginPane();
		this.add(new JLabel(new ImageIcon(pane.getBanner())), "north");
		
		JLabel korisnickoIme = new JLabel("Korisnicko Ime");
		JLabel sifra = new JLabel("Sifra");
		
		JTextField unosKorisnickogImena = new JTextField(25);
		JPasswordField unosSifre = new JPasswordField(25);
		
		add(korisnickoIme);
		add(unosKorisnickogImena, "wrap");
		add(sifra);
		add(unosSifre, "wrap");
		
		JButton ulogujSe = new JButton("Uloguj se");
		JButton gost = new JButton("Udji kao gost");
		JButton registracija = new JButton("Registruj se");
		
		add(ulogujSe, "wrap");
		add(gost, "gaptop 30");
		add(registracija, "gaptop 30");
		logButton = ulogujSe;
		guestButton = gost;
		registerButton = registracija;
		this.korisnickoIme = unosKorisnickogImena;
		this.sifra = unosSifre;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, 
						"Jeste sigurni da zelite izaci iz aplikacije?", "IZLAZ", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
	}

	public JTextField getKorisnickoIme() {
		return korisnickoIme;
	}

	public JPasswordField getSifra() {
		return sifra;
	}

	public JButton getLogButton() {
		return logButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getGuestButton() {
		return guestButton;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme.setText(korisnickoIme);
	}

	public void setSifra(String sifra) {
		this.sifra.setText(sifra);
	}

	
}
