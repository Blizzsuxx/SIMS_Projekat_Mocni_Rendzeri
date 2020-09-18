package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.Album;
import model.Izvodjac;
import model.MuzickiSadrzaj;
import model.MuzickoDelo;
import model.Slikovit;
import model.TipMuzickogSadrzaja;
import model.Urednik;
import model.Zanr;
import view.TableModelWrapper;

public class MuzickiSadrzajMenadzer {
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private List<Album> albumi;
	private List<MuzickoDelo> muzickaDela;
	
	public MuzickiSadrzajMenadzer() {
		super();
		this.muzickiSadrzaj = new ArrayList<>();
		this.albumi = new ArrayList<>();
		this.muzickaDela = new ArrayList<>();
	}
	
	public MuzickiSadrzajMenadzer(IzvodjacMenadzer izvodjacMenadzer, KorisniciMenadzer korisniciMenadzer, ZanroviMenadzer zanroviMenadzer,
			String putanjaPrva, String putanjaDruga) {
		this();
		try {
			ucitaj(izvodjacMenadzer, korisniciMenadzer, zanroviMenadzer, putanjaPrva);
			poveziAlbumeDjela(putanjaDruga);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ucitaj(IzvodjacMenadzer izvodjacMenadzer, KorisniciMenadzer korisniciMenadzer, ZanroviMenadzer zanroviMenadzer, String putanja) throws ParseException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String linija = "";
			String[] tokeni;
			while((linija = br.readLine()) != null) {
				tokeni = linija.split(";");
				String naslov = tokeni[0].trim();
				String opis = tokeni[1].trim();
				Date datumIzdavanja = new SimpleDateFormat("dd.MM.yyyy.").parse(tokeni[2].trim());
				Izvodjac i = izvodjacMenadzer.nadiPoUmetnickomImenu(tokeni[3].trim());
				Urednik u = (Urednik)korisniciMenadzer.trazi(tokeni[4].trim());
				boolean status = Boolean.valueOf(tokeni[5].trim());
				String[] pomocniTokeni = tokeni[6].trim().split(",");
				List<Zanr> zanrovi = new ArrayList<>();
				for (String s: pomocniTokeni) 
					zanrovi.add(zanroviMenadzer.trazi(s));
				MuzickiSadrzaj ms = null;
				if (tokeni.length == 8) { // Album
					boolean odobrenost = Boolean.valueOf(tokeni[7].trim());
					ms = new Album(naslov, opis, datumIzdavanja, i, u, status, zanrovi, odobrenost); // unutar konstruktora se inicijalizuje lista pjesama
					this.albumi.add((Album)ms);
				} else { // MuzickoDjelo
					float prosjecnaOcjenaKorisnika = Float.parseFloat(tokeni[7].trim());
					float prosjecnaOcjenaUrednika = Float.parseFloat(tokeni[8].trim());
					ms = new MuzickoDelo(naslov, opis, datumIzdavanja, i, u, status, zanrovi, prosjecnaOcjenaKorisnika, prosjecnaOcjenaUrednika);
					this.muzickaDela.add((MuzickoDelo)ms);
				}
				
				this.muzickiSadrzaj.add(ms);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvaj(String putanja) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(putanja, false));
			for (MuzickiSadrzaj ms: this.muzickiSadrzaj)
				pw.print(ms.toFileString());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void poveziAlbumeDjela(String putanja) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String linija = "";
			String[] tokeni;
			String[] pomocniTokeni;
			while ((linija = br.readLine()) != null) {
				tokeni = linija.split("\\|");
				String naslovAlbuma = tokeni[0].trim();
				Album a = (Album) vratiNaOsnovuNazive(naslovAlbuma);
				pomocniTokeni = tokeni[1].trim().split(",");
				for (String nazivPjesme: pomocniTokeni)
					a.getListaPesama().add((MuzickoDelo)vratiNaOsnovuNazive(nazivPjesme));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajAlbumeDjela(String putanja) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			String linija;
			for (Album a: this.albumi) {
				linija = "";
				if (!a.getListaPesama().isEmpty()) {
					linija += a.getNaslov()+"|";
					for(MuzickoDelo md: a.getListaPesama()) {
						linija += md.getNaslov()+",";
					}
					pw.println(linija);
				}
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MuzickiSadrzaj vratiNaOsnovuNazive(String naziv) {
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj) 
			if (ms.getNaslov().equals(naziv))
				return ms;
		return null;
	}
	
	public List<Album> vratiAlbumeNaOsnovuOdobrenosti(boolean indikator){
		List<Album> albumi = new ArrayList<>();
		for(Album a: this.albumi)
			if (a.isOdobreno() == indikator)
				albumi.add(a);
		return albumi;
	}
	
	public boolean dozvolaAlbuma(String naslov) {
		for (Album a : this.albumi )
			if (a.isStatus() && !a.isOdobreno() && a.getNaslov().equals(naslov)) {
				a.setOdobreno(true);
				a.getIzvodjac().addIzdatAlbum(a);
				return true;
			}
		return false;
	}
	
	public List<MuzickiSadrzaj> vratiAktivanMuzickiSadrzaj(){
		List<MuzickiSadrzaj> temp = new ArrayList<>();
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj)
			if (ms.isStatus())
				temp.add(ms);
		return temp;
	}
	
	public List<MuzickiSadrzaj> vratiAktivneAlbumeSadrzaja(){
		List<MuzickiSadrzaj> temp = new ArrayList<>();
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj)
			if (ms.isStatus() && ms instanceof Album)
				temp.add(ms);
		return temp;
	}
	
	public List<MuzickiSadrzaj> vratiAktivnaMuzickaDjelaSadrzaja(){
		List<MuzickiSadrzaj> temp = new ArrayList<>();
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj)
			if (ms.isStatus() && ms instanceof MuzickoDelo)
				temp.add(ms);
		return temp;
	}
	
