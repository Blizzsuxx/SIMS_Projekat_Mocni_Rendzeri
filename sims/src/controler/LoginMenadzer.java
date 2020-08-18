package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Korisnik;
import model.KorisnikAplikacije;
import model.Par;
import model.Pol;
import model.Sesija;
import view.DijalogKorisnickihInformacija;
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
	/** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association28 mult=1..1 side=A */
	private KorisniciMenadzer korisnici;
	private CitacDatoteka datoteke;
	
	
	
	
   private void zapocniSesiju(Korisnik korisnik) {
      // TODO: implement
	   Sesija sesija = Sesija.namestiSesiju(korisnik, datoteke, this);
	   sesija.izvrsi();
   }
   
   
   private void initActions() {
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
		   
		   prozor.addWindowListener(new WindowAdapter() {
			   @Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				datoteke.sacuvaj();
			}
		});
   }
   
   
   public LoginMenadzer(CitacDatoteka datoteke) {
	super();
	this.korisnici = datoteke.getKorisnici();
	this.korisnik = null;
	this.datoteke = datoteke;
	prozor = new LoginWindow(this);
	
	initActions();
	
	
}

   
   /** @pdOid 4bf09b27-0f29-425d-8c6b-7dd4b7072a68 */
   public void registruj() {
      // TODO: implement
	   KorisnikAplikacije novKorisnik = new KorisnikAplikacije();
	   DijalogKorisnickihInformacija dijalog = new DijalogKorisnickihInformacija(prozor, novKorisnik) {
		   @Override
		   protected void buttonTriggered() {
			   if(novKorisnik.getNalog() != null && !korisnici.getKorisnici().containsKey(novKorisnik.getKorisnickoIme())) {
					korisnici.getKorisnici().put(novKorisnik.getKorisnickoIme(), novKorisnik);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Molimo vas da popunite sva polja.");
				}
		   }
	   };
   }
   
   /** @pdOid e473b5cd-a8f2-4a1b-914c-5ab62e48c45f */
   public void uloguj() {
      // TODO: implement
	   prozor.setVisible(true);
   }
   
   
   private void pokusajUlogovanje(String korisnickoIme, String sifra) {
	   if(korisnickoIme == null && sifra == null) {
		   prozor.setVisible(false);
		   zapocniSesiju(null);
		   return;
	   }
	   korisnik = korisnici.getKorisnici().get(korisnickoIme);
	   if(korisnik == null || !korisnik.getNalog().potvrdiSifru(sifra)){
		   return;
	   }
	   prozor.setVisible(false);
	   zapocniSesiju(korisnik);
	   
   }
}