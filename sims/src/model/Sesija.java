/***********************************************************************
 * Module:  Sesija.java
 * Author:  Dragan
 * Purpose: Defines the Class Sesija
 ***********************************************************************/
package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import controler.CitacDatoteka;
import controler.Constants;
import controler.GlasanjeMenadzer;
import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.IzvestajSvihZanrovaMenadzer;
import controler.IzvodjacMenadzer;
import controler.KorisniciMenadzer;
import controler.LoginMenadzer;
import controler.MuzickiSadrzajMenadzer;
import controler.RecenzijeZaIzmenuMenadzer;
import controler.ReklameMenadzer;
import controler.ToplisteMenadzer;
import controler.UtisakMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import controler.ZanroviMenadzer;
import view.AdminHomepage;
import view.Homepage;
import view.KorisnikAplikacijeHomepage;
import view.UrednikHomepage;

/** @pdOid a6536d8d-e436-4d30-9c5d-e31219285ea3 */
public class Sesija {
   /** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association29 mult=1..1 */
   private KorisniciMenadzer korisnici;
   
   private IzvodjacMenadzer izvodjacMenadzer;
   
   private ZanroviMenadzer zanroviMenadzer;
   
   private MuzickiSadrzajMenadzer muzickiSadrzajMenadzer;
   private ToplisteMenadzer toplisteMenadzer;
   private ReklameMenadzer reklameMenadzer;
   private ZakazanaRecenzijaMenadzer zakazanaRecenzijaMenadzer;
   
  
   
   private GlasanjeMenadzer glasanjeMenadzer;
   
   private RecenzijeZaIzmenuMenadzer recenzijeZaIzmenuMenadzer;
   
   private static Korisnik trenutniKorisnik;
   
   private UtisakMenadzer utisakMenadzer;
   

   private IzvestajSvihZanrovaMenadzer izvestajSvihZanrova;// ovo ne treba da bude inicijalizovano, inic se u
                                                           // izvestajima kod admina

   
   private IzvestajSvihIzvodjacaMenadzer menIzvodjaca;// ovo ne treba biti inic, nego tek kad se pokrene izv
   private LoginMenadzer loginMenadzer;

   private static Sesija trenutnaSesija;
   

   

   /** @pdOid 2750728b-3647-44d9-803c-9a8cbcd00047 */
   public void odjava() {
      // TODO: implement
   }



   /**
    * @return the trenutniKorisnik
    */
   public static Korisnik getTrenutniKorisnik() {
      return trenutniKorisnik;
   }

