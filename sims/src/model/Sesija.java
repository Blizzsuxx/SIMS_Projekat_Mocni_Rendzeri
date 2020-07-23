/***********************************************************************
 * Module:  Sesija.java
 * Author:  Dragan
 * Purpose: Defines the Class Sesija
 ***********************************************************************/
package model;
import java.util.*;

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

}