package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Clan;
import model.Pojedinacanizvodjac;
import model.Slikovit;

public class PojedinacanIzvodjacView extends IzvodjacView {

    public PojedinacanIzvodjacView(JFrame parent, Pojedinacanizvodjac izvodjac) {
        super(parent, izvodjac);
        
        ExpandingPanel informacijeOGrupama = new ExpandingPanel("Grupe");
        ArrayList<Slikovit> grupe = new ArrayList<>();
        for(Clan clan : izvodjac.getClanstvaUGrupama()){
            grupe.add(clan.getGrupa());
        }
        informacijeOGrupama.setContent(new SearchResults(grupe));
        this.add(informacijeOGrupama, "growx, wrap");

        ExpandingPanel informacijeOIzvodjacu = new ExpandingPanel("informacije o izvodjacu");
        informacijeOIzvodjacu.getContent().add(new JLabel("Izvodjac se zove: " + izvodjac.getIme() + " " + izvodjac.getPrezime()));
        informacijeOIzvodjacu.getContent().add(new JLabel("Pol: " + izvodjac.getPol()), "wrap");
        informacijeOIzvodjacu.getContent().add(new JLabel("Ziveo: " + izvodjac.getDatumRodjenja() + " - " + (izvodjac.getDatumSmrti() == null ? "" : izvodjac.getDatumSmrti())), "growx, center, wrap");

        JTextArea opis = new JTextArea();
        opis.setEditable(false);
        opis.setText(izvodjac.getOpis());
        JScrollPane opisScroll = new JScrollPane(opis);
        informacijeOIzvodjacu.getContent().add(opisScroll, "grow, span, wrap");

        this.add(informacijeOIzvodjacu, "growx, wrap");

        JScrollPane sadrzaj = new JScrollPane(this.getContentPane());
        this.setContentPane(sadrzaj);

    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    
}
