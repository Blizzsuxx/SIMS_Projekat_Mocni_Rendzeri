/***********************************************************************
 * Module:  Sesija.java
 * Author:  Dragan
 * Purpose: Defines the Class Sesija
 ***********************************************************************/
package model;
import java.util.ArrayList;
import java.util.Collection;

import controler.CitacDatoteka;
import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.IzvestajSvihZanrovaMenadzer;
import controler.KorisniciMenadzer;

/** @pdOid a6536d8d-e436-4d30-9c5d-e31219285ea3 */
public class Sesija {
   /** @pdRoleInfo migr=no name=KorisniciMenadzer assc=association29 mult=1..1 */
   private KorisniciMenadzer korisnici;
   /** @pdRoleInfo migr=no name=MuzickoDjelo assc=association38 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<MuzickoDjelo> dela;
   /** @pdRoleInfo migr=no name=Grupa assc=association42 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<Grupa> grupe;
   /** @pdRoleInfo migr=no name=Pojedinacanizvodjac assc=association43 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<Pojedinacanizvodjac> umetnici;
   /** @pdRoleInfo migr=no name=Recenzija assc=association44 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<Recenzija> recenzije;
   
 public java.util.Collection<Urednik> urednici;
   
   public IzvestajSvihZanrovaMenadzer izvestajSvihZanrovaa;//ovo ne treba da bude inicijalizovano, inic se u izvestajima kod admina
   public ArrayList<Zanr> sviZanrovi;//ali ovo mora biti inic !!! pre ovog gore
   public IzvestajSvihZanrova jedanZanr;
   public IzvestajSvihIzvodjacaMenadzer menIzvodjaca;//ovo ne treba biti inic, nego tek kad se pokrene izv
   
   private Korisnik trenutniKorisnik;
   
   private static Sesija trenutnaSesija;
   
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


public static Sesija namestiSesiju(Korisnik korisnik, CitacDatoteka datoteke) {
	// TODO Auto-generated method stub
	if(trenutnaSesija != null) {
		return trenutnaSesija;
	} else {
		trenutnaSesija = new Sesija(korisnik, datoteke.getKorisnici(), datoteke.getMuzickaDela(), datoteke.getGrupe(), datoteke.getIzvodjaci(), datoteke.getRecenzije());
		return trenutnaSesija;
	}
}

private Sesija(Korisnik trenutniKorisnik, KorisniciMenadzer korisnici, Collection<MuzickoDjelo> dela, Collection<Grupa> grupe,
		Collection<Pojedinacanizvodjac> umetnici, Collection<Recenzija> recenzije) {
	super();
	this.korisnici = korisnici;
	this.dela = dela;
	this.grupe = grupe;
	this.umetnici = umetnici;
	this.recenzije = recenzije;
	this.trenutniKorisnik = trenutniKorisnik;
}


public boolean izvrsi() {
	// TODO Auto-generated method stub
	return false;
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

}