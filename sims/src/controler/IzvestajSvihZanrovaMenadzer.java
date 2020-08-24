package controler;

import java.util.ArrayList;
import java.util.Collection;

import model.IzvestajSvihZanrova;
import model.MuzickoDjelo;
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

	public IzvestajSvihZanrovaMenadzer(Collection<MuzickoDjelo> dela, Collection<Recenzija> recenzije, ArrayList<Zanr> z) {
		for(Zanr za:z) {
			IzvestajSvihZanrova iz=new IzvestajSvihZanrova(za.getNazivZanra());
			this.sviZanrovi.add(iz);
			ArrayList<MuzickoDjelo> md=pronadiDela(dela, iz);
			pronadiRecenzije(md,iz);
		}
		
	}

	private void pronadiRecenzije(ArrayList<MuzickoDjelo> md, IzvestajSvihZanrova iz) {
		for(MuzickoDjelo m:md) {
			for(Utisak u:m.getUtisci()) {
				if(u instanceof Recenzija) {iz.setBrojRecenzija(iz.getBrojRecenzija()+1);}
			}
		}
		
	}

	private ArrayList<MuzickoDjelo> pronadiDela(Collection<MuzickoDjelo> dela, IzvestajSvihZanrova iz) {
		ArrayList<MuzickoDjelo> mD=new ArrayList<MuzickoDjelo>();
		for(MuzickoDjelo m:dela) {
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