   /**
    * @param trenutniKorisnik the trenutniKorisnik to set
    */
   public static void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
      Sesija.trenutniKorisnik = trenutniKorisnik;
   }

   /**
    * @return the korisnici
    */
   public KorisniciMenadzer getKorisnici() {
      return korisnici;
   }

   /**
    * @param korisnici the korisnici to set
    */
   public void setKorisnici(KorisniciMenadzer korisnici) {
      this.korisnici = korisnici;
   }
   
   public IzvodjacMenadzer getIzvodjacMenadzer() {
	   return izvodjacMenadzer;
   }
   
   public void setIzvodjacMenadzer(IzvodjacMenadzer izvodjacMenadzer) {
	   this.izvodjacMenadzer = izvodjacMenadzer;
   }
   
   public ZanroviMenadzer getZanroviMenadzer() {
	   return zanroviMenadzer;
   }
   
   public void setZanroviMenadzer(ZanroviMenadzer zanroviMenadzer) {
	   this.zanroviMenadzer = zanroviMenadzer;
   }
   
   public ZakazanaRecenzijaMenadzer getZakazanaRecenzijaMenadzer() {
	   return zakazanaRecenzijaMenadzer;
   }
   
   public void setZakazanaRecenzijaMenadzer(ZakazanaRecenzijaMenadzer zakazanaRecenzijaMenadzer) {
	   this.zakazanaRecenzijaMenadzer = zakazanaRecenzijaMenadzer;
   }
   
   public GlasanjeMenadzer getGlasanjeMenadzer() {
	   return glasanjeMenadzer;
   }
   
   public void setGlasanjeMenadzer(GlasanjeMenadzer glasanjeMenadzer) {
	   this.glasanjeMenadzer = glasanjeMenadzer;
   }
   
   public RecenzijeZaIzmenuMenadzer getRecenzijeZaIzmenuMenadzer() {
	   return recenzijeZaIzmenuMenadzer;
   }
   
   public void setRecenzijeZaIzmenuMenadzer(RecenzijeZaIzmenuMenadzer recenzijeZaIzmenuMenadzer) {
	   this.recenzijeZaIzmenuMenadzer = recenzijeZaIzmenuMenadzer;
   }
   
   public UtisakMenadzer getUtisakMenadzer() {
	   return utisakMenadzer;
   }
   
   public void setUtisakMenadzer(UtisakMenadzer utisakMenadzer) {
	   this.utisakMenadzer = utisakMenadzer;
   }
   

   
   public void setZakazanaRecenzija(Recenzija recenzija, Urednik urednik) {
	   for (ZakazanaRecenzija zr : zakazanaRecenzijaMenadzer.getSve()) {
		   if (zr.getRecenzija().getNaslov().equals(recenzija.getNaslov())) {
			   zr.setRecenzija(recenzija);
			   zr.setUrednik(urednik);
			   break;
		   }
	   }
   }
   
   public void setRecenzijaZaIzmenu(Recenzija recenzija) {
	   for (RecezijaZaIzmenu rzi : recenzijeZaIzmenuMenadzer.getSveizmene()) {
		   if (rzi.getRecenzija().getNaslov().equals(recenzija.getNaslov())) {
			   rzi.setRecenzija(recenzija);
			   break;
		   }
	   }
   }
   
   public void setMuzickaDelaIzvodjaci() {
	   for (MuzickoDelo md : muzickiSadrzajMenadzer.getMuzickaDela()) {
		   for (Izvodjac i : izvodjacMenadzer.getSvi()) {
			   if (md.getIzvodjac().getUmetnickoIme().equals(i.getUmetnickoIme())) {
				   i.addDelo(md);
			   }
		   }
	   }
   }
   
   public void setIzdateAlbume() {
	   for (Album a : muzickiSadrzajMenadzer.getAlbumi()) {
		   if (a.isOdobreno() && a.isStatus()) {
			   for (Izvodjac i : izvodjacMenadzer.getSvi()) {
				   if (a.getIzvodjac().getUmetnickoIme().equals(i.getUmetnickoIme())) {
					   i.addIzdatAlbum(a);
				   }
			   }
		   }
	   }
   }
   
   public void setRecenzijeZaMuzickoDelo() {
	   for (MuzickoDelo md : muzickiSadrzajMenadzer.getMuzickaDela()) {
		   if (md.isStatus()) {
			   for (Recenzija r : utisakMenadzer.getRecenzije()) {
				   if (r.getDelo().getNaslov().equals(md.getNaslov())) {
					   md.getUtisci().add(r);
				   }
			   }
			   for (Komentar k : utisakMenadzer.getKomentari()) {
				   if (k.getDelo().getNaslov().equals(md.getNaslov())) {
					   md.getUtisci().add(k);
				   }
			   }
		   }
	   }
   }
   
   public void setZakazaneIIzemeneUrednike() {
	   for (ZakazanaRecenzija zr : zakazanaRecenzijaMenadzer.getSve()) {
		   for (Urednik u : getUrednici()) {
			   if (zr.getUrednik().getNalog().getKorisnickoIme().equals(u.getNalog().getKorisnickoIme())) {
				   u.getZakazaneRecenzije().add(zr);
			   }
		   }
	   }
	   for (RecezijaZaIzmenu rzi : recenzijeZaIzmenuMenadzer.getSveizmene()) {
		   for (Urednik u : getUrednici()) {
			   if (rzi.getRecenzija().getUrednik().getNalog().getKorisnickoIme().equals(u.getNalog().getKorisnickoIme())) {
				   u.getRecezijaZaIzmenu().add(rzi);
			   }
		   }
	   }
   }

   public static Sesija namestiSesiju(Korisnik korisnik, CitacDatoteka datoteke, LoginMenadzer menadzer) {
      // TODO Auto-generated method stub
      if (trenutnaSesija != null) {
         //trenutnaSesija.setTrenutniKorisnik(korisnik);
    	  Sesija.setTrenutniKorisnik(korisnik);
         return trenutnaSesija;
      } else {
         trenutnaSesija = new Sesija(korisnik, datoteke.getKorisnici(), datoteke.getIzvodjaci(), datoteke.getZanrovi(), datoteke.getMuzickiSadrzajMenadzer(),
        		datoteke.getToplisteMenadzer(), datoteke.getReklameMenadzer(), datoteke.getMuzickiSadrzajMenadzer().getMuzickaDela()
        		 , datoteke.getGrupe(),
               datoteke.getIzvodjaci().getSolo(), datoteke.getRecenzije(), datoteke.getUtisakmenadzer(), datoteke.getZakRecMenadzer(), datoteke.getIzmena(), datoteke.getGlasanjeMenadzer(),menadzer);
         return trenutnaSesija;
      }
   }

   private Sesija(Korisnik trenutniKorisnik, KorisniciMenadzer korisnici, IzvodjacMenadzer izvodjacMenadzer, ZanroviMenadzer zanroviMenadzer, MuzickiSadrzajMenadzer muzickiSadrzajMenadzer,
		   ToplisteMenadzer toplisteMenadzer, ReklameMenadzer reklameMenadzer, Collection<MuzickoDelo> dela, Collection<Grupa> grupe, Collection<Pojedinacanizvodjac> umetnici, 
		   Collection<Recenzija> recenzije, UtisakMenadzer utisakMenadzer,ZakazanaRecenzijaMenadzer zakazanaRecenzijaMenadzer,
         RecenzijeZaIzmenuMenadzer recenzijeZaIzmenuMenadzer, GlasanjeMenadzer glasanjeMenadzer, LoginMenadzer loginMenadzer) {
      super();
      this.setKorisnici(korisnici);
      this.setIzvodjacMenadzer(izvodjacMenadzer);
      this.setZanroviMenadzer(zanroviMenadzer);
      this.setMuzickiSadrzajMenadzer(muzickiSadrzajMenadzer);
      this.setToplisteMenadzer(toplisteMenadzer);
      this.setUtisakMenadzer(utisakMenadzer);
      this.setZakazanaRecenzijaMenadzer(zakazanaRecenzijaMenadzer);
      this.setRecenzijeZaIzmenuMenadzer(recenzijeZaIzmenuMenadzer);
      //this.setTrenutniKorisnik(trenutniKorisnik);
      Sesija.setTrenutniKorisnik(trenutniKorisnik);
      this.setGlasanjeMenadzer(glasanjeMenadzer);
      setMuzickaDelaIzvodjaci();
      setIzdateAlbume();
      setRecenzijeZaMuzickoDelo();
      setZakazaneIIzemeneUrednike();
      this.setReklameMenadzer(reklameMenadzer);
	this.loginMenadzer = loginMenadzer;
}


