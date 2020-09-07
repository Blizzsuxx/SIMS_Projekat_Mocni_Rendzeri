package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jaco.mp3.player.MP3Player;
import model.FrontEndKorisnik;
import model.Komentar;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import model.Recenzija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class MediaPlayer extends MojDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    UtisakView komentari;
    MuzickoDelo delo;
    
    private JButton dugmeKomentar;
    Korisnik trenutniKorisnik;
    JTextArea poljeKomentar;
    

    private MP3Player getMediaPlayer(String nazivPesme, String ekstenzija) {

        File fajl = new File("./sims/fajlovi/muzika/" + nazivPesme + ekstenzija);
        if (!fajl.exists())
            fajl = new File("./fajlovi/muzika/" + nazivPesme + ekstenzija);
        
        MP3Player mediaPlayer = null;
        if(fajl.exists()) {
        	mediaPlayer = new MP3Player(fajl);
        } else {
        	mediaPlayer = new MP3Player();
        }
        return mediaPlayer;
    }

    public MediaPlayer(JFrame parent, MuzickoDelo delo, Korisnik trenutniKorisnik) {
        super(parent, "Media Player");
        this.trenutniKorisnik = trenutniKorisnik;
        this.delo = delo;
        this.setResizable(true);
        this.getContentPane().setLayout(new MigLayout());
        MP3Player mediaPlayer = getMediaPlayer(delo.getNaziv(), ".mp3");
        mediaPlayer.setPreferredSize(new Dimension(this.getWidth() , 20));
        MuzickoDeloLabel labela = new MuzickoDeloLabel(trenutniKorisnik, this.getWidth()-50, 100, delo);
        labela.setClickable(false);
        this.add(labela, "wrap");
        add(mediaPlayer, "span, wrap");

        JPanel dataPanel = new JPanel(new MigLayout());
        StarRater rater = new StarRater(5, 3, 3);
        JLabel datum = new JLabel("Datum izdavanja: " + delo.getDatumIzdavanja());
        dataPanel.add(rater);
        dataPanel.add(datum, "gapleft 50");
        this.add(dataPanel, "wrap 20");


        UtisakView recenzije = new UtisakView();
        
        ExpandingPanel recenzijePanel = new ExpandingPanel("Recenzije");
        recenzijePanel.getContent().add(recenzije, BorderLayout.CENTER);
        recenzije.setSize(this.getWidth()-5, 100);
        recenzije.setPreferredSize(new Dimension(this.getWidth()-5, 100));
        this.add(recenzijePanel, "wrap 20");
        
        Urednik urednik = new Urednik();
        urednik.setKorisnickoIme("urednik");
        recenzije.addKomentar(new Recenzija("text", new Date(), true, urednik, delo, "naslov"));


        JTextArea opis = new JTextArea(delo.getOpis());
        JScrollPane pane = new JScrollPane(opis);
        pane.setSize(new Dimension(this.getWidth()-5, 100));
        opis.setPreferredSize(new Dimension(this.getWidth()-5, 100));
        opis.setEditable(false);

        ExpandingPanel opisPanel = new ExpandingPanel("Opis");
        opisPanel.getContent().add(pane, BorderLayout.CENTER);
        add(opisPanel, "wrap 20");
        
        if(mediaPlayer.getPlayList().size() > 0) mediaPlayer.play();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                mediaPlayer.stop();
            }
        });


        //////////////////////////////
        komentari = new UtisakView();
        
        ExpandingPanel expandingPanel = new ExpandingPanel("Komentari");
        
        //Dodavanje JPanel za upis komentara u kome ce biti JTextArea i JButton
        JPanel panelKomentar = new JPanel(new MigLayout());
        poljeKomentar = new JTextArea(3, 42);
        dugmeKomentar = new JButton("Komentarisi");
        panelKomentar.add(poljeKomentar, "wrap");
        panelKomentar.add(dugmeKomentar);
        expandingPanel.getContent().add(panelKomentar, BorderLayout.NORTH);
        setListeners();	//Funkcija za dodavanje listenera
        
        expandingPanel.getContent().add(komentari, BorderLayout.CENTER);
        komentari.setSize(this.getWidth()-5, 100);
        komentari.setPreferredSize(new Dimension(this.getWidth()-5, 100));
        this.add(expandingPanel, "wrap 20");
        
        
        
        KorisnikAplikacije k = new KorisnikAplikacije();
        k.setKorisnickoIme("korisnickoIme");
        komentari.addKomentar(new Komentar("AAAAAAAAA", new Date(), true, delo, k));
        //////////////////////////
        JScrollPane scroll = new JScrollPane(this.getContentPane());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setContentPane(scroll);
        }
    
    public void setListeners()
    {
    	dugmeKomentar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comment();	
			}
		});
    }
   
    private void comment()
    {
    	//Funkcija za dodavanje komentara
    	if(trenutniKorisnik == null)
    	{
    		JOptionPane.showMessageDialog(MediaPlayer.this, "Da bi ste dodali komentar morate biti ulogovani", "Dodavanje komentara", JOptionPane.INFORMATION_MESSAGE);
    		poljeKomentar.setText("");
    	}
    	else
    	{
    		Komentar kom = new Komentar(poljeKomentar.getText(), new Date(), true, delo, (FrontEndKorisnik)trenutniKorisnik);
    		komentari.addKomentar(kom);
    		JOptionPane.showMessageDialog(MediaPlayer.this, "Uspesno ste dodali komentar", "Komentar", JOptionPane.INFORMATION_MESSAGE);
    		poljeKomentar.setText("");
    	}
    }

        
    }