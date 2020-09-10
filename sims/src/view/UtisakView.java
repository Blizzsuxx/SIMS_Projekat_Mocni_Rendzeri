package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Utisak;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class UtisakView extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private int brojUtisaka=0;

    public void addKomentar(Utisak komentar){
        JLabel username = new JLabel(komentar.getPisac().getNalog().getKorisnickoIme());
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
        sadrzaj.setSize(panel.getSize());
        sadrzaj.setPreferredSize(panel.getPreferredSize());
        brojUtisaka++;
        try{
            this.setPreferredSize(new Dimension(adjust(this.getWidth()), adjust(this.getHeight())));
            this.setSize(adjust(this.getWidth()), adjust(this.getHeight()));
        } catch(Exception e) {

        }
    }

    private int adjust(int number){
        return (number/(brojUtisaka-1)) * brojUtisaka;
    }

    

    public UtisakView() {
        super(new MigLayout("fillx"));

    }
    
}