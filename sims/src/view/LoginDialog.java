package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LoginDialog extends MojDialog {
	private static final long serialVersionUID = 1L;
	
	private JButton btnOk, btnCancel;
	private JTextField tfKorisnickoIme;
	private JPasswordField tfSifra;
	
	public LoginDialog(JFrame glavniProzor) {
		super("Prijava Korisnika", 200, 200);
		
		this.initGUI();
		this.actionGUI();
		this.pack();
	}
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 1", "[]", "[]10[]10[]10[]25[]");
		setLayout(mig);
		
		add(new JLabel("Korisnicko Ime:"));
		tfKorisnickoIme = new JTextField(20);
		add(tfKorisnickoIme);
		
		add(new JLabel("Sifra:"));
		tfSifra = new JPasswordField(20);
		add(tfSifra);
		
		btnOk = new JButton("Ok");
		add(btnOk, "split");
		btnCancel = new JButton("Cancel");
		add(btnCancel);
	}
	
	private void actionGUI() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.dispose();
				
			}
			
		});
	}
}
