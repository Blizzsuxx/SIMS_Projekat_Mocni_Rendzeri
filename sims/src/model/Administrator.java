package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

import controler.Constants;

/** @pdOid 016eeff6-23c0-4cd3-b64b-7d02c2eef130 */
public class Administrator extends Korisnik {
   /** @pdRoleInfo migr=no name=ZakazanaRecenzija assc=association11 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<ZakazanaRecenzija> zakazaneRecenzije;
   /** @pdRoleInfo migr=no name=RecezijaZaIzmenu assc=association39 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   private java.util.Collection<RecezijaZaIzmenu> recenzijeZaIzmene;
   
   public Administrator() {
	   super();
   }
   
   public Administrator(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra,
			String korisnickoIme, Date datum, boolean status) {
      super(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status);
      zakazaneRecenzije = new ArrayList<>();
      recenzijeZaIzmene = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}



/** @pdOid dce443f0-0a05-4abd-8baa-cb9387af0879 */
   public void brisanjeNaloga() {
      // TODO: implement
   }
   
   /** @pdOid 67d16a7b-c498-4753-a9fb-63b2f74d7008 */
   public void registrovanjeIzvodjaca() {
      // TODO: implement
   }
   
   /** @pdOid c87f8aa7-0a8a-440e-8d14-d03a1dae5817 */
   public void registrovanjeAlbuma() {
      // TODO: implement
   }
   
   /** @pdOid 34411b33-76d4-4ff2-95df-7b1fa2389386 */
   public void dodeliZanr() {
      // TODO: implement
   }
   
   /** @pdOid 0685cf44-2089-4e7b-a05d-666ee9393362 */
   public void obrisiRecenziju() {
      // TODO: implement
   }
   
   /** @pdOid 4659641f-d599-4bfa-ab39-e27fd5bc2506 */
   public void odobriIzmenu() {
      // TODO: implement
   }
   
   /** @pdOid e83806f2-fea5-4304-b2ab-732c5efc640c */
   public void traziIzvestaj() {
      // TODO: implement
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
      if (!this.zakazaneRecenzije.contains(newZakazanaRecenzija))
         this.zakazaneRecenzije.add(newZakazanaRecenzija);
   }
   
   /** @pdGenerated default remove
     * @param oldZakazanaRecenzija */
   public void removeZakazaneRecenzije(ZakazanaRecenzija oldZakazanaRecenzija) {
      if (oldZakazanaRecenzija == null)
         return;
      if (this.zakazaneRecenzije != null)
         if (this.zakazaneRecenzije.contains(oldZakazanaRecenzija))
            this.zakazaneRecenzije.remove(oldZakazanaRecenzija);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllZakazaneRecenzije() {
      if (zakazaneRecenzije != null)
         zakazaneRecenzije.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RecezijaZaIzmenu> getRecenzijeZaIzmene() {
      if (recenzijeZaIzmene == null)
         recenzijeZaIzmene = new java.util.HashSet<RecezijaZaIzmenu>();
      return recenzijeZaIzmene;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<RecezijaZaIzmenu> getIteratorRecenzijeZaIzmene() {
      if (recenzijeZaIzmene == null)
         recenzijeZaIzmene = new java.util.HashSet<RecezijaZaIzmenu>();
      return recenzijeZaIzmene.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRecenzijeZaIzmene */
   public void setRecenzijeZaIzmene(java.util.Collection<RecezijaZaIzmenu> newRecenzijeZaIzmene) {
      removeAllRecenzijeZaIzmene();
      for (java.util.Iterator<RecezijaZaIzmenu> iter = newRecenzijeZaIzmene.iterator(); iter.hasNext();)
         addRecenzijeZaIzmene((RecezijaZaIzmenu)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRecezijaZaIzmenu */
   public void addRecenzijeZaIzmene(RecezijaZaIzmenu newRecezijaZaIzmenu) {
      if (newRecezijaZaIzmenu == null)
         return;
      if (this.recenzijeZaIzmene == null)
         this.recenzijeZaIzmene = new java.util.HashSet<RecezijaZaIzmenu>();
      if (!this.recenzijeZaIzmene.contains(newRecezijaZaIzmenu))
         this.recenzijeZaIzmene.add(newRecezijaZaIzmenu);
   }
   
   /** @pdGenerated default remove
     * @param oldRecezijaZaIzmenu */
   public void removeRecenzijeZaIzmene(RecezijaZaIzmenu oldRecezijaZaIzmenu) {
      if (oldRecezijaZaIzmenu == null)
         return;
      if (this.recenzijeZaIzmene != null)
         if (this.recenzijeZaIzmene.contains(oldRecezijaZaIzmenu))
            this.recenzijeZaIzmene.remove(oldRecezijaZaIzmenu);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRecenzijeZaIzmene() {
      if (recenzijeZaIzmene != null)
         recenzijeZaIzmene.clear();
   }



@Override

public String pratiociUpis() {
	
	return "";}

public BufferedImage defaultSlika() {
	// TODO Auto-generated method stub
	return Constants.UREDNIK_IKONA;

}

   
}