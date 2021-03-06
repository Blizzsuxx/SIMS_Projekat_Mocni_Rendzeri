/***********************************************************************
 * Module:  FrontEndKorisnik.java
 * Author:  Dragan
 * Purpose: Defines the Class FrontEndKorisnik
 ***********************************************************************/
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/** @pdOid a9c9f365-ca2f-446b-9e3e-06fdc25de877 */
public abstract class FrontEndKorisnik extends Korisnik {
	
	   /**
	    * @pdRoleInfo migr=no name=MuzickoDjelo assc=association32
	    *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	    */
	   private java.util.Collection<MuzickoDelo> istorija;
	   /**
	    * @pdRoleInfo migr=no name=Zanr assc=association36 coll=java.util.Collection
	    *             impl=java.util.HashSet mult=0..*
	    */
	   private java.util.Collection<Zanr> preferiraniZanrovi;
	   /**
	    * @pdRoleInfo migr=no name=KorisnikAplikacije assc=pracenjeKorisnika mult=0..*
	    *             side=A
	    */
	   private Collection<KorisnikAplikacije> pratilac;
	   
	   private Collection<Utisak> utisci;
	
	
	
	
   public FrontEndKorisnik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra,
         String korisnickoIme, Date datum, boolean status) {
      super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status);
      this.istorija = new ArrayList<>();
      this.pratilac = new ArrayList<>();
      this.preferiraniZanrovi = new ArrayList<>();
      this.utisci = new ArrayList<Utisak>();
   }




   public FrontEndKorisnik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra,
   String korisnickoIme, Date datum, boolean status, java.util.Collection<MuzickoDelo> muzickoDjelo, java.util.Collection<Zanr> preferiraniZanrovi, Collection<KorisnikAplikacije> pratilac) {
      super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status);
      this.istorija = muzickoDjelo;
      this.preferiraniZanrovi = preferiraniZanrovi;
      this.pratilac = pratilac;
      this.utisci = new ArrayList<Utisak>();
   }




   /**
    * @return the pratilac
    */
   public Collection<KorisnikAplikacije> getPratilac() {
      return pratilac;
   }

   /**
    * @param pratilac the pratilac to set
    */
   public void setPratilac(Collection<KorisnikAplikacije> pratilac) {
      this.pratilac = pratilac;
   }

   public FrontEndKorisnik() {
      this.istorija = new ArrayList<>();
      this.pratilac = new ArrayList<>();
      this.preferiraniZanrovi = new ArrayList<>();

   }

   

/** @pdOid 2764179e-3960-4723-809e-5c4cf97d9e27 */
   public void izbrisiNalog() {
      // TODO: implement
   }
   
   /** @pdOid 1a533454-c6d5-4902-bc59-11b991a4d481 */
   public void oceni() {
      // TODO: implement
   }
   
   /** @pdOid ab3d2968-fc86-43fb-a9b3-2a1f1e7ad4fe */
   public void komentarisi(Utisak utisak) {
      this.utisci.add(utisak);
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<MuzickoDelo> getIstorija() {
      if (istorija == null)
         istorija = new java.util.HashSet<MuzickoDelo>();
      return istorija;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<MuzickoDelo> getIteratorIstorija() {
      if (istorija == null)
         istorija = new java.util.HashSet<MuzickoDelo>();
      return istorija.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMuzickoDjelo */
   public void setIstorija(java.util.Collection<MuzickoDelo> newMuzickoDjelo) {
      removeAllIstorija();
      for (java.util.Iterator<MuzickoDelo> iter = newMuzickoDjelo.iterator(); iter.hasNext();)
         addIstorija((MuzickoDelo)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMuzickoDjelo */
   public void addIstorija(MuzickoDelo newMuzickoDjelo) {
      if (newMuzickoDjelo == null)
         return;
      if (this.istorija == null)
         this.istorija = new java.util.HashSet<MuzickoDelo>();
      if (!this.istorija.contains(newMuzickoDjelo))
         this.istorija.add(newMuzickoDjelo);
   }
   
   /** @pdGenerated default remove
     * @param oldMuzickoDjelo */
   public void removeIstorija(MuzickoDelo oldMuzickoDjelo) {
      if (oldMuzickoDjelo == null)
         return;
      if (this.istorija != null)
         if (this.istorija.contains(oldMuzickoDjelo))
            this.istorija.remove(oldMuzickoDjelo);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIstorija() {
      if (istorija != null)
         istorija.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Zanr> getPreferiraniZanrovi() {
      if (preferiraniZanrovi == null)
         preferiraniZanrovi = new java.util.HashSet<Zanr>();
      return preferiraniZanrovi;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Zanr> getIteratorPreferiraniZanrovi() {
      if (preferiraniZanrovi == null)
         preferiraniZanrovi = new java.util.HashSet<Zanr>();
      return preferiraniZanrovi.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPreferiraniZanrovi */
   public void setPreferiraniZanrovi(java.util.Collection<Zanr> newPreferiraniZanrovi) {
      removeAllPreferiraniZanrovi();
      for (java.util.Iterator<Zanr> iter = newPreferiraniZanrovi.iterator(); iter.hasNext();)
         addPreferiraniZanrovi((Zanr)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newZanr */
   public void addPreferiraniZanrovi(Zanr newZanr) {
      if (newZanr == null)
         return;
      if (this.preferiraniZanrovi == null)
         this.preferiraniZanrovi = new java.util.HashSet<Zanr>();
      if (!this.preferiraniZanrovi.contains(newZanr))
         this.preferiraniZanrovi.add(newZanr);
   }
   
   /** @pdGenerated default remove
     * @param oldZanr */
   public void removePreferiraniZanrovi(Zanr oldZanr) {
      if (oldZanr == null)
         return;
      if (this.preferiraniZanrovi != null)
         if (this.preferiraniZanrovi.contains(oldZanr))
            this.preferiraniZanrovi.remove(oldZanr);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPreferiraniZanrovi() {
      if (preferiraniZanrovi != null)
         preferiraniZanrovi.clear();
   }
   @Override
	public String pratiociUpis() {
	   String ad=this.getNalog().getKorisnickoIme()+"/";//zanrovi pratioci dela
	   int i=0;
	  
	   for(Zanr z:preferiraniZanrovi) {
		   if(i!=0) {
			   ad+=";";
		   }
		   i++;
		   if(z!=null) {
		   ad+=z.getNazivZanra();}
	   }
	   ad+="/";i=0;
	   for(KorisnikAplikacije k:pratilac) {
		   if(i!=0) {
			   ad+=";";
		   }
		   if(k!=null) {
		   ad+=k.getNalog().getKorisnickoIme();}
	   }
	   ad+="/";i=0;
	   for(MuzickoDelo d:istorija) {
		   if(i!=0) {
			   ad+=";";
		   }
		   if(d!=null) {
		   ad+=d.getNaslov();}
	   }
	   return ad;
   }




public Collection<Utisak> getUtisci() {
	return utisci;
}




public void setUtisci(Collection<Utisak> utisci) {
	this.utisci = utisci;
}

}