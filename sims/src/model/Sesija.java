/***********************************************************************
 * Module:  Sesija.java
 * Author:  Dragan
 * Purpose: Defines the Class Sesija
 ***********************************************************************/
package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import controler.CitacDatoteka;
import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.IzvestajSvihZanrovaMenadzer;
import controler.IzvodjacMenadzer;
import controler.KorisniciMenadzer;
import controler.LoginMenadzer;
import view.KorisnikAplikacijeHomepage;

/** @pdOid a6536d8d-e436-4d30-9c5d-e31219285ea3 */
public class Sesija {
   /** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association29 mult=1..1 */
   private KorisniciMenadzer korisnici;
   
   private IzvodjacMenadzer izvodjacMenadzer;
   /**
    * @pdRoleInfo migr=no name=MuzickoDelo assc=association38
    *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
    */
   private java.util.Collection<MuzickoDelo> dela;
   /**
    * @pdRoleInfo migr=no name=Grupa assc=association42 coll=java.util.Collection
    *             impl=java.util.HashSet mult=0..*
    */
   private java.util.Collection<Grupa> grupe;
   /**
    * @pdRoleInfo migr=no name=Pojedinacanizvodjac assc=association43
    *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
    */
   private java.util.Collection<Pojedinacanizvodjac> umetnici;
   /**
    * @pdRoleInfo migr=no name=Recenzija assc=association44
    *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
    */
   private java.util.Collection<Recenzija> recenzije;

   private java.util.List<Urednik> urednici;

   private IzvestajSvihZanrovaMenadzer izvestajSvihZanrova;// ovo ne treba da bude inicijalizovano, inic se u
                                                           // izvestajima kod admina
   private ArrayList<Zanr> sviZanrovi;// ali ovo mora biti inic !!! pre ovog gore
   private IzvestajSvihZanrova jedanZanr;
   private IzvestajSvihIzvodjacaMenadzer menIzvodjaca;// ovo ne treba biti inic, nego tek kad se pokrene izv
   private LoginMenadzer loginMenadzer;
   private Korisnik trenutniKorisnik;

   private static Sesija trenutnaSesija;
   

   

   /** @pdOid 2750728b-3647-44d9-803c-9a8cbcd00047 */
   public void odjava() {
      // TODO: implement
   }

   /**
    * @return the urednici
    */
   public java.util.List<Urednik> getUrednici() {
      return urednici;
   }

   /**
    * @param urednici the urednici to set
    */
   public void setUrednici(java.util.List<Urednik> urednici) {
      this.urednici = urednici;
   }
   
   public ArrayList<Zanr> getZanrovi() {
	   return this.sviZanrovi;
   }
   
   public void setZanrovi(ArrayList<Zanr> zanrovi) {
	   this.sviZanrovi = zanrovi;
   }

   /**
    * @return the trenutniKorisnik
    */
   public Korisnik getTrenutniKorisnik() {
      return trenutniKorisnik;
   }

   /**
    * @param trenutniKorisnik the trenutniKorisnik to set
    */
   public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
      this.trenutniKorisnik = trenutniKorisnik;
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

