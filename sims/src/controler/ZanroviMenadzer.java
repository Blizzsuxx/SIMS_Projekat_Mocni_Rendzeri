package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Zanr;

public class ZanroviMenadzer { //ovu klasu ili treba da ima sesija ili da bude prosledjena instanca klase IzvestajViseIzvodjaca
	private ArrayList<Zanr> sviZanrovi;

	public ArrayList<Zanr> getSviZanrovi() {
		return sviZanrovi;
	}

	public void setSviZanrovi(ArrayList<Zanr> sviZanrovi) {
		this.sviZanrovi = sviZanrovi;
	}

	public ZanroviMenadzer(ArrayList<Zanr> sviZanrovi) {
		super();
		this.sviZanrovi = sviZanrovi;
	}

	public ZanroviMenadzer() {
		this.sviZanrovi = new ArrayList<Zanr>();
	}

	public ZanroviMenadzer(List<String[]> data){
		ucitajZanrove(data);
	}
	
	
	public String[] izlistajSveZanrove() {
		String[] imena= {};
		int i=0;
		for(Zanr z:sviZanrovi) {
			imena[i]=z.getNazivZanra();
					i++;
		}
		return imena;}


	public ArrayList<Zanr> getZanrovi() {
		
		return this.sviZanrovi;
	}

	private void ucitajZanrove(List<String[]> data) {

		for(String[] linije : data){
			Zanr a = new Zanr(linije[0].trim(), true );
			if (linije[1].trim().equals("true")) {
			a.setStatus(true);
			}else {
				a.setStatus(false);
			}
			sviZanrovi.add(a);
		}
		
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"zanrovi.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Zanr a:sviZanrovi) {
				pw.println(a.toFileString());
				
			}pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		
	}
	

}
