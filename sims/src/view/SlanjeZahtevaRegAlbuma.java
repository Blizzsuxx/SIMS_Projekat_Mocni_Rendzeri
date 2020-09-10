package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controler.KorisniciMenadzer;
import model.Sesija;
import model.Urednik;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SlanjeZahtevaRegAlbuma extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtNazivAlbuma;
	private Sesija sesija;
	private String title;
	
	public SlanjeZahtevaRegAlbuma(Sesija sesija, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNazivAlbuma = new JLabel("Naziv albuma");
		lblNazivAlbuma.setBounds(10, 11, 72, 14);
		getContentPane().add(lblNazivAlbuma);
		
		txtNazivAlbuma = new JTextField();
		txtNazivAlbuma.setBounds(86, 8, 348, 20);
		getContentPane().add(txtNazivAlbuma);
		txtNazivAlbuma.setColumns(10);
		
		JButton btnPosalji = new JButton("Posalji zahtev");
		btnPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazivAlbuma = txtNazivAlbuma.getText();
				if (nazivAlbuma.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Morate uneti naziv albuma.");
					return;
				}
				if (Sesija.getTrenutniKorisnik().getClass() == Urednik.class) {
					KorisniciMenadzer km = sesija.getKorisnici();
					km.dodajZahtevUrednika((Urednik)Sesija.getTrenutniKorisnik(), nazivAlbuma);
					sesija.setKorisnici(km);
				}
			}
		});
		btnPosalji.setBounds(324, 52, 110, 23);
		getContentPane().add(btnPosalji);
		
		setVisible(true);
	}
}
