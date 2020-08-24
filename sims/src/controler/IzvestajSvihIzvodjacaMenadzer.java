package controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import model.IzvestajJednogIzvodjaca;
import model.Izvodjac;
import model.MuzickoDjelo;
import model.Recenzija;
import model.Utisak;
import model.Zanr;
import view.IzvestajIzvodjaca;

public class IzvestajSvihIzvodjacaMenadzer {
	private ArrayList<IzvestajJednogIzvodjaca> izvodjaci;

	public ArrayList<IzvestajJednogIzvodjaca> getIzvodjaci() {
		return izvodjaci;
	}

	public void setIzvodjaci(ArrayList<IzvestajJednogIzvodjaca> izvodjaci) {
		this.izvodjaci = izvodjaci;
	}

	public IzvestajSvihIzvodjacaMenadzer(ArrayList<IzvestajJednogIzvodjaca> izvodjaci) {
		super();
		this.izvodjaci = izvodjaci;
	}

	public IzvestajSvihIzvodjacaMenadzer() {
		super();
		this.izvodjaci=new ArrayList<IzvestajJednogIzvodjaca>();
	}
	
	public void namestiIzvestaj(ArrayList<Izvodjac> izv) {
		for(Izvodjac i:izv) {
			model.IzvestajJednogIzvodjaca nov=new IzvestajJednogIzvodjaca(i.getUmetnickoIme());
			nov.setIzvodjacReferenca(i);
			nov.setBrojDela(i.getMuzickaDela().size());
			pretraziDela(i.getMuzickaDela(), nov);
			izvodjaci.add(nov);
		}
	}

	private void pretraziDela(ArrayList<MuzickoDjelo> muzickaDela, IzvestajJednogIzvodjaca nov) {
		double ocenaKo=0;
		double ocenaUr=0; 
		for(MuzickoDjelo m:muzickaDela) {
			ocenaKo+=m.getProsecnaOcenaKorisnika();
			ocenaUr+=m.getProsecnaOcenaUrednika();
			for(Utisak u:m.getUtisci()) {
				if(u instanceof Recenzija) {
					nov.setBrojRecenzija(nov.getBrojRecenzija()+1);
				}else {
					nov.setBrojKomentara(nov.getBrojKomentara()+1);
				}
			}
		}
		nov.setOcenaKorisnika(ocenaKo/muzickaDela.size());
		nov.setOcenaUrednika(ocenaUr/muzickaDela.size());
		
	}

	public void izlistajPoDatumimaIZanru(LocalDate dan, LocalDate dan1, String imeZanra) {
		for(IzvestajJednogIzvodjaca iz:this.izvodjaci) {
			iz.setBrojDela(0);
			iz.setBrojKomentara(0);
			iz.setBrojRecenzija(0);
			iz.setOcenaKorisnika(0);
			iz.setOcenaUrednika(0);
			proveriPoDatumima(iz.getIzvodjacReferenca().getMuzickaDela(), dan, dan1, imeZanra, iz);
			
		}
		
	}

	private void proveriPoDatumima(ArrayList<MuzickoDjelo> muzickaDela, LocalDate dan, LocalDate dan1,
			String imeZanra, IzvestajJednogIzvodjaca iz) {
		double ocenaKo=0;
		double ocenaUr=0; 
		int brD=0;
		Date danPocetka=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
		Date dankraja=new Date(dan1.getYear(), dan1.getMonthValue(), dan1.getDayOfMonth());
		for(MuzickoDjelo m: muzickaDela) {
			if(m.getDatumIzdavanja().after(danPocetka) && m.getDatumIzdavanja().before(dankraja)) {
				for(Zanr z:m.getZanrovi()) {
					if(z.getNazivZanra().equals(imeZanra)) {
						iz.setBrojDela(iz.getBrojDela()+1);
						ocenaKo+=m.getProsecnaOcenaKorisnika();
						ocenaUr+=m.getProsecnaOcenaUrednika();
						for(Utisak u:m.getUtisci()) {
							if(u instanceof Recenzija) {
								iz.setBrojRecenzija(iz.getBrojRecenzija()+1);
							}else {
								iz.setBrojKomentara(iz.getBrojKomentara()+1);
							}
						}
						brD++;
						break;
					}
				}
			}
		}
		iz.setOcenaKorisnika(ocenaKo/brD);
		iz.setOcenaUrednika(ocenaUr/brD);
		iz.setBrojDela(brD);
		
	}

}