   /** @pdGenerated default getter */
   public java.util.Collection<MuzickoDelo> getDela() {
      if (dela == null)
         dela = new java.util.HashSet<MuzickoDelo>();
      return dela;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator<MuzickoDelo> getIteratorDela() {
      if (dela == null)
         dela = new java.util.HashSet<MuzickoDelo>();
      return dela.iterator();
   }

   /**
    * @pdGenerated default setter
    * @param newDela
    */
   public void setDela(java.util.Collection<MuzickoDelo> newDela) {
      removeAllDela();
      for (java.util.Iterator<MuzickoDelo> iter = newDela.iterator(); iter.hasNext();)
         addDela((MuzickoDelo) iter.next());
   }

   /**
    * @pdGenerated default add
    * @param newMuzickoDjelo
    */
   public void addDela(MuzickoDelo newMuzickoDjelo) {
      if (newMuzickoDjelo == null)
         return;
      if (this.dela == null)
         this.dela = new java.util.HashSet<MuzickoDelo>();
      if (!this.dela.contains(newMuzickoDjelo))
         this.dela.add(newMuzickoDjelo);
   }

   /**
    * @pdGenerated default remove
    * @param oldMuzickoDjelo
    */
   public void removeDela(MuzickoDelo oldMuzickoDjelo) {
      if (oldMuzickoDjelo == null)
         return;
      if (this.dela != null)
         if (this.dela.contains(oldMuzickoDjelo))
            this.dela.remove(oldMuzickoDjelo);
   }

   /** @pdGenerated default removeAll */
   public void removeAllDela() {
      if (dela != null)
         dela.clear();
   }

   /** @pdGenerated default getter */
   public java.util.Collection<Grupa> getGrupe() {
      if (grupe == null)
         grupe = new java.util.HashSet<Grupa>();
      return grupe;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Grupa> getIteratorGrupe() {
      if (grupe == null)
         grupe = new java.util.HashSet<Grupa>();
      return grupe.iterator();
   }

   /**
    * @pdGenerated default setter
    * @param newGrupe
    */
   public void setGrupe(java.util.Collection<Grupa> newGrupe) {
      removeAllGrupe();
      for (java.util.Iterator<Grupa> iter = newGrupe.iterator(); iter.hasNext();)
         addGrupe((Grupa) iter.next());
   }

   /**
    * @pdGenerated default add
    * @param newGrupa
    */
   public boolean addGrupe(Grupa newGrupa) {
	  if (newGrupa == null)
		  return false;
      if (this.grupe == null)
         this.grupe = new java.util.HashSet<Grupa>();
      for (Grupa g : grupe)
      {
    	  if (g.getUmetnickoIme().equals(newGrupa.getUmetnickoIme()))
    		  return false;
      }
      this.grupe.add(newGrupa);
      return true;
   }

   /**
    * @pdGenerated default remove
    * @param oldGrupa
    */
   public void removeGrupe(Grupa oldGrupa) {
      if (oldGrupa == null)
         return;
      if (this.grupe != null)
         if (this.grupe.contains(oldGrupa))
            this.grupe.remove(oldGrupa);
   }

   /** @pdGenerated default removeAll */
   public void removeAllGrupe() {
      if (grupe != null)
         grupe.clear();
   }

   /** @pdGenerated default getter */
   public java.util.Collection<Pojedinacanizvodjac> getUmetnici() {
      if (umetnici == null)
         umetnici = new java.util.HashSet<Pojedinacanizvodjac>();
      return umetnici;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Pojedinacanizvodjac> getIteratorUmetnici() {
      if (umetnici == null)
         umetnici = new java.util.HashSet<Pojedinacanizvodjac>();
      return umetnici.iterator();
   }

   /**
    * @pdGenerated default setter
    * @param newUmetnici
    */
   public void setUmetnici(java.util.Collection<Pojedinacanizvodjac> newUmetnici) {
      removeAllUmetnici();
      for (java.util.Iterator<Pojedinacanizvodjac> iter = newUmetnici.iterator(); iter.hasNext();)
         addUmetnici((Pojedinacanizvodjac) iter.next());
   }

   /**
    * @pdGenerated default add
    * @param newPojedinacanizvodjac
    */
   public boolean addUmetnici(Pojedinacanizvodjac newPojedinacanizvodjac) {
	  if (newPojedinacanizvodjac == null)
		  return false;
      if (this.umetnici == null)
         this.umetnici = new java.util.HashSet<Pojedinacanizvodjac>();
      for (Pojedinacanizvodjac pi : umetnici)
      {
    	  if (pi.getUmetnickoIme().equals(newPojedinacanizvodjac.getUmetnickoIme()))
    		  return false;
      }
      this.umetnici.add(newPojedinacanizvodjac);
      return true;
   }

   /**
    * @pdGenerated default remove
    * @param oldPojedinacanizvodjac
    */
   public void removeUmetnici(Pojedinacanizvodjac oldPojedinacanizvodjac) {
      if (oldPojedinacanizvodjac == null)
         return;
      if (this.umetnici != null)
         if (this.umetnici.contains(oldPojedinacanizvodjac))
            this.umetnici.remove(oldPojedinacanizvodjac);
   }

   /** @pdGenerated default removeAll */
   public void removeAllUmetnici() {
      if (umetnici != null)
         umetnici.clear();
   }

   /** @pdGenerated default getter */
   public java.util.Collection<Recenzija> getRecenzije() {
      if (recenzije == null)
         recenzije = new java.util.HashSet<Recenzija>();
      return recenzije;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Recenzija> getIteratorRecenzije() {
      if (recenzije == null)
         recenzije = new java.util.HashSet<Recenzija>();
      return recenzije.iterator();
   }

   /**
    * @pdGenerated default setter
    * @param newRecenzije
    */
   public void setRecenzije(java.util.Collection<Recenzija> newRecenzije) {
      removeAllRecenzije();
      for (java.util.Iterator<Recenzija> iter = newRecenzije.iterator(); iter.hasNext();)
         addRecenzije((Recenzija) iter.next());
   }

   /**
    * @pdGenerated default add
    * @param newRecenzija
    */
   public void addRecenzije(Recenzija newRecenzija) {
      if (newRecenzija == null)
         return;
      if (this.recenzije == null)
         this.recenzije = new java.util.HashSet<Recenzija>();
      if (!this.recenzije.contains(newRecenzija))
         this.recenzije.add(newRecenzija);
   }

   /**
    * @pdGenerated default remove
    * @param oldRecenzija
    */
   public void removeRecenzije(Recenzija oldRecenzija) {
      if (oldRecenzija == null)
         return;
      if (this.recenzije != null)
         if (this.recenzije.contains(oldRecenzija))
            this.recenzije.remove(oldRecenzija);
   }

   /** @pdGenerated default removeAll */
   public void removeAllRecenzije() {
      if (recenzije != null)
         recenzije.clear();
   }

   public static Sesija namestiSesiju(Korisnik korisnik, CitacDatoteka datoteke, LoginMenadzer menadzer) {
      // TODO Auto-generated method stub
      if (trenutnaSesija != null) {
         trenutnaSesija.setTrenutniKorisnik(korisnik);
         return trenutnaSesija;
      } else {
         trenutnaSesija = new Sesija(korisnik, datoteke.getKorisnici(), datoteke.getIzvodjaci(), datoteke.getMuzickaDela(), datoteke.getGrupe(),
               datoteke.getIzvodjaci().getSolo(), datoteke.getRecenzije(), menadzer);
         return trenutnaSesija;
      }
   }

   private Sesija(Korisnik trenutniKorisnik, KorisniciMenadzer korisnici, IzvodjacMenadzer izvodjacMenadzer, Collection<MuzickoDelo> dela,
         Collection<Grupa> grupe, Collection<Pojedinacanizvodjac> umetnici, Collection<Recenzija> recenzije,
         LoginMenadzer loginMenadzer) {
      super();
      this.setKorisnici(korisnici);
      this.setIzvodjacMenadzer(izvodjacMenadzer);
      this.dela = dela;
      this.grupe = grupe;
      this.umetnici = umetnici;
      this.recenzije = recenzije;
      this.setTrenutniKorisnik(trenutniKorisnik);
	this.loginMenadzer = loginMenadzer;
}


public void izvrsi() {
	// TODO Auto-generated method stub
	
	
	KorisnikAplikacijeHomepage homepage = new KorisnikAplikacijeHomepage(this);
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
	   this.izvestajSvihZanrova=new IzvestajSvihZanrovaMenadzer(dela, recenzije, sviZanrovi);
	   
	return izvestajSvihZanrova;
	   
   }
   public IzvestajSvihZanrova pronadjiPodatkejednogZanra(String naziv) {
	   jedanZanr=new IzvestajSvihZanrova(naziv);
	   pronadiDela(naziv);
	   return jedanZanr;
   }


private void pronadiDela(String naziv) {
	for(MuzickoDelo d:dela) {
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
	   for(Grupa g:this.grupe) { sviizv.add(g);}
	   for(Pojedinacanizvodjac p:this.umetnici) {sviizv.add(p);}
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
		jedan.setOcenaKorisnika(ocenaKo/i.muzickaDela.size());
		jedan.setOcenaUrednika(ocenaUr/i.muzickaDela.size());
	   return jedan;
	   
   }
   public String[] izvadiImenaIzvodjaca() {
	   String[] imena= new String[this.grupe.size()+this.umetnici.size()];
	   int j=0;
	   for(Grupa i:this.grupe) {
		   imena[j]=i.getUmetnickoIme();
		   j++;
	   }
	   for(Pojedinacanizvodjac p:this.umetnici) {
		   imena[j]=p.getUmetnickoIme();
		   j++;
	   }
	   return imena;
	   
	   
   }
public String[] izvadiImenaDela(String i) {
	for(Izvodjac iz:this.getGrupe()) {
		if(iz.getUmetnickoIme().equals(i)) {return iz.getImenaDela();}
	}
	for(Izvodjac iz:this.getUmetnici()) {
		if(iz.getUmetnickoIme().equals(i)) {return iz.getImenaDela();}
	}
	String[] s= {""};
	return s;
	
}


public Izvodjac getIzvodjac(String i) {
	for(Izvodjac iz:this.getGrupe()) {
		if(iz.getUmetnickoIme().equals(i)) {return iz;}
	}
	for(Izvodjac iz:this.getUmetnici()) {
		if(iz.getUmetnickoIme().equals(i)) {return iz;}
	}
	return null;
}


public boolean napraviDelo(String datumIzdavanja, String naslov, String opis, Izvodjac izv, ArrayList<Zanr> zanrovi) {
	try {
	DateTimeFormatter form=DateTimeFormatter.ofPattern("dd.mm.yyyy.");
	LocalDate datum=LocalDate.parse(datumIzdavanja, form);
	Date dan=new Date(datum.getYear(), datum.getMonthValue(), datum.getDayOfMonth());
	MuzickoDelo md=new MuzickoDelo(naslov, opis, dan, true, zanrovi);
	izv.getMuzickaDela().add(md);
	this.getDela().add(md);
	return true;
	}catch(DateTimeException e) {
		return false;
	}
}

public IzvestajSvihZanrovaMenadzer getIzvestajSvihZanrova() {
	return this.izvestajSvihZanrova;
}


}