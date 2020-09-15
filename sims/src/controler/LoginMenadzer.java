package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JOptionPane;

import model.Korisnik;
import model.KorisnikAplikacije;
import model.Sesija;
import view.DijalogKorisnickihInformacija;
import view.LoginWindow;

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
	   
	   prozor.getSifra().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pokusajUlogovanje(prozor.getKorisnickoIme().getText(), String.valueOf(prozor.getSifra().getPassword()));
		}
	});
	   
	   
	   prozor.getKorisnickoIme().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
				try {
					datoteke.sacuvaj();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
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
   
   //test 

   
   /** @pdOid 4bf09b27-0f29-425d-8c6b-7dd4b7072a68 */
   public void registruj() {
      // TODO: implement
	   KorisnikAplikacije novKorisnik = new KorisnikAplikacije();
	   DijalogKorisnickihInformacija dijalog = new DijalogKorisnickihInformacija(prozor, novKorisnik) {
		private static final long serialVersionUID = 1L;

		@Override
		   protected void registruj() {
			   try{
					super.registruj();
			   } catch (Exception e){
					JOptionPane.showMessageDialog(this, e.getMessage());
					return;
			   }
			   if(novKorisnik.getNalog() != null && !korisnici.provjeriKorisnickoIme(novKorisnik.getNalog().getKorisnickoIme())) {
					korisnici.dodaj(novKorisnik);
					this.dispose();
					prozor.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Korisnik sa takvim korisnickim imenom vec postoji");
				}
		   }
	   };
	   prozor.setVisible(false);
	   dijalog.setVisible(true);
   }
   
   /** @pdOid e473b5cd-a8f2-4a1b-914c-5ab62e48c45f */
   public void uloguj() {
      // TODO: implement
	   prozor.setVisible(true);
	   prozor.setKorisnickoIme("");
	   prozor.setSifra("");
   }
   
   
   private void pokusajUlogovanje(String korisnickoIme, String sifra) {
	   if(korisnickoIme == null && sifra == null) {
		   prozor.setVisible(false);
		   zapocniSesiju(null);
		   return;
	   }
	   korisnik = korisnici.trazi(korisnickoIme);
	   if(korisnik == null || !korisnik.getNalog().potvrdiSifru(sifra)){
		   JOptionPane.showMessageDialog(prozor, "Pogresni username ili sifra");
		   return;
	   }
	   if (!korisnik.isStatus()) {
		   JOptionPane.showMessageDialog(prozor, "Ovaj nalog je blokiran!");
		   return;
	   }
	   prozor.setVisible(false);
	   zapocniSesiju(korisnik);
	   
   }
}