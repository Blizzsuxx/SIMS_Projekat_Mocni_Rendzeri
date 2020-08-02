package controler;

import java.time.LocalDate;
import java.util.ArrayList;

import model.POdaciUrednikaZaIzvestaj;
import model.Urednik;

public class IzvestajViseUrednikaMenadzer {
	private ArrayList<POdaciUrednikaZaIzvestaj> podaci;

	public ArrayList<POdaciUrednikaZaIzvestaj> getPodaci() {
		return podaci;
	}

	public void setPodaci(ArrayList<POdaciUrednikaZaIzvestaj> podaci) {
		this.podaci = podaci;
	}

	public IzvestajViseUrednikaMenadzer(ArrayList<POdaciUrednikaZaIzvestaj> podaci) {
		super();
		this.podaci = podaci;
	}

	public IzvestajViseUrednikaMenadzer() {
		super();
	}
	
	public IzvestajViseUrednikaMenadzer(LocalDate danPocetka, LocalDate danKraja, ArrayList<Urednik> sviUrednici) {
		podaci=new ArrayList<POdaciUrednikaZaIzvestaj>();
		for(Urednik u: sviUrednici) {
			POdaciUrednikaZaIzvestaj i=new POdaciUrednikaZaIzvestaj(danPocetka, danKraja, u);
			podaci.add(i);
		}
		
	}
	
	
	
	
	
}
