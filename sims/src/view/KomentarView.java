package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Komentar;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class KomentarView extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    public void addKomentar(Komentar komentar){
        JLabel username = new JLabel(komentar.getKomentator().getNalog().getKorisnickoIme());
        JTextArea sadrzaj = new JTextArea(komentar.getText());
        sadrzaj.setEditable(false);
        JScrollPane skrol = new JScrollPane(sadrzaj);
        skrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel panel = new JPanel(new BorderLayout());

        
        CC componentConstraints = new CC();
        componentConstraints.alignX("center").spanX();
        componentConstraints.wrap();
        componentConstraints.grow();


        panel.add(username, BorderLayout.NORTH);
        panel.add(skrol, BorderLayout.CENTER);
        this.add(panel, componentConstraints);
        
    }

    

    public KomentarView() {
        super(new MigLayout("fillx"));
    }
    
}