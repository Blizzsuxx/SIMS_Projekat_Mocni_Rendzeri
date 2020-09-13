/***********************************************************************
 * Module:  Urednik.java
 * Author:  Dragan
 * Purpose: Defines the Class Urednik
 ***********************************************************************/
package model;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controler.Constants;

/** @pdOid d7b7dd3e-6a5f-4c47-ad28-32c0171650d0 */
public class Urednik extends FrontEndKorisnik implements DeljivPoZanrovima{
   /** @pdRoleInfo migr=no name=Recenzija assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Recenzija> istorijaRecenzija;
   /** @pdRoleInfo migr=no name=ZakazanaRecenzija assc=association10 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<ZakazanaRecenzija> zakazaneRecenzije;
   /** @pdRoleInfo migr=no name=RecezijaZaIzmenu assc=association41 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<RecezijaZaIzmenu> recezijaZaIzmenu;
   
   
   private List<Zanr> zanrovi;
   
   public Urednik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra,
			String korisnickoIme, Date datum, boolean status) {
		super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status);
		   this.istorijaRecenzija = new ArrayList<>();
		   this.zakazaneRecenzije = new ArrayList<>();
		   this.recezijaZaIzmenu = new ArrayList<>();
		   this.zanrovi = new ArrayList<>();
	}

   public Urednik() {
	   this.istorijaRecenzija = new ArrayList<>();
	   this.zakazaneRecenzije = new ArrayList<>();
	   this.recezijaZaIzmenu = new ArrayList<>();
	   this.zanrovi = new ArrayList<>();
   }

/** @pdOid f9c63904-e42a-4710-8822-3e3f1e173c5e */
   public void pokreniGlasanje() {
      // TODO: implement
   }
   
   /** @pdOid fe820a8c-9476-42ec-94d4-036123592bda */
   public void komentarisi() {
      // TODO: implement
   }
   
   /** @pdOid 5c48f2d8-0ee6-4ba4-8b51-131ade50e7b3 */
   public void oceni() {
      // TODO: implement
   }
   
   /** @pdOid d3573c55-ec9a-4629-942a-e1f17b31764e */
   public void zahtevajRegistraciju() {
      // TODO: implement
   }
   
