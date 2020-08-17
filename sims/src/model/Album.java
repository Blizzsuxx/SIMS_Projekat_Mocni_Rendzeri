package model;

import java.util.ArrayList;
import java.util.Date;

public class Album {
	private Izvodjac izvodjac;
	private ArrayList<MuzickoDjelo> listaPesama;
	private Urednik urednik; //urednik koji je izvrsio registraciju tog albuma
	private Date danRegistracije;
	private boolean odobreno; 
	private String naslov;
	
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public ArrayList<MuzickoDjelo> getListaPesama() {
		return listaPesama;
	}
	
	public Izvodjac getIzvodjac() {
		return izvodjac;
	}

	public void setIzvodjac(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
	}

	public void setListaPesama(ArrayList<MuzickoDjelo> listaPesama) {
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
	public Album(String naziv,ArrayList<MuzickoDjelo> listaPesama, Urednik urednik,Izvodjac izv, Date danRegistracije, boolean odobreno) {
		super();
		this.naslov=naziv;
		this.listaPesama = listaPesama;
		this.urednik = urednik;
		this.danRegistracije = danRegistracije;
		this.odobreno = odobreno;
		this.izvodjac=izv;
	}
	public Album(String naziv, ArrayList<MuzickoDjelo> listaPesama, Urednik urednik, Izvodjac izv) {
		super();
		this.naslov=naziv;
		this.listaPesama = listaPesama;
		this.urednik = urednik;
		this.danRegistracije=new Date();
		this.odobreno=false;
		this.izvodjac=izv;
	}
	
	
}
