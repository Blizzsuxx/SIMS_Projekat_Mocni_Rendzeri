
package model;

/** @pdOid d7d7bf7a-b03b-41f8-b51f-f0668c0cb763 */
public class Ocena {
   /** @pdOid 2d8fae1f-3339-40e8-8283-fb6163520797 */
   private float ocena;
   
   /** @pdRoleInfo migr=no name=FrontEndKorisnik assc=association30 mult=1 side=A */
   public FrontEndKorisnik ocenitelj;
   public MuzickoDelo delo;

public float getOcena() {
	return ocena;
}

public void setOcena(float ocena) {
	this.ocena = ocena;
}

public FrontEndKorisnik getOcenitelj() {
	return ocenitelj;
}

public void setOcenitelj(FrontEndKorisnik ocenitelj) {
	this.ocenitelj = ocenitelj;
}

public Ocena(float ocena, FrontEndKorisnik ocenitelj) {
	super();
	this.ocena = ocena;
	this.ocenitelj = ocenitelj;
}

public String toFileString() {
	//System.out.println(this.delo.getNaziv());
	String ad=this.getOcena()+","+this.delo.getNaslov()+","+this.getOcenitelj().getNalog().getKorisnickoIme();
	return ad;
}
   
   

}