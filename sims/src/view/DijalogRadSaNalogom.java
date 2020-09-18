package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.jdesktop.swingx.JXImagePanel;

import controler.Constants;
import model.FrontEndKorisnik;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Sesija;
import model.Slikovit;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class DijalogRadSaNalogom extends MojDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel imeLabela, prezLabela, mailLabela, polLabela, dateLabela, sifraLabela, userLabela;
	private MojTextField poljeIme, poljePrez, poljeMail, poljePol, poljeDate, poljeUser;
	private JPasswordField poljeSifra;
	private JButton izadjiBtn, potvrdiBtn;
	private Korisnik korisnik;
	private JLabel brojPratioca;

	private boolean indikator;
	

	
	public DijalogRadSaNalogom(JFrame parent, Korisnik korisnik, String naziv) {
		super(parent, naziv);
		this.korisnik = korisnik;
		initGui();
		setListeners();
	}
	
	public DijalogRadSaNalogom(JFrame parent, Korisnik korisnik, String naziv, boolean indikator) {
		super(parent, naziv);
		this.korisnik = korisnik;
		this.indikator = indikator;
		initGui();
		setListeners();
	}

	private void initGui() {
		this.setSize(450, 600);
		this.brojPratioca = new JLabel();
		this.setLayout(new MigLayout("fillx"));

		ExpandingPanel userInfo = new ExpandingPanel("Korisnicke informacije");

		MigLayout mig = new MigLayout("",
									  "50 [] 50 []", //Kolone
									  "10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10"); //Vrste
		userInfo.getContent().setLayout(mig);
		
		///////////////// - mali dodatak od strane Dragana, ako ti se ne svidja obrisi
		///////////////// - izbrisi takodje i prvi red u MigLayout-u, tj "[] 30" deo
		JXImagePanel image = new JXImagePanel();
		image.setEditable(true);
		
		
		
		Callable<Image> skalirajSliku = new Callable<Image>() {
			
			@Override
			public Image call() throws Exception {
				// TODO Auto-generated method stub
				return image.getImage().getScaledInstance(-1, 300, Image.SCALE_DEFAULT);
			}
		};
		image.setImageLoader(skalirajSliku);
		image.setImage(this.korisnik.defaultSlika().getScaledInstance(-1, 300, Image.SCALE_DEFAULT));
		this.add(image, "span, alignx center, wrap");
		
		if( korisnik instanceof Urednik){
			if(Sesija.getTrenutniKorisnik() instanceof KorisnikAplikacije && !Sesija.getTrenutniKorisnik().equals(this.korisnik)){
				this.add(this.brojPratioca);
				initFollowButton();
			} else {
				this.add(this.brojPratioca, "wrap");
			}
			refreshBrojPratioca();
			
		}
		////////////////// kraj promena
		
		
		imeLabela  = new JLabel("Ime"); prezLabela = new JLabel("Prezime"); 		mailLabela = new JLabel("eMail");
		polLabela  = new JLabel("Pol"); dateLabela = new JLabel("Datum Rodjenja"); 	sifraLabela = new JLabel("Sifra");
		userLabela = new JLabel("Korisnicko ime");
		
		poljeIme   = new MojTextField(20); poljeIme.setText(korisnik.getIme()); 
		poljePrez  = new MojTextField(20); poljePrez.setText(korisnik.getPrezime());
		poljeMail  = new MojTextField(20); poljeMail.setText(korisnik.geteMail());
		poljePol   = new MojTextField(20); poljePol.setText(korisnik.getPol());
		poljeDate  = new MojTextField(20); poljeDate.setText(korisnik.getDatumRodjenja());
		poljeUser =  new MojTextField(20); poljeUser.setText(korisnik.getNalog().getKorisnickoIme());
		
		poljeSifra = new JPasswordField(20);
		
		izadjiBtn  = new JButton("Izadji");
		potvrdiBtn = new JButton("Potvrdi");
		
		userInfo.getContent().add(imeLabela);   userInfo.getContent().add(poljeIme,   "wrap");
		userInfo.getContent().add(prezLabela);  userInfo.getContent().add(poljePrez,  "wrap");
		userInfo.getContent().add(mailLabela);  userInfo.getContent().add(poljeMail,  "wrap");
		userInfo.getContent().add(polLabela);   userInfo.getContent().add(poljePol,   "wrap");
		userInfo.getContent().add(dateLabela);  userInfo.getContent().add(poljeDate,  "wrap");
		userInfo.getContent().add(userLabela);  userInfo.getContent().add(poljeUser,  "wrap");
		userInfo.getContent().add(sifraLabela); userInfo.getContent().add(poljeSifra, "wrap");
		
		userInfo.getContent().add(potvrdiBtn);
		
		if(korisnik instanceof FrontEndKorisnik) {
			JButton izbrisiNalog =new JButton("Izbrisi nalog");
			userInfo.getContent().add(izbrisiNalog);
			izbrisiNalog.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(JOptionPane.showConfirmDialog(null, 
							"Jeste sigurni da zelite izaci iz aplikacije?", "IZLAZ", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						
						
						korisnik.setStatus(false);
						if(Sesija.getTrenutniKorisnik().equals(korisnik)) {
							Sesija.setTrenutniKorisnik(null);
						}
						DijalogRadSaNalogom.this.dispose();
					}
					
					
				}
			});
		}

		this.add(userInfo, "growx, spanx, wrap");

		if(korisnik instanceof FrontEndKorisnik){
			FrontEndKorisnik k = (FrontEndKorisnik) this.korisnik;
			ExpandingPanel istorija = new ExpandingPanel("istorija");
			Collection<Slikovit> slike = (Collection<Slikovit>)  (Collection<?>) k.getIstorija();

			slike = slike.size() == 0 ? Constants.DELA : slike; 
			

			istorija.setContent(new SearchResults(slike));
			this.add(istorija, "growx, spanx, wrap");
		}
		this.add(izadjiBtn, "wrap");
		if (indikator) { // ako gledamo korisnika cisto radi informacija
			poljeSifra.setVisible(false);
			sifraLabela.setVisible(false);
			potvrdiBtn.setVisible(false);
		}
	}
	
	private void initFollowButton() {

		JButton zaprati = new JButton("Zaprati");
        zaprati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(zaprati.getText().equals("Zapratio")) return;
				zaprati.setText("Zapratio");
				KorisnikAplikacije trenutniKorisnik = (KorisnikAplikacije) Sesija.getTrenutniKorisnik();
				Urednik urednik = (Urednik) korisnik;
                urednik.getPratilac().add(trenutniKorisnik);
                trenutniKorisnik.addPratite(urednik);
                refreshBrojPratioca();
		}});
		this.add(zaprati, "wrap");

	}

	private void refreshBrojPratioca() {
		this.brojPratioca.setText("Pratioci: " + ((Urednik) korisnik).getPratilac().size());
	}

	void setListeners() {
		izadjiBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DijalogRadSaNalogom.this.dispose();
			}
		});
		
		potvrdiBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(poljeSifra.getText().equals("")) {
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Popunite sva polja", "Prazna polja", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(!(korisnik.getNalog().getSifra().equals(poljeSifra.getText()))) {
				
					korisnik.getNalog().setSifra(poljeSifra.getText());
					System.out.println(korisnik.getNalog().getSifra());
					
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Uspesno ste izmenili sifru", "Izmena sifre", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Nista niste menjali", "Izmena", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
}
