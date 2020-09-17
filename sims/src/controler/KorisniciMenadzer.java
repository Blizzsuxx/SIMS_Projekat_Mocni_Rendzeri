/***********************************************************************
 * Module:  KorisniciMenadzer.java
 * Author:  Dragan
 * Purpose: Defines the Class KorisniciMenadzer
 ***********************************************************************/
package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Pol;
import model.Sesija;
import model.Urednik;
import view.Slikovit;
import view.TableModelWrapper;


/** @pdOid 121daa1d-b073-437c-95b7-7f061b5ef5df */
public class KorisniciMenadzer {
   /** @pdRoleInfo migr=no name=Korisnik assc=association25 mult=1 type=Aggregation */
   private HashMap<String, Korisnik> korisnici;
   
   public KorisniciMenadzer() {
	   this.korisnici = new HashMap<String, Korisnik>();
   }
   
	public KorisniciMenadzer(List<String[]> readAll) {
	// TODO Auto-generated constructor stub
	   korisnici = new HashMap<String, Korisnik>();
	   SimpleDateFormat format = Constants.FORMAT_ZA_DATUM;
	   for(String[] s : readAll) {
		   String ime = s[0].trim();
		   String prezime = s[1].trim();
		   String eMail = s[2].trim();
		   Pol pol = Pol.valueOf(s[3].trim());
		   Date datumRodjenja = null;
		
			try {
				datumRodjenja = format.parse(s[4].trim());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		   String sifra = s[5].trim();
		   String korisnickoIme = s[6].trim();
		   Date datum = null;
		try {
			
			datum = format.parse(s[7].trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   boolean status = Boolean.parseBoolean(s[8].trim());
		   String uloga = s[9].trim();
		   switch(uloga) {
		   case "a":
			   dodaj(new Administrator(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
			   break;
			case "k":
				dodaj(new KorisnikAplikacije(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
				break;
			case "u":
				dodaj(new Urednik(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
				break;
		   }
	   }
	}
   
   public HashMap<String, Korisnik> getKorisnici()
   {
	   return this.korisnici;
   }
   
   public void setKorisnici(HashMap<String, Korisnik> korisnici)
   {
	   this.korisnici = korisnici;
   }
   
   public TableModelWrapper getTabelaKorisnika()  throws Exception {
		String[] columns = { "Korisnicko ime" ,"Ime", "Prezime", "Datum rodjenja", "Status"};
		Class<?>[] columnTypes = { String.class, String.class, String.class, Date.class, Boolean.class};
		boolean[] editableColumns = { false, false, false, false, false};
		int[] columnWidths = { 120, 120, 100, 80, 80};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		Iterator<Entry<String, Korisnik>> it = korisnici.entrySet().iterator();
		while (it.hasNext()) 
		{
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			if (k.getNalog().getKorisnickoIme().equals(Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme()))
				continue;
			data.add(new Object[] {k.getNalog().getKorisnickoIme(), k.getIme(), k.getPrezime(), k.getDatumRodjenja(), k.isStatus()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
   
   public boolean provjeriKorisnickoIme(String korisnickoIme) {
	   return korisnici.containsKey(korisnickoIme);
   }
   
   /** @pdOid de9351bc-01ca-477d-962b-27f63d3d7a01 */
   public void dodaj(Korisnik k) {
      if (!provjeriKorisnickoIme(k.getNalog().getKorisnickoIme()))
    	  korisnici.put(k.getNalog().getKorisnickoIme(), k);
   }
   
 
   
   /** @pdOid 59cb0526-ada2-4d39-93a6-eaaf6a8f1733 */
   public Korisnik trazi(String korisnickoIme) {
      // TODO: implement
      return this.korisnici.get(korisnickoIme);
   }

   public void sacuvaj(){
	   PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"korisnici.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Korisnik a:korisnici.values()) {
				if(a instanceof KorisnikAplikacije) {
					pw.println(a.toFileString()+"k");
				}else if(a instanceof Administrator) {
					pw.println(a.toFileString()+"a");
				}
				else {
					pw.println(a.toFileString()+"u");
				}
				
				
			}pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
	  
   }
   
   public List<Korisnik> vratiUrednike(){
	   List<Korisnik> lista = new ArrayList<>();
	   for (Korisnik k : korisnici.values()) 
		   if (k.isStatus() && k instanceof Urednik)
			   lista.add((Urednik) k);
	   
	   return lista;
	   
   }
   
   public List<Korisnik> vratiObicneKorisnike(){
	   List<Korisnik> lista = new ArrayList<>();
	   for (Korisnik k : korisnici.values()) 
		   if (k.isStatus() && k instanceof KorisnikAplikacije)
			   lista.add(k);
	   
	   return lista;
   }

public List<Korisnik> vratiAdmine() {
	List<Korisnik> lista = new ArrayList<>();
	   for (Korisnik k : korisnici.values()) 
		   if (k.isStatus() && k instanceof Administrator)
			   lista.add(k);
	   
	   return lista;
}

public List<Korisnik> vratiSveAktivneKorisnike() {
	List<Korisnik> lista = new ArrayList<>();
	   for (Korisnik k : korisnici.values()) 
		   if (k.isStatus() )
			   lista.add(k);
	   
	   return lista;
}
public Collection<Urednik> traziZaSearch(String textZaSearch) {
	Collection<Urednik> rezultat = new ArrayList<>();
	for(Korisnik a : this.vratiUrednike()) {
		if(a.Ime().contains(textZaSearch)) {
			rezultat.add((Urednik) a);
		}
	}
	return rezultat;
}
public Korisnik nadji(String imeK) {
	for(Korisnik k:this.korisnici.values()) {
		//System.out.println(k.getNalog().getKorisnickoIme()+" korisnicko ime");
		//System.out.println(imeK+" trazeno");
		if(k.getNalog().getKorisnickoIme().equals(imeK)) {
			return k;
		}
	}
	return null;
}

}