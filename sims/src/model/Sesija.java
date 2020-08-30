/***********************************************************************
 * Module:  Sesija.java
 * Author:  Dragan
 * Purpose: Defines the Class Sesija
 ***********************************************************************/
package model;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.ComboBoxModel;
import javax.swing.JTextField;

import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.IzvestajSvihZanrovaMenadzer;
import controler.KorisniciMenadzer;

/** @pdOid a6536d8d-e436-4d30-9c5d-e31219285ea3 */
public class Sesija {
   /** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association29 mult=1 */
   public KorisniciMenadzer korisnici;
   /** @pdRoleInfo migr=no name=MuzickoDjelo assc=association38 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<MuzickoDjelo> dela;
   /** @pdRoleInfo migr=no name=Grupa assc=association42 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Grupa> grupe;
   /** @pdRoleInfo migr=no name=Pojedinacanizvodjac assc=association43 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Pojedinacanizvodjac> umetnici;
   /** @pdRoleInfo migr=no name=Recenzija assc=association44 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Recenzija> recenzije;
   public java.util.Collection<Urednik> urednici;
   
   public IzvestajSvihZanrovaMenadzer izvestajSvihZanrovaa;//ovo ne treba da bude inicijalizovano, inic se u izvestajima kod admina
   public ArrayList<Zanr> sviZanrovi;//ali ovo mora biti inic !!! pre ovog gore
   public IzvestajSvihZanrova jedanZanr;
   public IzvestajSvihIzvodjacaMenadzer menIzvodjaca;//ovo ne treba biti inic, nego tek kad se pokrene izv
   
   
   
public IzvestajSvihZanrova getJedanZanr() {
	return jedanZanr;
}


public void setJedanZanr(IzvestajSvihZanrova jedanZanr) {
	this.jedanZanr = jedanZanr;
}


public IzvestajSvihIzvodjacaMenadzer getMenIzvodjaca() {
	return menIzvodjaca;
}


public void setMenIzvodjaca(IzvestajSvihIzvodjacaMenadzer menIzvodjaca) {
	this.menIzvodjaca = menIzvodjaca;
}


public IzvestajSvihZanrovaMenadzer getIzvestajSvihZanrovaa() {
	return izvestajSvihZanrovaa;
}


public void setIzvestajSvihZanrovaa(IzvestajSvihZanrovaMenadzer izvestajSvihZanrovaa) {
	this.izvestajSvihZanrovaa = izvestajSvihZanrovaa;
}


public ArrayList<Zanr> getSviZanrovi() {
	return sviZanrovi;
}


public void setSviZanrovi(ArrayList<Zanr> sviZanrovi) {
	this.sviZanrovi = sviZanrovi;
}


public KorisniciMenadzer getKorisnici() {
	return korisnici;
}


public void setKorisnici(KorisniciMenadzer korisnici) {
	this.korisnici = korisnici;
}


public java.util.Collection<Urednik> getUrednici() {
	return urednici;
}


public void setUrednici(java.util.Collection<Urednik> urednici) {
	this.urednici = urednici;
}


/** @pdOid 2750728b-3647-44d9-803c-9a8cbcd00047 */
   public void odjava() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<MuzickoDjelo> getDela() {
      if (dela == null)
         dela = new java.util.HashSet<MuzickoDjelo>();
      return dela;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDela() {
      if (dela == null)
         dela = new java.util.HashSet<MuzickoDjelo>();
      return dela.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDela */
   public void setDela(java.util.Collection<MuzickoDjelo> newDela) {
      removeAllDela();
      for (java.util.Iterator iter = newDela.iterator(); iter.hasNext();)
         addDela((MuzickoDjelo)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMuzickoDjelo */
   public void addDela(MuzickoDjelo newMuzickoDjelo) {
      if (newMuzickoDjelo == null)
         return;
      if (this.dela == null)
         this.dela = new java.util.HashSet<MuzickoDjelo>();
      if (!this.dela.contains(newMuzickoDjelo))
         this.dela.add(newMuzickoDjelo);
   }
   
   /** @pdGenerated default remove
     * @param oldMuzickoDjelo */
   public void removeDela(MuzickoDjelo oldMuzickoDjelo) {
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
   public java.util.Iterator getIteratorGrupe() {
      if (grupe == null)
         grupe = new java.util.HashSet<Grupa>();
      return grupe.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newGrupe */
   public void setGrupe(java.util.Collection<Grupa> newGrupe) {
      removeAllGrupe();
      for (java.util.Iterator iter = newGrupe.iterator(); iter.hasNext();)
         addGrupe((Grupa)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newGrupa */
   public void addGrupe(Grupa newGrupa) {
      if (newGrupa == null)
         return;
      if (this.grupe == null)
         this.grupe = new java.util.HashSet<Grupa>();
      if (!this.grupe.contains(newGrupa))
         this.grupe.add(newGrupa);
   }
   
   /** @pdGenerated default remove
     * @param oldGrupa */
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
   public java.util.Iterator getIteratorUmetnici() {
      if (umetnici == null)
         umetnici = new java.util.HashSet<Pojedinacanizvodjac>();
      return umetnici.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newUmetnici */
   public void setUmetnici(java.util.Collection<Pojedinacanizvodjac> newUmetnici) {
      removeAllUmetnici();
      for (java.util.Iterator iter = newUmetnici.iterator(); iter.hasNext();)
         addUmetnici((Pojedinacanizvodjac)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPojedinacanizvodjac */
   public void addUmetnici(Pojedinacanizvodjac newPojedinacanizvodjac) {
      if (newPojedinacanizvodjac == null)
         return;
      if (this.umetnici == null)
         this.umetnici = new java.util.HashSet<Pojedinacanizvodjac>();
      if (!this.umetnici.contains(newPojedinacanizvodjac))
         this.umetnici.add(newPojedinacanizvodjac);
   }
   
   /** @pdGenerated default remove
     * @param oldPojedinacanizvodjac */
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
   public java.util.Iterator getIteratorRecenzije() {
      if (recenzije == null)
         recenzije = new java.util.HashSet<Recenzija>();
      return recenzije.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRecenzije */
   public void setRecenzije(java.util.Collection<Recenzija> newRecenzije) {
      removeAllRecenzije();
      for (java.util.Iterator iter = newRecenzije.iterator(); iter.hasNext();)
         addRecenzije((Recenzija)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRecenzija */
   public void addRecenzije(Recenzija newRecenzija) {
      if (newRecenzija == null)
         return;
      if (this.recenzije == null)
         this.recenzije = new java.util.HashSet<Recenzija>();
      if (!this.recenzije.contains(newRecenzija))
         this.recenzije.add(newRecenzija);
   }
   
   /** @pdGenerated default remove
     * @param oldRecenzija */
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


   public IzvestajSvihZanrovaMenadzer namestiIzvestaj() {
	   this.izvestajSvihZanrovaa=new IzvestajSvihZanrovaMenadzer(dela, recenzije, sviZanrovi);
	   
	return izvestajSvihZanrovaa;
	   
   }
   public IzvestajSvihZanrova pronadjiPodatkejednogZanra(String naziv) {
	   jedanZanr=new IzvestajSvihZanrova(naziv);
	   pronadiDela(naziv);
	   return jedanZanr;
   }


private void pronadiDela(String naziv) {
	for(MuzickoDjelo d:dela) {
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
		for(MuzickoDjelo m:i.getMuzickaDela()) {
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
	MuzickoDjelo md=new MuzickoDjelo(naslov, opis, dan, true, zanrovi);
	izv.getMuzickaDela().add(md);
	this.getDela().add(md);
	return true;
	}catch(DateTimeException e) {
		return false;
	}
}


}