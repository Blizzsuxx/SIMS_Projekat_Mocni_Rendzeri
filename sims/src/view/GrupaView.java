package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import model.Clan;
import model.Grupa;

public class GrupaView extends IzvodjacView {

    public GrupaView(JFrame parent, Grupa grupa) {
        super(parent, grupa);
        this.add(new JLabel("Vreme postojanja: " + grupa.getDatumOsnivanja() + " - " + (grupa.getDatumRaspada() == null ? "" : grupa.getDatumRaspada())));
        ExpandingPanel clanovi = new ExpandingPanel("Clanovi");

        ArrayList<Slikovit> izvodjaci = new ArrayList<>();
        for(Clan clan : grupa.getClanovi()){
            izvodjaci.add(clan.getIzvodjac());
        }
        clanovi.setContent(new SearchResults(izvodjaci));
        this.add(clanovi, "grow, span, wrap");

        JScrollPane sadrzaj = new JScrollPane(this.getContentPane());
        this.setContentPane(sadrzaj);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
