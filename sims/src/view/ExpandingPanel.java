package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import org.jdesktop.swingx.JXCollapsiblePane;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class ExpandingPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JXCollapsiblePane content;
    private BasicArrowButton expandingButton;
    private JLabel label;


    private void initGui(int basicArrowButtonDirection, JXCollapsiblePane.Direction collapsablePaneDirection){
        

        BasicArrowButton expandingButton = new BasicArrowButton(basicArrowButtonDirection);

        CC componentConstraints = new CC();
        switch(basicArrowButtonDirection) {
        case BasicArrowButton.SOUTH:
        	componentConstraints.dockNorth();
        	break;
        case BasicArrowButton.NORTH:
        	componentConstraints.dockSouth();
        	break;
        case BasicArrowButton.EAST:
        	componentConstraints.dockWest();
        	break;
        case BasicArrowButton.WEST:
        	componentConstraints.dockEast();
        	break;
        }

        this.add(expandingButton, componentConstraints);
        JXCollapsiblePane content = new JXCollapsiblePane(collapsablePaneDirection);
        content.setBackground(Color.BLACK);

        CC componentConstraints2 = new CC();
        componentConstraints2.alignX("center").spanX();
        componentConstraints2.grow();

        this.add(content, componentConstraints2);

        expandingButton.addActionListener(content.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));

        
        this.content = content;
        this.expandingButton = expandingButton;
        this.content.setCollapsed(true);


    }

    private void initGui(){
        initGui(BasicArrowButton.SOUTH, JXCollapsiblePane.Direction.DOWN);
    }


    public ExpandingPanel(String labela){
        super(new MigLayout("fillx"));
        this.setBackground(Color.BLACK);
        
        CC componentConstraints = new CC();
        componentConstraints.alignX("center").spanX();
        componentConstraints.gapLeft("200");
        componentConstraints.dockNorth();

        JLabel label = new JLabel(labela);
        this.add(label, componentConstraints);
        this.label = label;
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        

        initGui();
    }

    public ExpandingPanel() {
        super(new MigLayout("fillx"));

        initGui();

    }



    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    public JPanel getContent() {
        return this.content;
    }

    public BasicArrowButton getExpandingButton() {
        return this.expandingButton;
    }
    
    
    
}