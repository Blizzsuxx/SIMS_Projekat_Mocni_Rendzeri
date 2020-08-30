package controler;

import java.time.LocalDate;
import java.util.ArrayList;

import model.PodaciUrednikaZaIzvestaj;
import model.Urednik;

public class IzvestajViseUrednikaMenadzer {
	private ArrayList<PodaciUrednikaZaIzvestaj> podaci;

	public ArrayList<PodaciUrednikaZaIzvestaj> getPodaci() {
		return podaci;
	}

	public void setPodaci(ArrayList<PodaciUrednikaZaIzvestaj> podaci) {
		this.podaci = podaci;
	}

	public IzvestajViseUrednikaMenadzer(ArrayList<PodaciUrednikaZaIzvestaj> podaci) {
		super();
		this.podaci = podaci;
	}

	public IzvestajViseUrednikaMenadzer() {
		super();
	}
	
	public IzvestajViseUrednikaMenadzer(LocalDate danPocetka, LocalDate danKraja, ArrayList<Urednik> sviUrednici) {
		podaci=new ArrayList<PodaciUrednikaZaIzvestaj>();
		for(Urednik u: sviUrednici) {
			PodaciUrednikaZaIzvestaj i=new PodaciUrednikaZaIzvestaj(danPocetka, danKraja, u);
			podaci.add(i);
		}
		
	}
	
	
	
	
	
}
