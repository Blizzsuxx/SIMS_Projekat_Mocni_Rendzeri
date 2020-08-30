package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jaco.mp3.player.MP3Player;
import model.Komentar;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import net.miginfocom.swing.MigLayout;

public class MediaPlayer extends MojDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MP3Player getMediaPlayer(String nazivPesme, String ekstenzija) {

        File fajl = new File("./sims/fajlovi/muzika/" + nazivPesme + ekstenzija);
        if (!fajl.exists())
            fajl = new File("./fajlovi/muzika/" + nazivPesme + ekstenzija);
        MP3Player mediaPlayer = new MP3Player(fajl);
        return mediaPlayer;
    }

    public MediaPlayer(JFrame parent, MuzickoDelo delo) {
        super(parent, "Media Player");
        this.setResizable(true);
        this.getContentPane().setLayout(new MigLayout());
        MP3Player mediaPlayer = getMediaPlayer(delo.getNaziv(), ".mp3");
        mediaPlayer.setPreferredSize(new Dimension(this.getWidth() , 20));
        MuzickoDeloLabel labela = new MuzickoDeloLabel(this.getWidth(), 100, delo);
        labela.setClickable(false);
        this.add(labela, "wrap");
        add(mediaPlayer, "span, wrap");

        JPanel dataPanel = new JPanel(new MigLayout());
        StarRater rater = new StarRater(5, 1.2f, 3);
        JLabel datum = new JLabel("Datum izdavanja: " + delo.getDatumIzdavanja());
        dataPanel.add(datum);
        dataPanel.add(rater, "gapleft 40");
        this.add(dataPanel, "wrap");

        JTextArea opis = new JTextArea(delo.getOpis());
        JScrollPane pane = new JScrollPane(opis);
        pane.setSize(new Dimension(this.getWidth(), 100));
        opis.setPreferredSize(pane.getSize());
        opis.setEditable(false);

        
        ExpandingPanel opisPanel = new ExpandingPanel("Opis");
        opisPanel.getContent().add(pane, BorderLayout.CENTER);
        add(opisPanel, "wrap");
        
        mediaPlayer.play();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                mediaPlayer.stop();
            }
        });


        //////////////////////////////
        KomentarView komentari = new KomentarView();
        
        ExpandingPanel expandingPanel = new ExpandingPanel("Komentari");
        expandingPanel.getContent().add(komentari, BorderLayout.CENTER);
        komentari.setSize(this.getWidth()-5, 100);
        komentari.setPreferredSize(new Dimension(this.getWidth()-5, 100));
        this.add(expandingPanel, "wrap");
        
        KorisnikAplikacije k = new KorisnikAplikacije();
        k.setKorisnickoIme("korisnickoIme");
        komentari.addKomentar(new Komentar("AAAAAAAAA", new Date(), true, delo, k));
        //////////////////////////

        JScrollPane scroll = new JScrollPane(this.getContentPane());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setContentPane(scroll);

        }
        
    }