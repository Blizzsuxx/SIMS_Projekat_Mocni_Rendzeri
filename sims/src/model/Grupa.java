/***********************************************************************
 * Module:  Grupa.java
 * Author:  Dragan
 * Purpose: Defines the Class Grupa
 ***********************************************************************/
package model;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import controler.Constants;

/** @pdOid 38a4054e-e6d7-430f-83e2-c22dad90681d */
public class Grupa extends Izvodjac {
	/** @pdOid 4ad9e500-3e23-4910-a130-75d40fcd6374 */
	private int brojClanova;
	/** @pdOid adad38f8-8fac-43ba-85dd-eb9faf4ea934 */
	private Date datumOsnivanja;
	/** @pdOid e4429194-5897-4195-9b98-cdf8a48f2c45 */
	private Date datumRaspada;
	private ArrayList<Clan> clanovi;
   
	public ArrayList<Clan> getClanovi() {
		return clanovi;
	}
	public void setClanovi(ArrayList<Clan> clanovi) {
		this.clanovi = clanovi;
	}
	public int getBrojClanova() {
		return brojClanova;
	}
	public void setBrojClanova(int brojClanova) {
		this.brojClanova = brojClanova;
	}
	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}
	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}
	public Date getDatumRaspada() {
		return datumRaspada;
	}
	public void setDatumRaspada(Date datumRaspada) {
		this.datumRaspada = datumRaspada;
	}
	public Grupa(String umetnickoIme, ArrayList<Zanr>  zanr, boolean status, LinkedList<KorisnikAplikacije> prati, int brojClanova, Date datumOsnivanja,
			Date datumRaspada) {
		super(umetnickoIme, zanr, status, prati);
		this.brojClanova = brojClanova;
		this.datumOsnivanja = datumOsnivanja;
		this.datumRaspada = datumRaspada;
		this.clanovi=new ArrayList<>();
	}
	public Grupa(String umetnickoIme, ArrayList<Zanr>  zanr, boolean status, LinkedList<KorisnikAplikacije> prati) {
		super(umetnickoIme, zanr, status, prati);
		this.clanovi=new ArrayList<>();
	}
	
	
	public Grupa(boolean odobrenost, String umetnickoIme, ArrayList<Zanr>  zanr, boolean status,  int brojClanova, Date datumOsnivanja,
			Date datumRaspada) {
		super(umetnickoIme, zanr, status, odobrenost);
		this.brojClanova = brojClanova;
		this.datumOsnivanja = datumOsnivanja;
		this.datumRaspada = datumRaspada;
		this.clanovi=new ArrayList<>();
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
	@Override
	public String toFileString() {
		String ad="";
		ad+=this.isOdobrenost()+";";
		ad+=this.getUmetnickoIme()+";";
		ad+= Izvodjac.getNaizvZanrova(this.getZanr())+";";
		ad+=this.isStatus()+";";
		ad+=this.getBrojClanova()+";";
		DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
		ad+=df.format(this.getDatumOsnivanja())+";";//
		if(this.getDatumRaspada()==null) {
			ad+="/;";
		}else {
			ad+=df.format(this.getDatumRaspada())+";";//		
		}
		
		return ad;
	}
	

	public static String Grupa2String(Grupa g) {
		String pattern = "dd.MM.yyyy.";
		String datumRaspada = "/";
		DateFormat df = new SimpleDateFormat(pattern);
		if (g.getDatumRaspada() != null)
			datumRaspada = df.format(g.getDatumRaspada());
		return g.isOdobrenost()+";"+g.getUmetnickoIme() + ";" + Izvodjac.getNaizvZanrova(g.getZanr()) + ";" + g.isStatus() + ";" + g.getBrojClanova() + ";" +
				 df.format(g.getDatumOsnivanja()) + ";" + datumRaspada + System.lineSeparator();
	}
	
	@Override
	public BufferedImage defaultSlika() {
		// TODO Auto-generated method stub
		return Constants.GRUPA_IKONA;
	}
}