package controler;

import java.util.ArrayList;
import java.util.Collection;

import model.IzvestajSvihZanrova;
import model.MuzickoDelo;
import model.Recenzija;
import model.Utisak;
import model.Zanr;

public class IzvestajSvihZanrovaMenadzer {
	ArrayList<IzvestajSvihZanrova> sviZanrovi;

	public ArrayList<IzvestajSvihZanrova> getSviZanrovi() {
		return sviZanrovi;
	}

	public void setSviZanrovi(ArrayList<IzvestajSvihZanrova> sviZanrovi) {
		this.sviZanrovi = sviZanrovi;
	}

	public IzvestajSvihZanrovaMenadzer(ArrayList<IzvestajSvihZanrova> sviZanrovi) {
		super();
		this.sviZanrovi = sviZanrovi;
	}

	public IzvestajSvihZanrovaMenadzer(Collection<MuzickoDelo> dela, Collection<Recenzija> recenzije, ArrayList<Zanr> z) {
		for(Zanr za:z) {
			IzvestajSvihZanrova iz=new IzvestajSvihZanrova(za.getNazivZanra());
			this.sviZanrovi.add(iz);
			ArrayList<MuzickoDelo> md=pronadiDela(dela, iz);
			pronadiRecenzije(md,iz);
		}
		
	}

	private void pronadiRecenzije(ArrayList<MuzickoDelo> md, IzvestajSvihZanrova iz) {
		for(MuzickoDelo m:md) {
			for(Utisak u:m.getUtisci()) {
				if(u instanceof Recenzija) {iz.setBrojRecenzija(iz.getBrojRecenzija()+1);}
			}
		}
		
	}

	private ArrayList<MuzickoDelo> pronadiDela(Collection<MuzickoDelo> dela, IzvestajSvihZanrova iz) {
		ArrayList<MuzickoDelo> mD=new ArrayList<MuzickoDelo>();
		for(MuzickoDelo m:dela) {
			int i=0;
			for(Zanr z:m.getZanrovi()) {
				if(z.getNazivZanra()==iz.getNaziv()) {
					iz.setBrojMuzdela(iz.getBrojMuzdela()+1);
					if(i==0) {
					mD.add(m);i++;
					}
				}
			}
		}
		return mD;
	}
	
	

}
