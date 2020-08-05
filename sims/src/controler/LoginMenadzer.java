package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.Korisnik;
import model.KorisnikAplikacije;
import model.Par;
import model.Pol;
import view.EditorPanel;
import view.LoginWindow;
import view.MojDialog;

/***********************************************************************
 * Module:  LoginMenadzer.java
 * Author:  Dragan
 * Purpose: Defines the Class LoginMenadzer
 ***********************************************************************/

/** @pdOid 2b1b06aa-dc54-4379-b514-e1647f805308 */
public class LoginMenadzer {
   /** @pdOid 5896f2f3-149c-4da2-bde4-a305085b14a8 */
	
	private Korisnik korisnik;
	private LoginWindow prozor;
	
	
   private void zapocniSesiju() {
      // TODO: implement
   }
   
   public LoginMenadzer(KorisniciMenadzer korisnici) {
	super();
	this.korisnici = korisnici;
	this.korisnik = null;
	prozor = new LoginWindow(this);
}

/** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association28 mult=1..1 side=A */
   private KorisniciMenadzer korisnici;
   
   /** @pdOid 4bf09b27-0f29-425d-8c6b-7dd4b7072a68 */
   public void registruj() {
      // TODO: implement
	   JDialog dialog = new MojDialog(prozor, "Registrovanje", 450, 250);
	   Korisnik novKorisnik = new KorisnikAplikacije();
	   EditorBuilder editor = new EditorBuilder(Constants.POLJA_ZA_REGISTRACIJU, novKorisnik, Constants.ATRIBUTI_ZA_REGISTRACIJU);
	   EditorPanel panel = new EditorPanel();
	   JRadioButton muski = new JRadioButton("Muski");
	   JRadioButton zenski = new JRadioButton("Zenski");
	   zenski.setSelected(true);
	   ButtonGroup pol = new ButtonGroup();
	   pol.add(muski);
	   pol.add(zenski);
	   panel.add(muski);
	   panel.add(zenski, "wrap");
	   
	   dialog.setContentPane(panel);
	   
	   
	   panel.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(muski.isSelected()) {
				novKorisnik.setPol(Pol.muski);
			} else {
				novKorisnik.setPol(Pol.zenski);
			}
			if(novKorisnik.getNalog() != null && !korisnici.getKorisnici().containsKey(novKorisnik.getKorisnickoIme())) {
				korisnici.getKorisnici().put(novKorisnik.getKorisnickoIme(), novKorisnik);
				dialog.dispose();
			} else {
				JOptionPane.showMessageDialog(dialog, "Molimo vas da popunite sva polja.");
			}
		}
	});
	   editor.napraviPanel(panel);
	   dialog.setVisible(true);
   }
   
   /** @pdOid e473b5cd-a8f2-4a1b-914c-5ab62e48c45f */
   public Korisnik uloguj() {
      // TODO: implement
	   korisnik = null;
	   prozor.setVisible(true);
	   
	   prozor.getLogButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pokusajUlogovanje(prozor.getKorisnickoIme().getText(), String.valueOf(prozor.getSifra().getPassword()));
		}
	});
	   
	   prozor.getGuestButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pokusajUlogovanje(null, null);
		}
	});
	   
	   
	   prozor.getRegisterButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			registruj();
		}
	});
	   return korisnik;
   }
   
   
   private void pokusajUlogovanje(String korisnickoIme, String sifra) {
	   if(korisnickoIme == null || sifra == null) {
		   prozor.setVisible(false);
		   prozor.dispose();
		   return;
	   }
	   korisnik = korisnici.getKorisnici().get(korisnickoIme);
	   if(korisnik == null || !korisnik.getNalog().potvrdiSifru(sifra)){
		   return;
	   }
	   prozor.setVisible(false);
	   prozor.dispose();
	   
   }
}