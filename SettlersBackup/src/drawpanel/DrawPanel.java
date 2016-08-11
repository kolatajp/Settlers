package drawpanel;

import java.awt.Graphics;
import javax.swing.JPanel;

//g.drawImage( Toolkit.getDefaultToolkit().getImage( getClass().getResource( "ased.png" )

public class DrawPanel extends JPanel {
    drawpanel.DrawPanelManager dpm;
    
    public DrawPanel() {
        super();
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        this.dpm.drawObjects(g);
    }
}
