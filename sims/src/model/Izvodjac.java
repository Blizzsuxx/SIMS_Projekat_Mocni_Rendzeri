/***********************************************************************
 * Module:  Izvodjac.java
 * Author:  Dragan
 * Purpose: Defines the Class Izvodjac
 ***********************************************************************/
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import view.Slikovit;

/** @pdOid 20a31e0c-cbdd-4a1e-956e-0c8ed03fce3d */
public abstract class Izvodjac implements Slikovit, DeljivPoZanrovima {
	/** @pdOid 515ac754-9ea8-45bb-bc6e-0b899ea103d8 */
	private String umetnickoIme;
	/** @pdOid a5f2bea1-d21a-43c6-99b6-58d368935d68 */
	private boolean status;

	private ArrayList<Zanr> zanrovi;
	
	// indikator da li je izvodjac odobren od strane admina
	private boolean odobrenost;
	/** @pdRoleInfo migr=no name=KorisnikAplikacije assc=association16 mult=0..* side=A */
	private LinkedList<KorisnikAplikacije> prati;
   
	private ArrayList<MuzickoDelo> muzickaDela;
	private ArrayList<Album> izdatiAlbumi;
   
	public ArrayList<Album> getIzdatiAlbumi() {
		return izdatiAlbumi;
	}
	
	public void setIzdatiAlbumi(ArrayList<Album> izdatiAlbumi) {
		this.izdatiAlbumi = izdatiAlbumi;
	}
	
	public void addIzdatAlbum(Album album) {
		for (Album a : izdatiAlbumi) {
			if (a.getNaslov().equals(album.getNaslov())) {
				return;
			}
		}
		izdatiAlbumi.add(album);
	}
	
	public ArrayList<MuzickoDelo> getMuzickaDela() {
		return muzickaDela;
	}
	
	public void setMuzickaDela(ArrayList<MuzickoDelo> muzickaDela) {
		this.muzickaDela = muzickaDela;
	}
	
	public String getUmetnickoIme() {
		return umetnickoIme;
	}
	
	public void setUmetnickoIme(String umetnickoIme) {
		this.umetnickoIme = umetnickoIme;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public LinkedList<KorisnikAplikacije> getPrati() {
		return prati;
	}
	
	public void setPrati(LinkedList<KorisnikAplikacije> prati) {
		this.prati = prati;
	}
	
	public ArrayList<Zanr>  getZanr() {
		return this.zanrovi;
	}
	
	public void setZanr(ArrayList<Zanr>  z) {
		this.zanrovi = z;
	}
	
	public Izvodjac(String umetnickoIme, ArrayList<Zanr>  zanr, boolean status, LinkedList<KorisnikAplikacije> prati) {
		super();
		this.umetnickoIme = umetnickoIme;
		this.zanrovi = zanr;
		this.status = status;
		if (prati == null)
	   	{
	   		this.prati = new LinkedList<>();
	   	}
		this.muzickaDela = new ArrayList<MuzickoDelo>();
		this.izdatiAlbumi = new ArrayList<Album>();
	}
	
	public Izvodjac(String umetnickoIme, ArrayList<Zanr>  zanr, boolean status, LinkedList<KorisnikAplikacije> prati, boolean odobrenost) {
		super();
		this.umetnickoIme = umetnickoIme;
		this.zanrovi = zanr;
		this.status = status;
		if (prati == null)
	   	{
	   		this.prati = new LinkedList<>();
	   	}
		this.odobrenost = odobrenost;
		this.muzickaDela = new ArrayList<MuzickoDelo>();
		this.izdatiAlbumi = new ArrayList<Album>();
	}
	
	
	public Izvodjac() {
		super();
		this.muzickaDela = new ArrayList<MuzickoDelo>();
		this.izdatiAlbumi = new ArrayList<Album>();
		this.prati = new LinkedList<>();
	}
	
	public Izvodjac(String umetnickoIme2, ArrayList<Zanr>  zanr, boolean status2) {
		this.umetnickoIme = umetnickoIme2;
		this.zanrovi = zanr;
		this.status = status2;
		this.muzickaDela = new ArrayList<MuzickoDelo>();
		this.izdatiAlbumi = new ArrayList<Album>();
	}
	
	public Izvodjac(String umetnickoIme2, ArrayList<Zanr>  zanr, boolean status2, boolean odobrenost) {
		this.umetnickoIme = umetnickoIme2;
		this.zanrovi = zanr;
		this.status = status2;
		this.odobrenost = odobrenost;
		this.muzickaDela = new ArrayList<MuzickoDelo>();
		this.izdatiAlbumi = new ArrayList<Album>();
		this.prati = new LinkedList<>();
	}
	
	protected abstract String[] getImenaDela();
	
	public MuzickoDelo pronadiDelo(String br1) {
		if(this.muzickaDela==null) {return null;}
		for(MuzickoDelo md:this.muzickaDela) {
			if(md.getNaslov().equals(br1)) {return md;}
		}
		return null;
	}
	
	public void addDelo(MuzickoDelo md) {
		for (MuzickoDelo muzickoDelo : muzickaDela) {
			if (muzickoDelo.getNaslov().equals(md.getNaslov())) {
				return;
			}
		}
		this.muzickaDela.add(md);
	}
	
	public abstract String toFileString();
	
	@Override
	public String Ime() {
		// TODO Auto-generated method stub
		return this.getUmetnickoIme();
	}
	@Override
	public String putDoSlike() {
		// TODO Auto-generated method stub
		return "fajlovi/slike" + this.Ime() + ".jpg";
	}

	public boolean isOdobrenost() {
		return odobrenost;
	}

	public void setOdobrenost(boolean odobrenost) {
		this.odobrenost = odobrenost;
	}



	public static String getNaizvZanrova(ArrayList<Zanr> zanr) {
		String ime = "";
		for(Zanr jedanZanr : zanr){
			ime += jedanZanr.getNazivZanra() + ",";
		}
		return ime.substring(0, ime.length()-1);
	}

	@Override
	public Collection<Zanr> getZanrovi() {
		// TODO Auto-generated method stub
		return this.zanrovi;
	}
}