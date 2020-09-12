package controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import model.IzvestajJednogIzvodjaca;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Recenzija;
import model.Utisak;
import model.Zanr;

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

	private void pretraziDela(ArrayList<MuzickoDelo> muzickaDela, IzvestajJednogIzvodjaca nov) {
		double ocenaKo=0;
		double ocenaUr=0; 
		for(MuzickoDelo m:muzickaDela) {
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

	public void izlistajPoDatumimaIZanru(Date dan, Date dan1, String imeZanra) {
		for(IzvestajJednogIzvodjaca iz:this.izvodjaci) {
			iz.setBrojDela(0);
			iz.setBrojKomentara(0);
			iz.setBrojRecenzija(0);
			iz.setOcenaKorisnika(0);
			iz.setOcenaUrednika(0);
			proveriPoDatumima(iz.getIzvodjacReferenca().getMuzickaDela(), dan, dan1, imeZanra, iz);
			
		}
		
	}

	private void proveriPoDatumima(ArrayList<MuzickoDelo> muzickaDela, Date dan, Date dan1,
			String imeZanra, IzvestajJednogIzvodjaca iz) {
		double ocenaKo=0;
		double ocenaUr=0; 
		int brD=0;
		//@SuppressWarnings("deprecation")
		//Date danPocetka=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
		//@SuppressWarnings("deprecation")
		//Date dankraja=new Date(dan1.getYear(), dan1.getMonthValue(), dan1.getDayOfMonth());
		if(dan.after(dan1)) {
			Date d=dan;
			dan=dan1;
			dan1=d;
		}
		for(MuzickoDelo m: muzickaDela) {
			if(m.getDatumIzadavanja().after(dan) && m.getDatumIzadavanja().before(dan1)) {
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
