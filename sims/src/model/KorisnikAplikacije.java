/***********************************************************************
 * Module:  KorisnikAplikacije.java
 * Author:  Dragan
 * Purpose: Defines the Class KorisnikAplikacije
 ***********************************************************************/
package model;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import controler.Constants;

/** @pdOid d00188b7-a297-4fca-8ad3-4a5e996aa205 */
public class KorisnikAplikacije extends FrontEndKorisnik {
   public KorisnikAplikacije(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra,
			String korisnickoIme, Date datum, boolean status) {
      super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status);
      pratite = new ArrayList<>();
      onajKogaPrati = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

public KorisnikAplikacije() {
	// TODO Auto-generated constructor stub
}

/** @pdRoleInfo migr=no name=FrontEndKorisnik assc=pracenjeKorisnika coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<FrontEndKorisnik> pratite;
   /** @pdRoleInfo migr=no name=Izvodjac assc=association16 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<Izvodjac> onajKogaPrati;
   



public KorisnikAplikacije(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, boolean status,
		String sifra, String korisnickoIme, Date datum, Collection<MuzickoDelo> muzickoDelo, Collection<Zanr> preferiraniZanrovi,
		Collection<KorisnikAplikacije> pratilac, Collection<FrontEndKorisnik> pratite,
		Collection<Izvodjac> onajKogaPrati) {
	   super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status, muzickoDelo, preferiraniZanrovi, pratilac);
	   this.pratite = pratite;
	   this.onajKogaPrati = onajKogaPrati;

   }




/** @pdOid 570b1cab-dcf3-4bfc-a491-be5540c78efd */
   public void komentarisi() {
      // TODO: implement
   }
   
   /** @pdOid 979144ce-362b-4f8e-86d4-21c79429d6c5 */
   public void oceni() {
      // TODO: implement
   }
   
   /** @pdOid 59bd3470-4656-4519-a7c3-8be26ef3fee5 */
   public void zaprati() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<FrontEndKorisnik> getPratite() {
      if (pratite == null)
         pratite = new java.util.HashSet<FrontEndKorisnik>();
      return pratite;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<FrontEndKorisnik> getIteratorPratite() {
      if (pratite == null)
         pratite = new java.util.HashSet<FrontEndKorisnik>();
      return pratite.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPratite */
   public void setPratite(java.util.Collection<FrontEndKorisnik> newPratite) {
      removeAllPratite();
      for (java.util.Iterator<FrontEndKorisnik> iter = newPratite.iterator(); iter.hasNext();)
         addPratite((FrontEndKorisnik)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFrontEndKorisnik */
   public void addPratite(FrontEndKorisnik newFrontEndKorisnik) {
      if (newFrontEndKorisnik == null)
         return;
      if (this.pratite == null)
         this.pratite = new java.util.HashSet<FrontEndKorisnik>();
      if (!this.pratite.contains(newFrontEndKorisnik))
         this.pratite.add(newFrontEndKorisnik);
   }
   
   /** @pdGenerated default remove
     * @param oldFrontEndKorisnik */
   public void removePratite(FrontEndKorisnik oldFrontEndKorisnik) {
      if (oldFrontEndKorisnik == null)
         return;
      if (this.pratite != null)
         if (this.pratite.contains(oldFrontEndKorisnik))
            this.pratite.remove(oldFrontEndKorisnik);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPratite() {
      if (pratite != null)
         pratite.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Izvodjac> getOnajKogaPrati() {
      if (onajKogaPrati == null)
         onajKogaPrati = new java.util.HashSet<Izvodjac>();
      return onajKogaPrati;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Izvodjac> getIteratorOnajKogaPrati() {
      if (onajKogaPrati == null)
         onajKogaPrati = new java.util.HashSet<Izvodjac>();
      return onajKogaPrati.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOnajKogaPrati */
   public void setOnajKogaPrati(java.util.Collection<Izvodjac> newOnajKogaPrati) {
      removeAllOnajKogaPrati();
      for (java.util.Iterator<Izvodjac>  iter = newOnajKogaPrati.iterator(); iter.hasNext();)
         addOnajKogaPrati((Izvodjac)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newIzvodjac */
   public void addOnajKogaPrati(Izvodjac newIzvodjac) {
      if (newIzvodjac == null)
         return;
      if (this.onajKogaPrati == null)
         this.onajKogaPrati = new java.util.HashSet<Izvodjac>();
      if (!this.onajKogaPrati.contains(newIzvodjac))
         this.onajKogaPrati.add(newIzvodjac);
   }
   
   /** @pdGenerated default remove
     * @param oldIzvodjac */
   public void removeOnajKogaPrati(Izvodjac oldIzvodjac) {
      if (oldIzvodjac == null)
         return;
      if (this.onajKogaPrati != null)
         if (this.onajKogaPrati.contains(oldIzvodjac))
            this.onajKogaPrati.remove(oldIzvodjac);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOnajKogaPrati() {
      if (onajKogaPrati != null)
         onajKogaPrati.clear();
   }
   @Override
	public String pratiociUpis() {
	   String ad=super.pratiociUpis();
	   int i=0;
	   ad+="/";
	   for(FrontEndKorisnik f:pratite) {
		   if(i!=0) {
			   ad+=";";
		   }
		   if(f!=null) {
		   ad+=f.getNalog().getKorisnickoIme();i++;}
	   }
	   ad+="/";
	   i=0;
	   for(Izvodjac f:onajKogaPrati) {
		   if(i!=0) {
			   ad+=";";
		   }
		   if(f!=null) {
		   ad+=f.getUmetnickoIme();i++;}
	   }
	   return ad;
 }

@Override
public BufferedImage defaultSlika() {
	// TODO Auto-generated method stub
	return Constants.KORISNICKA_IKONA;
}

}