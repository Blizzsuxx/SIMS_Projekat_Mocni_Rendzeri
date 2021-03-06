/***********************************************************************
 * Module:  Pojedinacanizvodjac.java
 * Author:  Dragan
 * Purpose: Defines the Class Pojedinacanizvodjac
 ***********************************************************************/
package model;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import controler.Constants;

/** @pdOid 2fc12510-acfe-4d29-b294-58047091b3ba */
public class Pojedinacanizvodjac extends Izvodjac {
	/** @pdOid d1866ad1-9677-405a-85a4-d344052641a8 */
	private String ime;
	/** @pdOid 4a269e8f-2f97-4a9c-a700-b07fdadf1080 */
	private String prezime;
	/** @pdOid 8e87bae9-79fb-491e-a442-fe17aa6f8258 */
	private Date datumRodjenja;
	/** @pdOid 704b9043-5813-4ed1-abee-ef065c908ce9 */
	private Date datumSmrti;
	/** @pdOid 998ec758-dd8f-4791-b49b-5db5d8a7a166 */
	private String opis;
	/** @pdOid 81c6af95-4f2f-4227-bfe0-84f4aa1d1f1d */
	private Pol pol;
	private ArrayList<Clan> clanstvaUGrupama;
   
   
	public ArrayList<Clan> getClanstvaUGrupama() {
		return clanstvaUGrupama;
	}
	public void setClanstvaUGrupama(ArrayList<Clan> clanstvaUGrupama) {
		this.clanstvaUGrupama = clanstvaUGrupama;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Date getDatumSmrti() {
		return datumSmrti;
	}
	public void setDatumSmrti(Date datumSmrti) {
		this.datumSmrti = datumSmrti;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
	public Pojedinacanizvodjac() {
		super();
	}
	
	public Pojedinacanizvodjac(boolean odobrenost, String umetnickoIme, ArrayList<Zanr> zanr, boolean status, LinkedList<KorisnikAplikacije> prati, String ime, String prezime,
			Date datumRodjenja, Date datumSmrti, String opis, Pol pol) {
		super(umetnickoIme, zanr, status, prati, odobrenost);
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.datumSmrti = datumSmrti;
		this.opis = opis;
		this.pol = pol;
		this.clanstvaUGrupama=new ArrayList<Clan>();
	}
	public Pojedinacanizvodjac(String umetnickoIme, ArrayList<Zanr> zanr, boolean status, LinkedList<KorisnikAplikacije> prati) {
		super(umetnickoIme, zanr, status, prati);
	}
	
	public Pojedinacanizvodjac(boolean odobrenost,String umetnickoIme, ArrayList<Zanr> zanr, boolean status, String ime, String prezime,
			Date datumRodjenja, Date datumSmrti, String opis, Pol pol) {
		super(umetnickoIme, zanr, status, odobrenost);
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.datumSmrti = datumSmrti;
		this.opis = opis;
		this.pol = pol;
		this.clanstvaUGrupama=new ArrayList<Clan>();
	}
	@Override
	protected String[] getImenaDela() {
		
		if(this.getMuzickaDela()==null) {String[] imena= {""};return imena;}
		String[] imena=new String[this.getMuzickaDela().size()];
		if(this.getMuzickaDela().size()==0) {String[] imenaa= {""};return imenaa;}
		int j=0;
		for(MuzickoDelo m:this.getMuzickaDela()) {
			imena[j]=m.getNaslov();
			j++;
		}
		return imena;
	}
	@SuppressWarnings("deprecation")
	@Override
	public String toFileString() {
		String ad="";
		ad+=this.isOdobrenost()+";";
		ad+=this.getUmetnickoIme()+";";
		ad+=Izvodjac.getNaizvZanrova(this.getZanr())+";";
		ad+=this.isStatus()+";";
		ad+=this.getIme()+";"+this.getPrezime()+";";
		ad+=this.getDatumRodjenja().getDay()+"."+this.getDatumRodjenja().getMonth()+"."+this.getDatumRodjenja().getYear()+".;";//
		if(this.getDatumSmrti()==null) {
			ad+="/;";
		}else {
			ad+=this.getDatumSmrti().getDay()+"."+this.getDatumSmrti().getMonth()+"."+this.getDatumSmrti().getYear()+".;";//		
		}
		ad+=this.getOpis()+";";
		ad+=this.getPol(); //mora biti malo? TODO
		
		return ad;
	}
	
	
	public static String PojedinacniIzvodjac2String(Pojedinacanizvodjac pi) {
		DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
		String datumSmrti = "/";
		if (pi.getDatumSmrti() != null)
			datumSmrti = df.format(pi.getDatumSmrti());
		return pi.isOdobrenost()+";"+pi.getUmetnickoIme() + ";"  + Izvodjac.getNaizvZanrova(pi.getZanr()) + ";" + pi.isStatus() + ";" + pi.getIme() + ";" +
				pi.getPrezime() + ";" + df.format(pi.getDatumRodjenja()) + ";" + datumSmrti +
				";" + pi.getOpis() + ";" + pi.getPol() + System.lineSeparator();
	}

	@Override
	public BufferedImage defaultSlika() {
		// TODO Auto-generated method stub
		return Constants.POJEDINACAN_IZVODJAC_IKONA;
	}
}