public void izvrsi() {
	// TODO Auto-generated method stub
	
	Homepage homepage;
	
	if (trenutniKorisnik instanceof Administrator) {
		homepage = new AdminHomepage(this);
	} else if (trenutniKorisnik instanceof Urednik) {
		homepage = new UrednikHomepage(this);
	} else {
		homepage = new KorisnikAplikacijeHomepage(this);
	}
	
	homepage.setVisible(true);
	homepage.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			loginMenadzer.uloguj();
		}
	});
	
}


public IzvestajSvihZanrovaMenadzer namestiIzvestaj() {
	   this.izvestajSvihZanrova=new IzvestajSvihZanrovaMenadzer(this.getMuzickiSadrzajMenadzer().getMuzickaDela(), this.utisakMenadzer.getRecenzije(),this.zanroviMenadzer.getSviZanrovi());
	   
	return izvestajSvihZanrova;
	   
   }
   public IzvestajSvihZanrova pronadjiPodatkejednogZanra(String naziv) {
	   IzvestajSvihZanrova jedanZanr = new IzvestajSvihZanrova(naziv);
	   pronadiDela(naziv, jedanZanr);
	   return jedanZanr;
   }


private void pronadiDela(String naziv, IzvestajSvihZanrova jedanZanr) {
	for(MuzickoDelo d:this.muzickiSadrzajMenadzer.getMuzickaDela()) {
		for(Zanr z:d.getZanrovi()) {
			if(z.getNazivZanra().equals(naziv)) {
				for(Utisak u:d.getUtisci()) {
					if(u instanceof Recenzija) {
				jedanZanr.setBrojRecenzija(jedanZanr.getBrojRecenzija()+1);
					}else {
						jedanZanr.setBrojKOmentara(jedanZanr.getBrojKOmentara()+1);
					}
				jedanZanr.setBrojMuzdela(jedanZanr.getBrojMuzdela()+1);
				break;
			}
		}
	}
	
}
}
   public IzvestajSvihIzvodjacaMenadzer namestiIzvestajIzvodjaca() {
	   this.menIzvodjaca=new IzvestajSvihIzvodjacaMenadzer();
	   ArrayList<Izvodjac> sviizv=new ArrayList<Izvodjac>();
	   for(Grupa g : this.izvodjacMenadzer.getGrupe()) { sviizv.add(g);}
	   
	   for(Pojedinacanizvodjac p : this.izvodjacMenadzer.getSolo()) {sviizv.add(p);}
	   this.menIzvodjaca.namestiIzvestaj(sviizv);
	   
	   return this.menIzvodjaca;
   }
   public IzvestajJednogIzvodjaca namestiJedanizvestaj(Izvodjac i) {
	   IzvestajJednogIzvodjaca jedan=new IzvestajJednogIzvodjaca(i.getUmetnickoIme());
	   jedan.setIzvodjacReferenca(i);
		jedan.setBrojDela(i.getMuzickaDela().size());
		double ocenaKo=0;
		double ocenaUr=0; 
		for(MuzickoDelo m:i.getMuzickaDela()) {
			ocenaKo+=m.getProsecnaOcenaKorisnika();
			ocenaUr+=m.getProsecnaOcenaUrednika();
			for(Utisak u:m.getUtisci()) {
				if(u instanceof Recenzija) {
					jedan.setBrojRecenzija(jedan.getBrojRecenzija()+1);
				}else {
					jedan.setBrojKomentara(jedan.getBrojKomentara()+1);
				}
			}
		}
		jedan.setOcenaKorisnika(ocenaKo/i.getMuzickaDela().size());
		jedan.setOcenaUrednika(ocenaUr/i.getMuzickaDela().size());
	   return jedan;
	   
   }
   public String[] izvadiImenaIzvodjaca() {
	   String[] imena = new String[this.izvodjacMenadzer.getSvi().size()];
	   ArrayList<Izvodjac> izvodjaci = this.izvodjacMenadzer.getSvi();
	   for(int i = 0; i < izvodjaci.size(); ++i ) 
		   if (izvodjaci.get(i).isOdobrenost())
			   imena[i] = izvodjaci.get(i).getUmetnickoIme();
	   return imena;
	   
	   
   }
