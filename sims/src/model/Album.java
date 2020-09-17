package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controler.Constants;

public class Album extends MuzickiSadrzaj {
	private List<MuzickoDelo> listaPesama;
	private boolean odobreno;
	
	public Album() {
		super();
	}
	
	public Album(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik, boolean status) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status);
		this.listaPesama = new ArrayList<>();
	}

	
	public Album(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik, boolean status,
			List<Zanr> zanrovi) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status, zanrovi);
		this.listaPesama = new ArrayList<>();
	}
	
	public Album(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik, boolean status,
			List<Zanr> zanrovi, List<MuzickoDelo> listaPesama, boolean odobreno) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status, zanrovi);
		this.listaPesama = listaPesama;
		this.odobreno = odobreno;
	}

	
	public Album(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik, boolean status,
			List<Zanr> zanrovi, boolean odobreno) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status, zanrovi);
		this.odobreno = odobreno;
		this.listaPesama = new ArrayList<>();
	}


	@Override
	public String toFileString() {
		return String.format("%s;%s\n", super.toFileString(), this.odobreno);
	}


	@Override
	public String Ime() {
		return this.getNaslov();
	}

	@Override
	public String putDoSlike() {
		return "fajlovi/" + Ime() + ".jpg";
	}

	@Override
	public BufferedImage defaultSlika() {
		return Constants.ALBUM_IKONA;
	}

	public List<MuzickoDelo> getListaPesama() {
		return listaPesama;
	}

	public void setListaPesama(List<MuzickoDelo> listaPesama) {
		this.listaPesama = listaPesama;
	}

	public boolean isOdobreno() {
		return odobreno;
	}

	public void setOdobreno(boolean odobreno) {
		this.odobreno = odobreno;
	}
	
	
}
