package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Sesija;
import model.Uloga;
import net.miginfocom.swing.MigLayout;

public class LoginProzor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton btnPrijava, btnRegistracija, btnNeulogovaniKorisnik;
	private Sesija sesija;
	
	public LoginProzor(Sesija sesija) throws HeadlessException {
		this.sesija = sesija;
		this.setTitle("Muzicki Katalog");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(LoginProzor.this, 
						"Jeste sigurni da zelite izaci iz aplikacije?", "IZLAZ", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		this.initGUI();
		this.actionGUI();
	}
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 3", "30[]10[]10[]10");
		JPanel base = new JPanel();
		base.setLayout(mig);
		
		btnPrijava = new JButton("Prijava");
		btnRegistracija = new JButton("Registracija");
		btnNeulogovaniKorisnik = new JButton("Neulogovani Korisnik");
		
		base.add(btnPrijava);
		base.add(btnRegistracija);
		base.add(btnNeulogovaniKorisnik);
		
		this.add(base, BorderLayout.NORTH);
	}

	private void actionGUI() {
		btnPrijava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginDialog ld = new LoginDialog(LoginProzor.this);
				ld.setVisible(true);
				
			}
			
		});
		
		btnRegistracija.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // VRATITI SE NA OVO
				KorisnikAddEdit kae = new KorisnikAddEdit("Registracija korisnika" ,Uloga.KORISNIK, sesija, new ArrayList<>());
				kae.setVisible(true);
				
			}
			
		});
		
		btnNeulogovaniKorisnik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