public String[] izvadiImenaDela(String ime) {
	
	 ArrayList<Izvodjac> izvodjaci = this.izvodjacMenadzer.getSvi();
	   for(int i = 0; i <izvodjaci.size(); ++i ) if(izvodjaci.get(i).getUmetnickoIme().equals(ime)) return izvodjaci.get(i).getImenaDela();
	String[] s= {""};
	return s;
	
}


public Izvodjac getIzvodjac(String ime) {
	
	ArrayList<Izvodjac> izvodjaci = this.izvodjacMenadzer.getSvi();
	   for(int i = 0; i <izvodjaci.size(); ++i ) if(izvodjaci.get(i).getUmetnickoIme().equals(ime)) return izvodjaci.get(i);

	return null;
}

public boolean napraviDelo(String datumIzdavanja, String naslov, String opis, Izvodjac izv, ArrayList<Zanr> zanrovi) {
	try {
		MuzickoDelo md = new MuzickoDelo(naslov, opis, Constants.NATASIN_FORMAT_ZA_DATUM.parse(datumIzdavanja), 
				izv, (Urednik)Sesija.getTrenutniKorisnik(), true, zanrovi, 0, 0);
		for (Izvodjac i : izvodjacMenadzer.getSvi()) {
			if (i.getUmetnickoIme().equals(izv.getUmetnickoIme())) {
				i.addDelo(md);
				break;
			}
		}
		if (muzickiSadrzajMenadzer.postojiDelo(md))
			return false;
		
		this.muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(md);
		this.muzickiSadrzajMenadzer.getMuzickaDela().add(md);
		return true;
	}
	catch(ParseException e) {
		return false;
	}
}

