package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Izvodjac;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Sesija;
import model.Slikovit;
import model.Zanr;

public abstract class IzvodjacView extends MojDialog {

    private Izvodjac izvodjac;
    private JLabel brojPratioca;
    private Korisnik trenutniKorisnik;

    private void refreshBrojPratioca() {
        this.brojPratioca.setText("Pratioci: " + this.izvodjac.getPrati().size());
    }

    public IzvodjacView(JFrame parent, Izvodjac izvodjac) {
        super(parent, "Izvodjac");
        this.izvodjac = izvodjac;
        this.brojPratioca = new JLabel();
        this.trenutniKorisnik = Sesija.getTrenutniKorisnik();

        this.refreshBrojPratioca();

        IzvodjacLabel slikaIzvodjaca = new IzvodjacLabel(this.getWidth() - 50, 100, izvodjac);
        slikaIzvodjaca.setClickable(false);
        this.add(slikaIzvodjaca, "wrap");

        Zanr[] zanrovi = new Zanr[izvodjac.getZanrovi().size()];
        izvodjac.getZanrovi().toArray(zanrovi);
        JComboBox<Zanr> listaZanrova = new JComboBox<>(zanrovi);

        JButton zaprati = new JButton("Zaprati");
        zaprati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(zaprati.getText().equals("Zapratio")) return;
                zaprati.setText("Zapratio");
                if(trenutniKorisnik instanceof KorisnikAplikacije) {
                    KorisnikAplikacije korisnik = (KorisnikAplikacije) trenutniKorisnik;
                    izvodjac.getPrati().add(korisnik);
                    korisnik.addOnajKogaPrati(izvodjac);
                    refreshBrojPratioca();
            }
        }});
        JPanel osnovneInformacije = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        osnovneInformacije.add(new JLabel("Zanrovi: "));
        osnovneInformacije.add(listaZanrova); osnovneInformacije.add(this.brojPratioca); osnovneInformacije.add(zaprati, "wrap");
        this.add(osnovneInformacije, "grow, wrap");
        if(!(trenutniKorisnik instanceof KorisnikAplikacije)) zaprati.setEnabled(false);
        
        

        ExpandingPanel pesme = new ExpandingPanel("Pesme");
        pesme.setContent(new SearchResults((ArrayList<Slikovit>) (ArrayList<?>) izvodjac.getMuzickaDela()));
        this.add(pesme, "growx, wrap");

        ExpandingPanel albumi = new ExpandingPanel("Albumi");
        albumi.setContent(new SearchResults((ArrayList<Slikovit>) (ArrayList<?>) izvodjac.getIzdatiAlbumi()));
        this.add(albumi, "growx, wrap");


        
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    
}