   /** @pdOid 3ca42b2b-5a0f-49d6-9bbb-0f3711619d56 */
   public void praviTopListu() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Recenzija> getIstorijaRecenzija() {
      if (istorijaRecenzija == null)
         istorijaRecenzija = new java.util.HashSet<Recenzija>();
      return istorijaRecenzija;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Recenzija> getIteratorIstorijaRecenzija() {
      if (istorijaRecenzija == null)
         istorijaRecenzija = new java.util.HashSet<Recenzija>();
      return istorijaRecenzija.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newIstorijaRecenzija */
   public void setIstorijaRecenzija(java.util.Collection<Recenzija> newIstorijaRecenzija) {
      removeAllIstorijaRecenzija();
      for (java.util.Iterator<Recenzija> iter = newIstorijaRecenzija.iterator(); iter.hasNext();)
         addIstorijaRecenzija((Recenzija)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRecenzija */
   public void addIstorijaRecenzija(Recenzija newRecenzija) {
      if (newRecenzija == null)
         return;
      if (this.istorijaRecenzija == null)
         this.istorijaRecenzija = new java.util.HashSet<Recenzija>();
      for (Recenzija r : istorijaRecenzija) {
    	  if (r.getNaslov().equals(newRecenzija.getNaslov())) {
    		  return;
    	  }
      }
      this.istorijaRecenzija.add(newRecenzija);
      newRecenzija.setUrednik(this);      
   }
   
   /** @pdGenerated default remove
     * @param oldRecenzija */
   public void removeIstorijaRecenzija(Recenzija oldRecenzija) {
      if (oldRecenzija == null)
         return;
      if (this.istorijaRecenzija != null)
         if (this.istorijaRecenzija.contains(oldRecenzija))
         {
            this.istorijaRecenzija.remove(oldRecenzija);
            oldRecenzija.setUrednik((Urednik)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIstorijaRecenzija() {
      if (istorijaRecenzija != null)
      {
         Recenzija oldRecenzija;
         for (java.util.Iterator<Recenzija> iter = getIteratorIstorijaRecenzija(); iter.hasNext();)
         {
            oldRecenzija = (Recenzija)iter.next();
            iter.remove();
            oldRecenzija.setUrednik((Urednik)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<ZakazanaRecenzija> getZakazaneRecenzije() {
      if (zakazaneRecenzije == null)
         zakazaneRecenzije = new java.util.HashSet<ZakazanaRecenzija>();
      return zakazaneRecenzije;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<ZakazanaRecenzija> getIteratorZakazaneRecenzije() {
      if (zakazaneRecenzije == null)
         zakazaneRecenzije = new java.util.HashSet<ZakazanaRecenzija>();
      return zakazaneRecenzije.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newZakazaneRecenzije */
   public void setZakazaneRecenzije(java.util.Collection<ZakazanaRecenzija> newZakazaneRecenzije) {
      removeAllZakazaneRecenzije();
      for (java.util.Iterator<ZakazanaRecenzija> iter = newZakazaneRecenzije.iterator(); iter.hasNext();)
         addZakazaneRecenzije((ZakazanaRecenzija)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newZakazanaRecenzija */
   public void addZakazaneRecenzije(ZakazanaRecenzija newZakazanaRecenzija) {
      if (newZakazanaRecenzija == null)
         return;
      if (this.zakazaneRecenzije == null)
         this.zakazaneRecenzije = new java.util.HashSet<ZakazanaRecenzija>();
      for (ZakazanaRecenzija zr : zakazaneRecenzije) {
    	  if (zr.getRecenzija().getNaslov().equals(newZakazanaRecenzija.getRecenzija().getNaslov())) {
    		  return;
    	  }
      }
      this.zakazaneRecenzije.add(newZakazanaRecenzija);
      newZakazanaRecenzija.setUrednik(this);      
   }
   
   /** @pdGenerated default remove
     * @param oldZakazanaRecenzija */
   public void removeZakazaneRecenzije(ZakazanaRecenzija oldZakazanaRecenzija) {
      if (oldZakazanaRecenzija == null)
         return;
      if (this.zakazaneRecenzije != null)
         if (this.zakazaneRecenzije.contains(oldZakazanaRecenzija))
         {
            this.zakazaneRecenzije.remove(oldZakazanaRecenzija);
            oldZakazanaRecenzija.setUrednik((Urednik)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllZakazaneRecenzije() {
      if (zakazaneRecenzije != null)
      {
         ZakazanaRecenzija oldZakazanaRecenzija;
         for (java.util.Iterator<ZakazanaRecenzija> iter = getIteratorZakazaneRecenzije(); iter.hasNext();)
         {
            oldZakazanaRecenzija = (ZakazanaRecenzija)iter.next();
            iter.remove();
            oldZakazanaRecenzija.setUrednik((Urednik)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RecezijaZaIzmenu> getRecezijaZaIzmenu() {
      if (recezijaZaIzmenu == null)
         recezijaZaIzmenu = new java.util.HashSet<RecezijaZaIzmenu>();
      return recezijaZaIzmenu;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<RecezijaZaIzmenu> getIteratorRecezijaZaIzmenu() {
      if (recezijaZaIzmenu == null)
         recezijaZaIzmenu = new java.util.HashSet<RecezijaZaIzmenu>();
      return recezijaZaIzmenu.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRecezijaZaIzmenu */
   public void setRecezijaZaIzmenu(java.util.Collection<RecezijaZaIzmenu> newRecezijaZaIzmenu) {
      removeAllRecezijaZaIzmenu();
      for (java.util.Iterator<RecezijaZaIzmenu> iter = newRecezijaZaIzmenu.iterator(); iter.hasNext();)
         addRecezijaZaIzmenu((RecezijaZaIzmenu)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRecezijaZaIzmenu */
   public void addRecezijaZaIzmenu(RecezijaZaIzmenu newRecezijaZaIzmenu) {
      if (newRecezijaZaIzmenu == null)
         return;
      if (this.recezijaZaIzmenu == null)
         this.recezijaZaIzmenu = new java.util.HashSet<RecezijaZaIzmenu>();
      for (RecezijaZaIzmenu rzi : recezijaZaIzmenu) {
    	  if (rzi.getRecenzija().getNaslov().equals(newRecezijaZaIzmenu.getRecenzija().getNaslov())) {
    		  return;
    	  }
      }
      this.recezijaZaIzmenu.add(newRecezijaZaIzmenu);
   }
   
   /** @pdGenerated default remove
     * @param oldRecezijaZaIzmenu */
   public void removeRecezijaZaIzmenu(RecezijaZaIzmenu oldRecezijaZaIzmenu) {
      if (oldRecezijaZaIzmenu == null)
         return;
      if (this.recezijaZaIzmenu != null)
         if (this.recezijaZaIzmenu.contains(oldRecezijaZaIzmenu))
            this.recezijaZaIzmenu.remove(oldRecezijaZaIzmenu);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRecezijaZaIzmenu() {
      if (recezijaZaIzmenu != null)
         recezijaZaIzmenu.clear();
   }
 
   
   public static String Urednik2String(Urednik urednik) {
	   DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
	   return urednik.getIme() + ";" + urednik.getPrezime() + ";" + urednik.geteMail() + ";" + urednik.getPol().name()
				+ ";" + df.format(urednik.getDatumRodjenja()) + ";" + urednik.getNalog().getSifra() + ";" + urednik.getNalog().getKorisnickoIme() +
				";" + df.format(urednik.getNalog().getDatumKreiranja()) + ";" + urednik.getNalog().isStatus() + System.lineSeparator();
	}
   
   	public List<Zanr> getZanrovi() {
		return zanrovi;
	}




	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}




@Override
public BufferedImage defaultSlika() {
	// TODO Auto-generated method stub
	return Constants.UREDNIK_IKONA;
}


   @Override
	public String pratiociUpis() {
	   String s=super.pratiociUpis();
	   return s;
 }

}