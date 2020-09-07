package model;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controler.Constants;
import view.Slikovit;

public class Album implements Slikovit {
	private Izvodjac izvodjac;
	private ArrayList<MuzickoDelo> listaPesama;
	private Urednik urednik; //urednik koji je izvrsio registraciju tog albuma
	private Date danRegistracije;
	private boolean odobreno; 
	private String naslov;
	private boolean izbrisi;
	
	
	
	public boolean isIzbrisi() {
		return izbrisi;
	}

	public void setIzbrisi(boolean izbrisi) {
		this.izbrisi = izbrisi;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public ArrayList<MuzickoDelo> getListaPesama() {
		return listaPesama;
	}
	
	public Izvodjac getIzvodjac() {
		return izvodjac;
	}

	public void setIzvodjac(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
	}

	public void setListaPesama(ArrayList<MuzickoDelo> listaPesama) {
		this.listaPesama = listaPesama;
	}
	public Urednik getUrednik() {
		return urednik;
	}
	public void setUrednik(Urednik urednik) {
		this.urednik = urednik;
	}
	public Date getDanRegistracije() {
		return danRegistracije;
	}
	public void setDanRegistracije(Date danRegistracije) {
		this.danRegistracije = danRegistracije;
	}
	public boolean isOdobreno() {
		return odobreno;
	}
	public void setOdobreno(boolean odobreno) {
		this.odobreno = odobreno;
	}
	public Album(String naziv,ArrayList<MuzickoDelo> listaPesama, Urednik urednik,Izvodjac izv, Date danRegistracije, boolean odobreno) {
		super();
		this.naslov=naziv;
		this.listaPesama = listaPesama;
		this.urednik = urednik;
		this.danRegistracije = danRegistracije;
		this.odobreno = odobreno;
		this.izvodjac=izv;
	}
	public Album(String naziv, ArrayList<MuzickoDelo> listaPesama, Urednik urednik, Izvodjac izv) {
		super();
		this.naslov=naziv;
		this.listaPesama = listaPesama;
		this.urednik = urednik;
		this.danRegistracije=new Date();
		this.odobreno=false;
		this.izvodjac=izv;
	}

	public String toFileString() {
		//izvodjac,urednik, dan,odobreno, naslov, izbrisi, dela....
		String pattern = "dd.MM.yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String ad="";
		ad+=this.getIzvodjac().getUmetnickoIme()+",";
		ad+=this.getUrednik().getNalog().getKorisnickoIme()+",";
		ad+=df.format(this.getDanRegistracije())+",";
		ad+=this.isOdobreno()+",";
		ad+=this.getNaslov()+",";
		ad+=this.isIzbrisi()+",";
		for(MuzickoDelo md:this.getListaPesama()) {
			ad+= "|" + md.getNaziv();
		}
		return ad;
	}

	@Override
	public String Ime() {
		// TODO Auto-generated method stub
		return this.getNaslov();
	}

	@Override
	public String putDoSlike() {
		// TODO Auto-generated method stub
		return "fajlovi/" + Ime() + ".jpg";
	}

	@Override
	public BufferedImage defaultSlika() {
		// TODO Auto-generated method stub
		return Constants.ALBUM_IKONA;
	}
}