	public List<Album> vratiAktivneAlbume(){
		List<Album> temp = new ArrayList<>();
		for (Album ms: this.albumi)
			if (ms.isStatus())
				temp.add(ms);
		return temp;
	}
	
	public List<MuzickoDelo> vratiAktivnaMuzickaDjela(){
		List<MuzickoDelo> temp = new ArrayList<>();
		for (MuzickoDelo ms: this.muzickaDela)
			if (ms.isStatus())
				temp.add(ms);
		return temp;
	}

	public void pretrageMuzickogSadrzajaNaOsnovuZanrova(List<MuzickiSadrzaj> muzickiSadrzaj, List<Zanr> zanrovi, TipMuzickogSadrzaja tip) {
		muzickiSadrzaj.clear();
		boolean provjera;
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj) {
			if (ms.isStatus()) {
				provjera = false;
				if (tip == TipMuzickogSadrzaja.ALBUM && ms instanceof Album)
					provjera = true;
				else if (tip == TipMuzickogSadrzaja.MUZICKO_DELO && ms instanceof MuzickoDelo)
					provjera = true;
				else if (tip == null)
					provjera = true;
				if (provjera) {
					for (Zanr z: ms.getZanrovi()) {
						if (zanrovi.contains(z)) {
							muzickiSadrzaj.add(ms);
							break;
						}
					}
				}
			}
		}
	}

	public TableModelWrapper getTabelaMuzickihDela(Izvodjac izvodjac)  throws Exception {
		String[] columns = { "Naziv" ,"Opis", "Datum izdavanja"};
		Class<?>[] columnTypes = { String.class, String.class, Date.class};
		boolean[] editableColumns = { false, false, false};
		int[] columnWidths = { 200, 200, 200};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (MuzickoDelo md : muzickaDela) {
			if (md.getIzvodjac().getUmetnickoIme().equals(izvodjac.getUmetnickoIme()))
				data.add(new Object[] {md.getNaslov(), md.getOpis(), md.getDatumIzadavanja()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
	
	public TableModelWrapper getTabelaMuzickihDela()  throws Exception {
		String[] columns = { "Naziv" ,"Opis", "Datum izdavanja"};
		Class<?>[] columnTypes = { String.class, String.class, Date.class};
		boolean[] editableColumns = { false, false, false};
		int[] columnWidths = { 200, 200, 200};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (MuzickoDelo md : muzickaDela) {
			data.add(new Object[] {md.getNaslov(), md.getOpis(), md.getDatumIzadavanja()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}

	public Collection<MuzickiSadrzaj> traziMuzickaDela(String substring) {
		Collection<MuzickiSadrzaj> delaKojaSadzeString = new ArrayList<>();
		for(MuzickoDelo delo : this.muzickaDela) {
			if(delo.Ime().contains(substring)) delaKojaSadzeString.add(delo);
		}
		return delaKojaSadzeString;
	}
	

	public Collection<Album> traziAlbume(String textZaSearch) { // da se razliku od gornje funkcije
		Collection<Album> rezultat = new ArrayList<>();
		for(Album a : this.albumi) {
			if(a.Ime().contains(textZaSearch)) {
				rezultat.add(a);
			}
		}
		return rezultat;
	}
	
	public List<MuzickiSadrzaj> vratiMuzickiSadrzajUrednika(List<Zanr> zanroviUrednika){ // vraca sadrzaj na osnovu zanrova urednika
		List<MuzickiSadrzaj> temp = new ArrayList<>();
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj) {
			if (ms.isStatus()) {
				for (Zanr z: ms.getZanrovi()) {
					if (zanroviUrednika.contains(z)) {
						temp.add(ms);
						break;
					}
				}
			}
		}
		return temp;
	}
	public List<MuzickiSadrzaj> getMuzickiSadrzaj() {
		return muzickiSadrzaj;
	}

	public void setMuzickiSadrzaj(List<MuzickiSadrzaj> muzickiSadrzaj) {
		this.muzickiSadrzaj = muzickiSadrzaj;
	}

	public List<Album> getAlbumi() {
		return albumi;
	}

	public void setAlbumi(List<Album> albumi) {
		this.albumi = albumi;
	}

	public List<MuzickoDelo> getMuzickaDela() {
		return muzickaDela;
	}

	public void setMuzickaDela(List<MuzickoDelo> muzickaDela) {
		this.muzickaDela = muzickaDela;
	}
	
	public boolean postojiDelo(MuzickoDelo muzickoDelo) {
		for (MuzickoDelo md : muzickaDela) {
			if (md.getNaslov().equals(muzickoDelo.getNaslov()))
				return true;
		}
		return false;
	}
	
	public boolean postojiAlbum(Album album) {
		for (Album a : albumi) {
			if (a.getNaslov().equals(album.getNaslov()))
				return true;
		}
		return false;
	}
	
	
}