public IzvestajSvihZanrovaMenadzer getIzvestajSvihZanrova() {
	return this.izvestajSvihZanrova;
}


public void addRecenzije(Recenzija recenzija) {
	this.utisakMenadzer.getRecenzije().add(recenzija);
	
}


public ArrayList<Recenzija> getRecenzije() {
	// TODO Auto-generated method stub
	return this.utisakMenadzer.getRecenzije();
}


public ArrayList<Pojedinacanizvodjac> getUmetnici() {
	// TODO Auto-generated method stub
	return this.izvodjacMenadzer.getSolo();
}


public ArrayList<Grupa> getGrupe() {
	// TODO Auto-generated method stub
	return this.izvodjacMenadzer.getGrupe();
}


public boolean addUmetnici(Pojedinacanizvodjac pi) {
	for(Pojedinacanizvodjac s : this.izvodjacMenadzer.getSolo()) {
		if(pi.getUmetnickoIme().equals(s.getUmetnickoIme())) 
			return false;
	}
	this.izvodjacMenadzer.dodaj(pi);
	return true;
}


public boolean addGrupe(Grupa pi) {
	for(Grupa s : this.izvodjacMenadzer.getGrupe()) {
		if(pi.getUmetnickoIme().equals(s.getUmetnickoIme())) 
			return false;
	}
	this.izvodjacMenadzer.dodaj(pi);
	return true;
}


@SuppressWarnings("unchecked")
public Collection<Urednik> getUrednici() {
	// TODO Auto-generated method stub
	return (Collection<Urednik>)(Collection<?>) this.korisnici.vratiUrednike();
}


public MuzickiSadrzajMenadzer getMuzickiSadrzajMenadzer() {
	return muzickiSadrzajMenadzer;
}


public void setMuzickiSadrzajMenadzer(MuzickiSadrzajMenadzer muzickiSadrzajMenadzer) {
	this.muzickiSadrzajMenadzer = muzickiSadrzajMenadzer;
}





public ToplisteMenadzer getToplisteMenadzer() {
	return toplisteMenadzer;
}





public void setToplisteMenadzer(ToplisteMenadzer toplisteMenadzer) {
	this.toplisteMenadzer = toplisteMenadzer;
}





public ReklameMenadzer getReklameMenadzer() {
	return reklameMenadzer;
}





public void setReklameMenadzer(ReklameMenadzer reklameMenadzer) {
	this.reklameMenadzer = reklameMenadzer;
}



public ArrayList<MuzickoDelo> filtrirajDela(Zanr z) {
	ArrayList<MuzickoDelo> dela=new ArrayList<MuzickoDelo>();
	for(MuzickoDelo d:this.muzickiSadrzajMenadzer.getMuzickaDela()) {
		for(Zanr z1:d.getZanrovi()) {
			if(z.getNazivZanra().equals(z1.getNazivZanra())) {
				dela.add(d);
				break;
			}
		}
	}
	return dela;
}

	
}